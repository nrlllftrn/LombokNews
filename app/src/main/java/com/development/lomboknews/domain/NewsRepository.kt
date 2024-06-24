package com.development.lomboknews.domain

import android.app.Application
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.data.local.room.NewsDao
import com.development.lomboknews.data.local.room.NewsDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewsRepository(application: Application) {

    private val newsDao: NewsDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val dbNews = NewsDatabase.getDatabase(application)
        newsDao = dbNews.newsDao()
    }

    fun upsert(newsEntity: NewsEntity) {
        executorService.execute { newsDao.insertNews(newsEntity) }
    }

    fun getNewsById(id: Int) = newsDao.getNewsById(id)

    fun getAllNews() = newsDao.getAllNews()

    fun searchNews(query: String) = newsDao.searchNews(query)
}