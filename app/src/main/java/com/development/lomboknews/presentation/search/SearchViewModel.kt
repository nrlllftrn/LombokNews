package com.development.lomboknews.presentation.search

import android.app.Application
import androidx.lifecycle.ViewModel
import com.development.lomboknews.domain.NewsRepository

class SearchViewModel(application: Application) : ViewModel() {
    private val newsRepository = NewsRepository(application)

    fun searchNews(query: String) = newsRepository.searchNews(query)
}