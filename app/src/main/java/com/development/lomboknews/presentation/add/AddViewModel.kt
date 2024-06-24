package com.development.lomboknews.presentation.add

import android.app.Application
import androidx.lifecycle.ViewModel
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.domain.NewsRepository

class AddViewModel(application: Application) : ViewModel() {
    private val newsRepository = NewsRepository(application)

    fun upsertNews(newsEntity: NewsEntity) = newsRepository.upsert(newsEntity)
}