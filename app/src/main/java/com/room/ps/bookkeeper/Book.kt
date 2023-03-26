package com.room.ps.bookkeeper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "books")
class Book(@PrimaryKey val id: String,

           @ColumnInfo(name = "author")
           val author: String,

           val book: String,

           val course: String,

           @ColumnInfo(name = "isbn")
           val isbn: String
           )