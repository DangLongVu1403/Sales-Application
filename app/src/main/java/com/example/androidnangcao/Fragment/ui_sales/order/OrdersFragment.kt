package com.example.androidnangcao.Fragment.ui_sales.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnangcao.Adapter.ViewPagerAdapter
import com.example.androidnangcao.R
import com.example.androidnangcao.databinding.FragmentOrdersBinding
import com.google.android.material.tabs.TabLayoutMediator

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Thiết lập adapter cho ViewPager2
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity())

        // Kết nối TabLayout với ViewPager2
        val processing = requireActivity().getString(R.string.processing_order)
        val done = requireActivity().getString(R.string.done_order)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> processing
                else -> done
            }
        }.attach()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}