package com.example.exp2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var lastOperator: String? = null
    private var previousInput: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Set up the buttons
        val button1 = findViewById<Button>(R.id.button1)
        val button2= findViewById<Button>(R.id.button2)
        val button3= findViewById<Button>(R.id.button3)
        val button4= findViewById<Button>(R.id.button4)
        val button5= findViewById<Button>(R.id.button5)
        val button6= findViewById<Button>(R.id.button6)
        val button7= findViewById<Button>(R.id.button7)
        val button8= findViewById<Button>(R.id.button8)
        val button9= findViewById<Button>(R.id.button9)
        val button0= findViewById<Button>(R.id.button0)
        val buttonClear= findViewById<Button>(R.id.buttonClear)
        val buttonPlus= findViewById<Button>(R.id.buttonPlus)
        val buttonMinus= findViewById<Button>(R.id.buttonMinus)
        val buttonMultiply= findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide= findViewById<Button>(R.id.buttonDivide)
        val buttonEquals= findViewById<Button>(R.id.buttonEquals)

        // Set onClick listeners for buttons
        button1.setOnClickListener { onButtonClick("1") }
        button2.setOnClickListener { onButtonClick("2") }
        button3.setOnClickListener { onButtonClick("3") }
        button4.setOnClickListener { onButtonClick("4") }
        button5.setOnClickListener { onButtonClick("5") }
        button6.setOnClickListener { onButtonClick("6") }
        button7.setOnClickListener { onButtonClick("7") }
        button8.setOnClickListener { onButtonClick("8") }
        button9.setOnClickListener { onButtonClick("9") }
        button0.setOnClickListener { onButtonClick("0") }
        buttonClear.setOnClickListener { onButtonClick("C") }
        buttonPlus.setOnClickListener { onButtonClick("+") }
        buttonMinus.setOnClickListener { onButtonClick("-") }
        buttonMultiply.setOnClickListener { onButtonClick("*") }
        buttonDivide.setOnClickListener { onButtonClick("/") }
        buttonEquals.setOnClickListener { onButtonClick("=") }
    }

    private fun onButtonClick(value: String) {
        when (value) {
            "C" -> {
                // Clear the display
                currentInput = ""
                previousInput = null
                lastOperator = null
                display.text = "0"
            }
            "=" -> {
                // Calculate the result
                if (currentInput.isNotEmpty() && previousInput != null && lastOperator != null) {
                    val num1 = previousInput!!.toDouble()
                    val num2 = currentInput.toDouble()
                    val result = when (lastOperator) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                        else -> 0.0
                    }
                    display.text = result.toString()
                    currentInput = result.toString()
                    previousInput = null
                    lastOperator = null
                }
            }
            "+" -> setOperator("+")
            "-" -> setOperator("-")
            "*" -> setOperator("*")
            "/" -> setOperator("/")
            else -> {
                // Append the clicked button value to the current input
                currentInput += value
                display.text = currentInput
            }
        }
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            previousInput = currentInput
            currentInput = ""
            lastOperator = operator
        }
    }
}