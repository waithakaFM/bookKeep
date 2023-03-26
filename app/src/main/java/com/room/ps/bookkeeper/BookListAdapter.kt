package com.room.ps.bookkeeper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class BookListAdapter(private val context: Context,
                      private val onDeleteClickListener: OnDeleteClickListener) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    interface OnDeleteClickListener {
        fun onDeleteClickListener(myBook: Book)
    }

    private var bookList: List<Book> = mutableListOf()

    inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var pos: Int = 0

//        private val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
        private val tvBook = itemView.findViewById<TextView>(R.id.tvBook)
        private val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdated)

//        private val ivRowEdit = itemView.findViewById<ImageView>(R.id.ivRowEdit)
        private val ivRowDelete = itemView.findViewById<ImageView>(R.id.ivRowDelete)

        fun setData(book: String, isbn: String, position: Int) {
            //            tvAuthor.text = author
            tvBook.text = book
            tvLastUpdate.text = isbn
            this.pos = position
        }


        fun setListeners(){
            itemView.setOnClickListener{
                val intent = Intent(context, EditBookActivity::class.java)
                intent.putExtra("id", bookList[pos].id)
                intent.putExtra("author", bookList[pos].author)
                intent.putExtra("book", bookList[pos].book)
                intent.putExtra("description", bookList[pos].course)
                intent.putExtra("isbn", bookList[pos].isbn)

                (context as Activity).startActivityForResult(intent, MainActivity.UPDATE_BOOK_ACTIVITY_REQUEST_CODE)
            }

            ivRowDelete.setOnClickListener{
                onDeleteClickListener.onDeleteClickListener(bookList[pos])
            }
        }

//        private fun getFormattedDate(lastUpdated: Int): String{
//            var time = "Last Updated: "
//            time += lastUpdated?.let {
//                val sdf = SimpleDateFormat("HH:mm d MMM, yyyy", Locale.getDefault())
//                sdf.format(lastUpdated)
//            }?: "Not Found"
//            return time
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.book, book.isbn, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = bookList.size

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }
}