package com.example.androidnangcao.Activity.ui_sales.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidnangcao.Activity.AddItemActivity
import com.example.androidnangcao.Activity.DetailItemActivity
import com.example.androidnangcao.Adapter.ItemAdapter
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.model.Item
import com.example.androidnangcao.databinding.FragmentHomeBinding
import java.text.Normalizer
import java.util.regex.Pattern

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var listItem: MutableList<Item> = mutableListOf()
    private var filteredList: MutableList<Item> = mutableListOf() // Danh sách được lọc
    private lateinit var adapter: ItemAdapter // Adapter cho RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val searchView = binding.search

        // Thêm sự kiện cho nút thêm sản phẩm
        binding.btnAddProduct.setOnClickListener {
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            startActivity(intent)
        }

        // Đảm bảo SearchView không bị thu nhỏ
        searchView.setOnClickListener {
            searchView.isIconified = false
            searchView.requestFocus()
        }

        // Thêm dữ liệu mẫu vào listItem
        listItem.add(Item("", "", "Áo polo", "180000", "áo thiết kế đẹp mắt", 100, 10))
        listItem.add(Item("", "", "Áo thun", "150000", "áo chất lượng cao", 200, 20))
        listItem.add(Item("", "", "Áo sơ mi", "200000", "áo lịch sự", 150, 30))

        // Khởi tạo danh sách lọc ban đầu với toàn bộ sản phẩm
        filteredList.addAll(listItem)

        // Khởi tạo adapter với danh sách ban đầu
        adapter = ItemAdapter(filteredList, object : RecyclerViewOnClick {
            override fun onClickItem(pos: Int) {
                val intent = Intent(requireContext(), DetailItemActivity::class.java)
                intent.putExtra("Item", filteredList[pos])
                startActivity(intent)
            }
        })

        // Thiết lập RecyclerView
        val itemList = binding.rvListItem
        itemList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        itemList.adapter = adapter
        itemList.setHasFixedSize(true)

        // Thiết lập SearchView để lọc danh sách
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Xử lý khi người dùng nhấn nút tìm kiếm
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return root
    }

    private fun filterList(query: String?) {
        filteredList.clear()
        if (!query.isNullOrEmpty()) {
            val searchText = removeDiacritics(query.lowercase())
            listItem.forEach { item ->
                // Loại bỏ dấu từ tên sản phẩm trước khi so sánh
                val itemName = removeDiacritics(item.name.lowercase())
                if (itemName.contains(searchText)) {
                    filteredList.add(item)
                }
            }
        } else {
            filteredList.addAll(listItem)
        }
        adapter.notifyDataSetChanged()
    }

    private fun removeDiacritics(text: String): String {
        val normalized = Normalizer.normalize(text, Normalizer.Form.NFD)
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
