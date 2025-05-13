package com.example.sendsms

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val SMS_PERMISSION_CODE = 627
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etphone : EditText = findViewById(R.id.etphone)
        val etmsg : EditText = findViewById(R.id.etmsg)
        val btsend : Button = findViewById(R.id.btsend)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this , arrayOf(android.Manifest.permission.SEND_SMS),SMS_PERMISSION_CODE)
        }
        btsend.setOnClickListener {
            val phone = etphone.text.toString()
            val msg = etmsg.text.toString()
            val smsManager : SmsManager
            smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phone, null, msg,null , null  )
            Toast.makeText(this ,"sent sucessfully..!",Toast.LENGTH_LONG).show()
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
