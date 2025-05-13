package com.example.exp7


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var regNoEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var cgpaEditText: EditText
    private lateinit var saveButton: Button

    private val REQUEST_CODE_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        regNoEditText = findViewById(R.id.editTextRegNo)
        nameEditText = findViewById(R.id.editTextName)
        cgpaEditText = findViewById(R.id.editTextCgpa)
        saveButton = findViewById(R.id.buttonSave)

        saveButton.setOnClickListener {
            if (checkPermission()) {
                writeToFile()
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            writeToFile()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun writeToFile() {
        val regNo = regNoEditText.text.toString()
        val name = nameEditText.text.toString()
        val cgpa = cgpaEditText.text.toString()

        if (regNo.isEmpty() || name.isEmpty() || cgpa.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val state = Environment.getExternalStorageState()
        if (state == Environment.MEDIA_MOUNTED) {
            val file = File(Environment.getExternalStorageDirectory(), "student_data.txt")
            try {
                val fos = FileOutputStream(file, true)
                fos.write("Register Number: $regNo\nName: $name\nCGPA: $cgpa\n\n".toByteArray())
                fos.close()
                Toast.makeText(this, "Data saved to SD card", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error writing to file: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "SD card not mounted", Toast.LENGTH_SHORT).show()
        }
    }
}
