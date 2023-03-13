package com.example.kitsuapp.presentation.activity

import android.view.LayoutInflater
import com.example.kitsuapp.core.base.BaseActivity
import com.example.kitsuapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}