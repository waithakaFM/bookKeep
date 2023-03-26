package com.room.ps.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

//    @Query("SELECT * FROM books")
//    fun getAllBooks(): LiveData<List<Book>>

    @get:Query("SELECT*FROM books")
    val allBooks: LiveData<List<Book>>


    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)
}