package com.example.exp4


    import android.os.Bundle
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity

    class SecondActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_second)

            // Get references to the TextViews where the data will be displayed
            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val rnoTextView = findViewById<TextView>(R.id.rnoTextView)
            val emailTextView = findViewById<TextView>(R.id.emailTextView)
            val sslcTextView = findViewById<TextView>(R.id.sslcTextView)
            val hscTextView = findViewById<TextView>(R.id.hscTextView)
            val cgpaTextView = findViewById<TextView>(R.id.cgpaTextView)

            // Retrieve the data passed from the first activity
            val name = intent.getStringExtra("name")
            val rno = intent.getStringExtra("rno")
            val email = intent.getStringExtra("email")
            val sslc = intent.getStringExtra("sslc")
            val hsc = intent.getStringExtra("hsc")
            val cgpa = intent.getStringExtra("cgpa")

            // Set the text of the TextViews to display the received data
            nameTextView.text = "Name: $name"
            rnoTextView.text = "Roll No: $rno"
            emailTextView.text = "Email: $email"
            sslcTextView.text = "SSLC percentage: $sslc"
            hscTextView.text = "HSC percentage: $hsc"
            cgpaTextView.text = "UG cgpa: $cgpa"

        }
    }
