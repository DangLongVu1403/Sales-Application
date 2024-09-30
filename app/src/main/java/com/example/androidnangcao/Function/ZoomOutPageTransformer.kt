package com.example.androidnangcao.Function

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        val absPos = Math.abs(position)

        if (position < -1 || position > 1) {
            page.alpha = 0f
        } else if (position <= 0) {
            page.alpha = 1 + position
            page.translationX = 0f
            page.scaleX = 1 + position * 0.2f
            page.scaleY = 1 + position * 0.2f
        } else {
            page.alpha = 1 - position
            page.translationX = -pageWidth * position
            page.scaleX = 1 - position * 0.2f
            page.scaleY = 1 - position * 0.2f
        }
    }
}
