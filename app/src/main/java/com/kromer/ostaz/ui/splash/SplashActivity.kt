package com.kromer.ostaz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.kromer.ostaz.databinding.ActivitySplashBinding
import com.kromer.ostaz.ui.base.BaseActivity
import com.kromer.ostaz.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun getVBInflater(): (LayoutInflater) -> ActivitySplashBinding =
        ActivitySplashBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}