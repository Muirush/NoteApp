package com.example.noteapp.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityLaunchBinding
import com.example.noteapp.utils.InternetChecker

class Launch : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Glide.with(this).load(R.drawable.loading).into(binding.gif)

        //Check Internet and redirect

        val checkNet = InternetChecker()

        if (checkNet.netz(this)){
            Handler(Looper.getMainLooper()).postDelayed({
               val intentOnline  = Intent(this@Launch, OnlineMainActivity::class.java)
                startActivity(intentOnline)
                finish()

            }, 3000)

            //Toast.makeText(this, "Internet connected", Toast.LENGTH_SHORT).show()
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                val intentOffline = Intent(this@Launch, MainActivity::class.java)
                startActivity(intentOffline)
                finish()

            }, 3000)
            //Toast.makeText(this, "Internet  not connected", Toast.LENGTH_SHORT).show()
        }

    }



}