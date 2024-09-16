package com.example.androidnangcao.Activity.ui_sales.feedback

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnangcao.Activity.DetailFeedbackActivity
import com.example.androidnangcao.Adapter.FeedbackAdapter
import com.example.androidnangcao.Adapter.ItemAdapter
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.databinding.FragmentFeedbackBinding
import com.example.androidnangcao.model.Feedback
import com.example.androidnangcao.model.Item

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null
    private var listFeedback: MutableList<Feedback> = mutableListOf()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listFeedback.add(Feedback("", "dangvu@gmail.com","giao hàng nhanh bh nbvnbvhg ghvghh hvhgh hgvy hvhgv fghv hvhg"))
        listFeedback.add(Feedback("", "dangvu@gmail.com","giao hàng nhanh"))
        listFeedback.add(Feedback("", "dangvu@gmail.com","giao hàng nhanh"))

        val adapterListFeedback = FeedbackAdapter(listFeedback,object : RecyclerViewOnClick {
            override fun onClickItem(pos: Int) {
                val intent = Intent(requireContext(),DetailFeedbackActivity::class.java)
                intent.putExtra("Feedback",listFeedback[pos])
                startActivity(intent)
            }
        })
        var feedbackList = binding.rvListFeedback
        feedbackList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        feedbackList.adapter = adapterListFeedback
        feedbackList.setHasFixedSize(true)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}