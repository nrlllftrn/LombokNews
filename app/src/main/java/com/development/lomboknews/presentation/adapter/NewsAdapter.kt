package com.development.lomboknews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.databinding.ItemNewsBinding

class NewsAdapter: ListAdapter<NewsEntity, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(newsEntity: NewsEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(newsEntity.imageUrl)
                    .into(ivNews)
                tvTitle.text = newsEntity.title
                tvAuthor.text = " • ${newsEntity.author}"
                tvUpload.text = newsEntity.dueDate
            }
        }
    }

    private companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity) = oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity) = oldItem == newItem
        }
    }
}