package com.example.exp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val msg=findViewById<TextView>(R.id.text)
        val btn1=findViewById<Button>(R.id.button1)
        val btn2=findViewById<Button>(R.id.button2)
        val btn3=findViewById<Button>(R.id.button3)
        btn1.setOnClickListener {
            msg.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
        btn2.setOnClickListener{
            msg.textSize=30f
        }
        btn3.setOnClickListener{
            Toast.makeText(this,"Welcome!!",Toast.LENGTH_SHORT).show()
        }



    }
}