package com.example.subactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample","Main onCreate() called.")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        Log.i("LifeCycleSample","Main onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i("LifeCycleSample","Main onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i("LifeCycleSample","Main onResume() called.")
        super.onResume()
    }
    public override fun onPause() {
        Log.i("LifeCycleSample","Main onPause() called.")
        super.onPause()
    }
    public override fun onStop() {
        Log.i("LifeCycleSample","Main onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i("LifeCycleSample","Main onDestoroy() called.")
        super.onDestroy()
    }

    fun onButtonclick(view: View) {
        val intent = Intent(applicationContext,Sub2Activity::class.java)
        startActivity(intent)
    }
}
