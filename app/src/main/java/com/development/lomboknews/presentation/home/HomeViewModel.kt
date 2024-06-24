package com.development.lomboknews.presentation.home

import android.app.Application
import androidx.lifecycle.ViewModel
import com.development.lomboknews.domain.NewsRepository

class HomeViewModel(application: Application) : ViewModel() {
    private val newsRepository = NewsRepository(application)

    fun getAllNews() = newsRepository.getAllNews()
}