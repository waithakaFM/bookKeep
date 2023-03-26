package com.room.ps.bookkeeper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditBookActivity : AppCompatActivity() {

	var id: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_new)

		val bundle: Bundle? = intent.extras
		val bAdd = findViewById<Button>(R.id.bAdd)
		val bCancel = findViewById<Button>(R.id.bCancel)
		val etAuthorName = findViewById<TextView>(R.id.etAuthorName)
		val etBookName = findViewById<TextView>(R.id.etBookName)
		val etDescription = findViewById<TextView>(R.id.etDescription)
		val txvLastUpdated = findViewById<TextView>(R.id.txvLastUpdated)

		bundle?.let {
			id = bundle.getString("id")
			val book = bundle.getString("book")
			val author: String = bundle.getString("author").toString()
			val description: String = bundle.getString("description").toString()
			val isbn: String? = bundle.getString("isbn")

			etAuthorName.text = author
			etBookName.text = book
			etDescription.text = description
			txvLastUpdated.text = isbn
		}

		bAdd.setOnClickListener {
			val updatedAuthor: String = etAuthorName.text.toString()
			val updatedBook: String = etBookName.text.toString()
			val updatedIsbn: String = etBookName.text.toString()

			val updatedDescription: String = etDescription.text.toString()

			val resultIntent = Intent()
			resultIntent.putExtra(ID, id)
			resultIntent.putExtra(UPDATED_AUTHOR, updatedAuthor)
			resultIntent.putExtra( UPDATED_BOOK, updatedBook)
			resultIntent.putExtra( UPDATED_ISBN, updatedIsbn)

			resultIntent.putExtra( UPDATED_DESCRIPTION, updatedDescription)
			setResult(Activity.RESULT_OK, resultIntent)

			finish()
		}

		bCancel.setOnClickListener {
			finish()
		}
	}

	companion object {
		const val ID = "book_id"
		const val UPDATED_AUTHOR = "author_name"
		const val UPDATED_BOOK = "book_name"
		const val UPDATED_DESCRIPTION = "description"
		const val UPDATED_ISBN = "isbn"
	}
}