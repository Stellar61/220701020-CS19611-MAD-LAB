package com.example.exp8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.AlertDialog
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showDialogButton: Button = findViewById(R.id.showDialogButton)
        val inputName: EditText = findViewById(R.id.inputname)

        showDialogButton.setOnClickListener {
            val name = inputName.text.toString()
            showAlertDialog(name)
        }
    }

    private fun showAlertDialog(name: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hello!")
        builder.setMessage("Are you sure you want to proceed, $name?")
        builder.setCancelable(false)

        builder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
