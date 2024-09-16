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
    private var statusFilter: Boolean = true
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
        statusFilter = arguments?.getBoolean("status") ?: true
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6,true))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6,false))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6,true))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6,false))
        listOrder.add(Order("", "dangvu@gmail.com","Thành tiền: 180 000 VND",6,true))

        // Lọc danh sách theo trạng thái
        val filteredList = listOrder.filter { it.status == statusFilter }.toMutableList()

        val adapterListFeedback = OrderAdapter(filteredList, object : RecyclerViewOnClick {
            override fun onClickItem(pos: Int) {
                // Xử lý khi click item
            }
        })

        val orderList = binding.rvListOrder
        orderList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        orderList.adapter = adapterListFeedback
        orderList.setHasFixedSize(true)

        return binding.root
    }

    companion object {
        // Hàm tạo mới Fragment với trạng thái được truyền vào
        fun newInstance(status: Boolean): OrderFragment {
            val fragment = OrderFragment()
            val args = Bundle()
            args.putBoolean("status", status)
            fragment.arguments = args
            return fragment
        }
    }
}