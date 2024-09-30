package com.example.androidnangcao.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.androidnangcao.Adapter.BannerAdapter
import com.example.androidnangcao.Adapter.ProductAdapter
import com.example.androidnangcao.R
import com.example.androidnangcao.Transformer.ZoomOutPageTransformer
import com.example.androidnangcao.databinding.FragmentHomeUserBinding
import com.example.androidnangcao.model.Item
import java.text.Normalizer
import java.util.regex.Pattern
import kotlin.math.abs

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeUserFragment : Fragment() {
    private lateinit var binding: FragmentHomeUserBinding
    private lateinit var viewPagerBanner: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private var listProduct: MutableList<Item> = mutableListOf()
    private lateinit var adapter: ProductAdapter
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
        binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        val searchView = binding.search

        searchView.setOnClickListener {
            searchView.isIconified = false
            searchView.requestFocus()
        }

        viewPagerBanner = binding.viewPagerBanner
        init(viewPagerBanner)
        setUpTransformer()

        viewPagerBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })

        // Initializing list and adapter
        listProduct.add(Item("", "", "Áo polo", "180000", "áo thiết kế đẹp mắt", 100, 10,10))
        listProduct.add(Item("", "", "Áo thun", "150000", "áo chất lượng cao", 200, 20,10))
        listProduct.add(Item("", "", "Áo sơ mi", "200000", "áo lịch sự", 150, 30,10))
        adapter = ProductAdapter(listProduct)
        binding.rvListProduct.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvListProduct.adapter = adapter

        // Thiết lập SearchView để lọc danh sách
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    private fun filterList(query: String?) {
        val filtered = if (!query.isNullOrEmpty()) {
            val searchText = removeDiacritics(query.lowercase())
            listProduct.filter { item ->
                val itemName = removeDiacritics(item.name.lowercase())
                itemName.contains(searchText)
            }
        } else {
            listProduct
        }
        adapter.updateProducts(filtered)
    }

    private fun removeDiacritics(text: String): String {
        val normalized = Normalizer.normalize(text, Normalizer.Form.NFD)
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("")
    }

    private val runnable = Runnable {
        viewPagerBanner.currentItem = viewPagerBanner.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPagerBanner.setPageTransformer(transformer)
    }

    private fun init(viewPagerBanner: ViewPager2) {
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.img)
        imageList.add(R.drawable.img_1)
        imageList.add(R.drawable.img_2)
        imageList.add(R.drawable.img_3)

        val bannerAdapter = BannerAdapter(imageList, viewPagerBanner)
        viewPagerBanner.adapter = bannerAdapter
        viewPagerBanner.offscreenPageLimit = 3
        viewPagerBanner.clipToPadding = false
        viewPagerBanner.clipChildren = false
        viewPagerBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

