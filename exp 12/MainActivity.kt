package com.example.exp12

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button by its ID
        val sendEmailButton: Button = findViewById(R.id.sendEmailButton)

        // Set up a click listener for the button
        sendEmailButton.setOnClickListener {
            // Trigger the sendEmail function
            sendEmail()
        }
    }

    // Function to send email using an Intent
    private fun sendEmail() {
        val recipient = "example@example.com"  // Email recipient
        val subject = "Subject of the Email"
        val message = "This is the body of the email."

        // Create the intent to send email
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")  // Ensure only email apps handle the intent
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))  // Recipient
            putExtra(Intent.EXTRA_SUBJECT, subject)  // Subject
            putExtra(Intent.EXTRA_TEXT, message)  // Message body
        }

        // Check if there's an email client available to handle this intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)  // Start the email client
        } else {
            // Handle the case where no email client is available
            // Show a toast or an alert dialog (optional)
        }
    }
}
