package com.example.androidnangcao.Frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnangcao.Adapter.OrderAdapter
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.databinding.FragmentOrderBinding
import com.example.androidnangcao.model.Order

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    private var listOrder: MutableList<Order> = mutableListOf()
    private lateinit var binding: FragmentOrderBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6))

        val adapterListFeedback = OrderAdapter(listOrder,object : RecyclerViewOnClick {
            override fun onClickItem(pos: Int) {

            }
        })
        var orderList = binding.rvListOrder
        orderList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        orderList.adapter = adapterListFeedback
        orderList.setHasFixedSize(true)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProcessingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}