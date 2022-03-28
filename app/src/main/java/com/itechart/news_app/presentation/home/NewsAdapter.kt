package com.itechart.news_app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itechart.news_app.databinding.ItemNewsBinding
import com.itechart.news_app.domain.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var news = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(news[position])

    override fun getItemCount() = news.size

    fun getNews(news: List<Article>) {
        this.news.clear()
        this.news = news.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
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