package com.example.androidnangcao.Fragment.ui_sales.individual

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidnangcao.Activity.InforStoreActivity
import com.example.androidnangcao.Activity.StatisticalRevenueActivity
import com.example.androidnangcao.databinding.FragmentIndividualBinding

class IndividualFragment : Fragment() {

    private var _binding: FragmentIndividualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndividualBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewProfile.setOnClickListener {
            val intent = Intent(requireActivity(), InforStoreActivity::class.java)
            startActivity(intent)
        }

        binding.viewRevenueStatistics.setOnClickListener {
            val intent = Intent(requireActivity(), StatisticalRevenueActivity::class.java)
            startActivity(intent)
        }

        binding.viewContact.setOnClickListener {

        }

        binding.viewLogout.setOnClickListener {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}