package com.example.animtest

import android.animation.*
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

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