package com.example.androidnangcao.Transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val scaleFactor = 0.85f + (1 - abs(position)) * 0.15f
            val alphaFactor = 0.5f + (1 - abs(position)) * 0.5f

            scaleX = scaleFactor
            scaleY = scaleFactor
            alpha = alphaFactor

            translationX = -position * view.width * 0.25f
        }
    }
}
