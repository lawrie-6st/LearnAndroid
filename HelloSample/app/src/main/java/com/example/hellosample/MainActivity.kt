package com.example.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btClick =findViewById<Button>(R.id.bt_click)
        val listner= HelloListner()
        btClick.setOnClickListener(listner)

        val btClear =findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listner)
    }

    private inner class HelloListner : View.OnClickListener {
        override fun onClick(view: View){
            val input =findViewById<EditText>(R.id.etName)
            val output = findViewById<TextView>(R.id.tv_Output)
            when(view.id){
                R.id.bt_click ->{
                    val inputStr = input.text.toString()
                    output.text = inputStr + "さん、こんにちわ！"
                }
                R.id.btClear ->{
                    input.setText("")
                    output.text = ""
                }
            }
        }
    }
}
