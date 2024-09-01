package com.example.androidnangcao.Adapter

import android.graphics.Paint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnangcao.Interface.RecyclerViewOnClick
import com.example.androidnangcao.R
import com.example.androidnangcao.model.Feedback
import com.squareup.picasso.Picasso

class FeedbackAdapter (private var feedbackList: MutableList<Feedback>,
                       private val rvInterfaceItem: RecyclerViewOnClick
) : RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {

    inner class FeedbackViewHolder(feedbackView: View) : RecyclerView.ViewHolder(feedbackView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_feedback, parent, false)
        return FeedbackViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.itemView.apply {
            val imgEmail = findViewById<ImageView>(R.id.imgEmail)
            val tvAddressEmail = findViewById<TextView>(R.id.tvAddressEmail)
            val tvFeedback = findViewById<TextView>(R.id.tvFeedback)

            val feedback = feedbackList[position]
            tvAddressEmail.text = feedback.email
            tvFeedback.text = feedback.feedback

            if (feedback.urlImgEmail.isNotEmpty()) {
                Picasso.get().load(feedback.urlImgEmail).into(imgEmail)
            } else {
                // Tải một hình ảnh mặc định nếu URL rỗng
                Picasso.get().load(R.drawable.ic_item).into(imgEmail)
            }


            holder.itemView.setOnClickListener {
                rvInterfaceItem.onClickItem(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }
}