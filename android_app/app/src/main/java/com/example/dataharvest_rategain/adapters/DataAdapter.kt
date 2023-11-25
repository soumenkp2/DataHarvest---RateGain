package com.example.dataharvest_rategain.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dataharvest_rategain.R
import com.example.dataharvest_rategain.datamodels.Data
import com.squareup.picasso.Picasso


class DataAdapter(private val context: Context, private val dataList: List<Data>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_rcv, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]

        if(data.image_url!=null && !data.image_url.toString().isEmpty()) {
            Picasso.get().load(data.image_url).into(holder.blogImage)
        }

        holder.blogTitle.text = data.blog_title
        holder.date.text = data.date.toString()
        holder.likes.text = "Likes - " + data.likes.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blogImage: ImageView = itemView.findViewById(R.id.iv_blog_image)
        val blogTitle: TextView = itemView.findViewById(R.id.tv_blog_title)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val likes: TextView = itemView.findViewById(R.id.tv_likes)
    }
}