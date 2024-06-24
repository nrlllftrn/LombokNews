package com.development.lomboknews.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("content")
    val content: String,

    @ColumnInfo("due_date")
    val dueDate: String,

    @ColumnInfo("image_url")
    val imageUrl: String,

    @ColumnInfo("author")
    val author: String
)
