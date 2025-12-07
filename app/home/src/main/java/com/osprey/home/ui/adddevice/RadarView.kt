package com.osprey.home.ui.adddevice
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.animation.ValueAnimator
import androidx.core.graphics.toColorInt

class RadarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val sweepPaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val centerPaint = Paint().apply {
        color = "#4A8DFF".toColorInt()
        isAntiAlias = true
    }

    private var sweepAngle = 0f
    private var centerX = 0f
    private var centerY = 0f

    private val animator = ValueAnimator.ofFloat(0f, 360f).apply {
        duration = 2000L
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener {
            sweepAngle = it.animatedValue as Float
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f

        val sweepGradient = SweepGradient(
            centerX, centerY,
            intArrayOf(
                "#30AEEAFF".toColorInt(),
                "#50AEEAFF".toColorInt(),
                "#80AEEAFF".toColorInt(),
                "#C0AEEAFF".toColorInt()
            ),
            floatArrayOf(0f, 0.3f, 0.7f, 1f)
        )

        val gradientMatrix = Matrix()
        sweepPaint.shader = sweepGradient
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = width / 2f

        val circlePaint = Paint().apply {
            color = Color.parseColor("#40AEEAFF")
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        canvas.drawCircle(centerX, centerY, radius, circlePaint)
        canvas.drawCircle(centerX, centerY, radius * 0.7f, circlePaint)
        canvas.drawCircle(centerX, centerY, radius * 0.4f, circlePaint)

        canvas.save()
        canvas.rotate(sweepAngle, centerX, centerY)

        val path = Path()
        path.moveTo(centerX, centerY)
        path.arcTo(
            centerX - radius, centerY - radius,
            centerX + radius, centerY + radius,
            0f, 120f, false // Góc quét cố định, sẽ xoay bằng canvas.rotate
        )
        path.close()

        canvas.drawPath(path, sweepPaint)
        canvas.restore()

        canvas.drawCircle(centerX, centerY, radius * 0.05f, centerPaint)
    }
}