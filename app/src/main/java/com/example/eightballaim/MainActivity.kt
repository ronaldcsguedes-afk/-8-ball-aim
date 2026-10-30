
package com.example.eightballaim

import android.app.Activity
import android.os.Bundle
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TableView())
    }
}

class TableView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var targetX = 650f
    private var targetY = 700f

    constructor(context: android.content.Context) : super(context)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.rgb(12, 18, 28))

        val w = width.toFloat()
        val h = height.toFloat()

        paint.color = Color.WHITE
        paint.textSize = 55f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("8 BALL AIM", w / 2, 100f, paint)

        val table = RectF(25f, 180f, w-25f, h-260f)
        paint.color = Color.rgb(110, 65, 35)
        canvas.drawRoundRect(table, 25f, 25f, paint)

        val felt = RectF(45f, 200f, w-45f, h-280f)
        paint.color = Color.rgb(20, 115, 75)
        canvas.drawRoundRect(felt, 15f, 15f, paint)

        val cx = w * 0.30f
        val cy = h * 0.52f
        val ox = w * 0.62f
        val oy = h * 0.48f

        paint.color = Color.YELLOW
        paint.strokeWidth = 5f
        canvas.drawLine(cx, cy, targetX, targetY, paint)

        paint.color = Color.CYAN
        paint.strokeWidth = 3f
        paint.pathEffect = DashPathEffect(floatArrayOf(15f, 10f), 0f)
        canvas.drawLine(cx, cy, targetX, targetY, paint)
        paint.pathEffect = null

        ball(canvas, cx, cy, Color.WHITE)
        ball(canvas, ox, oy, Color.YELLOW)
        ball(canvas, w*.75f, h*.36f, Color.BLUE)
        ball(canvas, w*.72f, h*.65f, Color.BLACK)

        paint.color = Color.WHITE
        paint.textSize = 25f
        canvas.drawText("Toque na mesa para mirar", w/2, h-100f, paint)
    }

    private fun ball(c: Canvas, x: Float, y: Float, color: Int) {
        paint.color = color
        c.drawCircle(x, y, 27f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE) {
            targetX = event.x
            targetY = event.y
            invalidate()
            return true
        }
        return true
    }
}
