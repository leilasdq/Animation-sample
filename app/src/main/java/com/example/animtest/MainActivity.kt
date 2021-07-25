package com.example.animtest

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        rotateButton = findViewById<Button>(R.id.rotate)
        translateButton = findViewById<Button>(R.id.translate)
        scaleButton = findViewById<Button>(R.id.scale)
        fadeButton = findViewById<Button>(R.id.fade)
        colorizeButton = findViewById<Button>(R.id.sky_color)
        showerButton = findViewById<Button>(R.id.shower)

        rotateButton.setOnClickListener {
            rotate()
        }
        translateButton.setOnClickListener {
            translate()
        }
        scaleButton.setOnClickListener {
            scale()
        }
        fadeButton.setOnClickListener {
            fade()
        }
        colorizeButton.setOnClickListener {
            changeBackgroundColor()
        }
        showerButton.setOnClickListener {
            shower()
        }
    }

    @SuppressLint("Recycle")
    private fun shower() {
        val newStar = createNewStar()

        AnimatorSet().playTogether(
            ObjectAnimator.ofFloat(
                newStar,
                View.TRANSLATION_Y,
                (star.parent as ViewGroup).height.toFloat()
            )
                .apply {
                    interpolator = AccelerateInterpolator(1f)
                    duration = 5000
                    start()
                },
            ObjectAnimator.ofFloat(newStar, View.ROTATION, 0f, 360f).apply {
                interpolator = LinearInterpolator()
                startDelay = 500
                duration = 5000
                start()
            }
        )
    }

    private fun createNewStar(): AppCompatImageView {
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()

        val newStar = AppCompatImageView(this).apply {
            setImageResource(R.drawable.ic_star)
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            scaleX = Math.random().toFloat() * 1.5f + .1f
            scaleY = this.scaleX
            starW = scaleX
            starH = scaleY
        }
        ((star.parent) as ViewGroup).addView(newStar)
        newStar.translationX =
            Math.random().toFloat() * (star.parent as ViewGroup).width - starW / 2
        newStar.translationY =
            Math.random().toFloat() * (star.parent as ViewGroup).height - starH / 2

        return newStar
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun changeBackgroundColor() {
        ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED).apply {
            disableViewDuringAnimation(colorizeButton)
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            duration = 5000
            startDelay = 1000
            start()
        }
    }

    private fun fade() {
        ObjectAnimator.ofFloat(star, View.ALPHA, 0f).apply {
            duration = 3000
            startDelay = 500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            disableViewDuringAnimation(fadeButton)
            start()
        }
    }

    @SuppressLint("Recycle")
    private fun scale() {
        /*AnimatorSet().playTogether(
            ObjectAnimator.ofFloat(star, View.SCALE_X, 20f).apply {
                duration = 3000
                startDelay = 500
                repeatCount = 1
                repeatMode = ValueAnimator.REVERSE
                disableViewDuringAnimation(scaleButton)
                start()
            },
            ObjectAnimator.ofFloat(star, View.SCALE_Y, 20f).apply {
                duration = 3000
                startDelay = 500
                repeatCount = 1
                repeatMode = ValueAnimator.REVERSE
                disableViewDuringAnimation(scaleButton)
                start()
            }
        )*/
        ObjectAnimator.ofPropertyValuesHolder(
            star,
            PropertyValuesHolder.ofFloat(View.SCALE_X, 20f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 20f)
        ).apply {
            duration = 3000
            startDelay = 500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            disableViewDuringAnimation(scaleButton)
            start()
        }

    }

    private fun rotate() {
        ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f).apply {
            duration = 3000
            startDelay = 500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            disableViewDuringAnimation(rotateButton)
            start()
        }
    }

    private fun translate() {
        ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 100f).apply {
            duration = 3000
            startDelay = 500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            disableViewDuringAnimation(translateButton)
            start()
        }
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
}