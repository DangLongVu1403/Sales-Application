package com.example.androidnangcao.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidnangcao.Fragment.OrderFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // Số lượng tab
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OrderFragment.newInstance(false) // Tab đầu tiên có trạng thái true
            1 -> OrderFragment.newInstance(true) // Tab thứ hai có trạng thái false
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
