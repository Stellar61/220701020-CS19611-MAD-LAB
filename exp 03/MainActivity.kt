package com.example.exp3

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customView = object : View(this) {
            private val paint = Paint().apply {
                isAntiAlias = true
            }

            override fun onDraw(canvas: Canvas) {
                super.onDraw(canvas)

                paint.color = Color.RED
                canvas.drawCircle(300f, 300f, 100f, paint)

                paint.color = Color.GREEN
                val ellipseRect = RectF(150f, 500f, 450f, 700f)
                canvas.drawOval(ellipseRect, paint)

                paint.color = Color.BLUE
                val rect = RectF(100f, 800f, 600f, 1000f)
                canvas.drawRect(rect, paint)

                paint.color = Color.BLACK
                paint.textSize = 50f
                canvas.drawText("Circle , Ellipse , Rectangle", 250f, 1200f, paint)
            }
        }

        val drawingLayout = findViewById<FrameLayout>(R.id.drawingLayout)
        drawingLayout.addView(customView)
    }
}
