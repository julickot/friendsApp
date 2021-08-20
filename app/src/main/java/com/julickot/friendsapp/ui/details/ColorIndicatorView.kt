package com.julickot.friendsapp.ui.details

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ColorIndicatorView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val radius: Float = 20f
    val defaultWidth: Int = 50
    val defaultHeight: Int = 50

    private val drawPaint by lazy {
        Paint().apply {
            color = 0
            isAntiAlias = true
            style = Paint.Style.FILL
        }
    }

    fun setColor(color: Int) {
        drawPaint.color = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2f, height / 2f, radius, drawPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureDimension(widthMeasureSpec, defaultWidth)
        val height = measureDimension(heightMeasureSpec, defaultHeight)

        setMeasuredDimension(width, height)
    }

    private fun measureDimension(spec: Int, defaultValue: Int): Int {
        val mode = MeasureSpec.getMode(spec)
        val size = MeasureSpec.getSize(spec)

        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> Math.min(defaultValue, size)
            else -> defaultValue
        }
    }
}