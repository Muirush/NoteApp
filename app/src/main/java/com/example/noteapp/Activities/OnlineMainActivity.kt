package com.example.noteapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import com.example.noteapp.databinding.ActivityOnlineMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class OnlineMainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityOnlineMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDialog()

    }
    private fun  showDialog(){
        val view = binding.mainXML1


        val snackbarView = Snackbar.make(view,"Notes will be saved online an offline since you are connected to the internet", Snackbar.LENGTH_LONG)
        val viewer = snackbarView.view
        val params = viewer.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        snackbarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackbarView.show()
    }
}