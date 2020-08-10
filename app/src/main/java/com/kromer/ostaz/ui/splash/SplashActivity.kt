package com.kromer.ostaz.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kromer.ostaz.ui.base.BaseActivity
import com.kromer.ostaz.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}