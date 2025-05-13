package com.example.exp6

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to the UI elements
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val idEditText = findViewById<EditText>(R.id.idEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val cpasswordEditText = findViewById<EditText>(R.id.cpasswordEditText)
        val validateButton = findViewById<Button>(R.id.validateButton)
        val validationMessage = findViewById<TextView>(R.id.validationMessage)

        validateButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = cpasswordEditText.text.toString().trim()

            // Validation logic
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(id)) {
                validationMessage.text = "Both fields are required."
                validationMessage.visibility = TextView.VISIBLE
            } else if (!username.matches(Regex("^[a-zA-Z]+$"))) {
                validationMessage.text = "Username must contain only alphabets."
                validationMessage.visibility = TextView.VISIBLE
            } else if (!id.matches(Regex("^[0-9]{4}$"))) {
                validationMessage.text = "ID must be a 4-digit number."
                validationMessage.visibility = TextView.VISIBLE
            } else if (password != confirmPassword) {
                validationMessage.text = "Password and Confirm Password both should be same."
                validationMessage.visibility = TextView.VISIBLE
            }else {
                validationMessage.visibility = TextView.GONE
                Toast.makeText(this, "Validation Successful!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}