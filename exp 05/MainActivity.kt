package com.example.exp5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = UserDatabaseHelper(this)

        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val buttonInsert = findViewById<Button>(R.id.buttonInsert)
        val buttonFetch = findViewById<Button>(R.id.buttonFetch)
        val buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        // Insert Data
        buttonInsert.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            if (name.isNotEmpty() && age != null) {
                val id = dbHelper.insertUser(name, age)
                if (id != -1L) {
                    Toast.makeText(this, "User added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Insert failed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter valid data", Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch Data
        buttonFetch.setOnClickListener {
            val users = dbHelper.getAllUsers()
            textViewResult.text = if (users.isNotEmpty()) users.joinToString("\n") else "No data found"
        }

        // Update Data
        buttonUpdate.setOnClickListener {
            val id = editTextId.text.toString().toIntOrNull()
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()

            if (id != null && name.isNotEmpty() && age != null) {
                val updated = dbHelper.updateUser(id, name, age)
                if (updated) {
                    Toast.makeText(this, "User updated!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Update failed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter valid ID, Name, and Age", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete Data
        buttonDelete.setOnClickListener {
            val id = editTextId.text.toString().toIntOrNull()

            if (id != null) {
                val deleted = dbHelper.deleteUser(id)
                if (deleted) {
                    Toast.makeText(this, "User deleted!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Delete failed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter valid ID", Toast.LENGTH_SHORT).show()
            }
        }
    }
}