package com.example.calculator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.viewModel.MyViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}