package com.example.subactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class Sub2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("Sub","Main onCreate() called.")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub2)
    }

    public override fun onStart() {
        Log.i("Sub","Main onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i("Sub","Main onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i("Sub","Main onResume() called.")
        super.onResume()
    }
    public override fun onPause() {
        Log.i("Sub","Main onPause() called.")
        super.onPause()
    }
    public override fun onStop() {
        Log.i("Sub","Main onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i("Sub","Main onDestoroy() called.")
        super.onDestroy()
    }

    fun onButtonclick(view: View) {
        finish()
    }
}
