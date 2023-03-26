package com.room.ps.bookkeeper

import android.app.Activity
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.room.ps.bookkeeper.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnDeleteClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val bookListAdapter = BookListAdapter(this, this)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.adapter = bookListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        bookViewModel.allBooks.observe(this, Observer{ books ->
            books?.let {
                bookListAdapter.setBooks(books)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val id = UUID.randomUUID().toString()
            val authorName = data?.getStringExtra(NewBookActivity.NEW_AUTHOR)
            val bookName = data?.getStringExtra(NewBookActivity.NEW_BOOK)
            val description = data?.getStringExtra(NewBookActivity.NEW_DESCRIPTION)
            val isbn = data?.getStringExtra(NewBookActivity.NEW_ISBN)


            val book = Book(id, authorName!!, bookName!!, description!!, isbn!!)
            bookViewModel.insert(book)

            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_LONG
            ).show()

        } else if (requestCode == UPDATE_BOOK_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val id = data!!.getStringExtra(EditBookActivity.ID)
            val authorName = data.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
            val bookName = data.getStringExtra(EditBookActivity.UPDATED_BOOK)
            val description = data.getStringExtra(EditBookActivity.UPDATED_DESCRIPTION)
            val isbn = data.getStringExtra(EditBookActivity.UPDATED_ISBN)


            val book = Book(id!!, authorName!!, bookName!!, description!!, isbn!!)

            // Code to update
            bookViewModel.update(book)

            Toast.makeText(applicationContext, R.string.updated, Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onDeleteClickListener(myBook: Book) {
        bookViewModel.delete(myBook)
        Toast.makeText(
            applicationContext,
            R.string.deleted,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.)search).actionView as SearchView

        // Setting the SearchResultActivity to show the result
        val componentName = ComponentName(this, SearchResultActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(componentName)
//        searchView.setSearchableInfo(searchableInfo)

        return true
    }

   companion object{
       private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
       const val UPDATE_BOOK_ACTIVITY_REQUEST_CODE = 2
   }

}