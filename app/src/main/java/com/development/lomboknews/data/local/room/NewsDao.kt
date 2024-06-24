package com.development.lomboknews.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.development.lomboknews.data.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewsById(id: Int): LiveData<NewsEntity>

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE title LIKE :query || '%'")
    fun searchNews(query: String): LiveData<List<NewsEntity>>
}