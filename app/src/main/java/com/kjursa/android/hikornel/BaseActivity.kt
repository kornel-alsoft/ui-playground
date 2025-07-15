package com.kjursa.android.hikornel

import android.util.Log
import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {

    override fun onResume() {
        super.onResume()
        Log.d("Base", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Base", "onPause")
    }


}

fun testA(s: String, b: Int): Int {
    return 1
}

fun String.test(b:Int): Int {
    return 2
}