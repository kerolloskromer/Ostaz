package com.kromer.ostaz.di.module

import android.os.Build
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kromer.ostaz.BuildConfig
import com.kromer.ostaz.data.local.prefs.Preferences
import com.kromer.ostaz.data.source.remote.NotificationsApiInterface
import com.kromer.ostaz.data.source.remote.PostsApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://192.168.1.3:8000/"

    private const val REQUEST_TIME_OUT: Long = 60

    @Provides
    @Singleton
    fun provideHeadersInterceptor(preferences: Preferences): Interceptor =
        Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Authorization", preferences.firebaseIdToken)
                .addHeader("Version-Name", BuildConfig.VERSION_NAME)
                .addHeader("Version-Code", BuildConfig.VERSION_CODE.toString())
                .addHeader("Android-Version-Code", Build.VERSION.SDK_INT.toString())
                .addHeader("Android-Version-Name", Build.VERSION.RELEASE);
            val request = requestBuilder.build()
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideAuthenticator(preferences: Preferences): Authenticator = object : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            if (response.code == 401) {
                val task: Task<GetTokenResult>? =
                    FirebaseAuth.getInstance().currentUser?.getIdToken(true)
                task?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        val idToken = it.result!!.token!!
                        preferences.firebaseIdToken = "FirebaseToken $idToken"
                    }
                }

                Tasks.await(task!!);

                return response.request.newBuilder()
                    .header("Authorization", preferences.firebaseIdToken).build()
            }
            return null
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor,
        authenticator: Authenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(headersInterceptor)
        .addNetworkInterceptor(logging)
        .authenticator(authenticator)
        .retryOnConnectionFailure(false)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls() // to allow sending null values
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun providePostsApiInterface(retrofit: Retrofit): PostsApiInterface =
        retrofit.create(PostsApiInterface::class.java)

    @Provides
    @Singleton
    fun provideNotificationsApiInterface(retrofit: Retrofit): NotificationsApiInterface =
        retrofit.create(NotificationsApiInterface::class.java)
}