package com.itechart.news_app.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itechart.news_app.databinding.ItemNewsBinding
import com.itechart.news_app.domain.model.Article

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position)!!)

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.apply {
                Glide
                    .with(binding.root.context)
                    .load(data.urlToImage)
                    .centerCrop()
                    .into(image)
                title.text = data.title
                subtitle.text = data.author
                date.text = data.publishedAt
                body.text = data.description
            }
        }
    }
}

class NewsDiffCallback: DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = areItemsTheSame(oldItem, newItem)
}