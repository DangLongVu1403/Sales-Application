package com.example.androidnangcao.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidnangcao.Frament.OrderFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2 // Số lượng tab
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OrderFragment()
            else -> OrderFragment()
        }
    }
}
