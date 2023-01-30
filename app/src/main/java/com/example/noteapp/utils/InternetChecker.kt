package com.example.noteapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/*
* A public class that is used to check internet connectivity.
* and redirects. If no internet situation, it redirects to either online or offline.
*
* ******Check on connect without internet(WIFI and mobile data)
*
* */

class InternetChecker {
     fun netz(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            val network = connectivityManager.activeNetwork ?: return false


            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true


                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true


                else -> false
            }
        } else {

            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}