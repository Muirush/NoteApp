package com.example.noteapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityLaunchBinding

class Launch : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Glide.with(this).load(R.drawable.loading).into(binding.gif)
        //Check Internet and redirect

    }
}