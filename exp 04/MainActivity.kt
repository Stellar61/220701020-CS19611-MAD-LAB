package com.example.exp4


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to the EditText views
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val rnoEditText = findViewById<EditText>(R.id.rnoEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val sslcEditText = findViewById<EditText>(R.id.sslcEditText)
        val hscEditText = findViewById<EditText>(R.id.hscEditText)
        val cgpaEditText = findViewById<EditText>(R.id.cgpaEditText)

        // Button to navigate to the second activity
        val sendButton = findViewById<Button>(R.id.sendButton)
        sendButton.setOnClickListener {
            // Get the text entered by the user
            val name = nameEditText.text.toString()
            val rno = rnoEditText.text.toString()
            val email = emailEditText.text.toString()
            val sslc = sslcEditText.text.toString()
            val hsc = hscEditText.text.toString()
            val cgpa = cgpaEditText.text.toString()

            // Check if name and age are not empty
            if (name.isNotEmpty() && rno.isNotEmpty() && email.isNotEmpty()&& sslc.isNotEmpty() && hsc.isNotEmpty() && cgpa.isNotEmpty()) {
                // Create an Intent to open the second activity
                val intent = Intent(this, SecondActivity::class.java)

                // Add data to the intent using putExtra
                intent.putExtra("name", name)
                intent.putExtra("roll no", rno)
                intent.putExtra("email", email)
                intent.putExtra("sslc", sslc)
                intent.putExtra("hsc", hsc)
                intent.putExtra("cgpa", cgpa)

                // Start the second activity
                startActivity(intent)
            }
        }
        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            // Clearing all EditText fields
            nameEditText.text.clear()
            rnoEditText.text.clear()
            emailEditText.text.clear()
            sslcEditText.text.clear()
            hscEditText.text.clear()
            cgpaEditText.text.clear()
        }
    }
}
