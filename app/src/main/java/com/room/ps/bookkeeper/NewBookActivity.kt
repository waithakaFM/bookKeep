package com.room.ps.bookkeeper

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class NewBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)


        val bAdd = findViewById<Button>(R.id.bAdd)
        val bCancel = findViewById<Button>(R.id.bCancel)
        val isbn = findViewById<TextView>(R.id.txvLastUpdated)
        val etAuthorName = findViewById<EditText>(R.id.etAuthorName)
        val etBookName = findViewById<EditText>(R.id.etBookName)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        bAdd.setOnClickListener{
            val resultIntent = Intent()

            if (TextUtils.isEmpty(etAuthorName.text)||
                    TextUtils.isEmpty(etBookName.text)){
                setResult(Activity.RESULT_CANCELED, resultIntent)
            }else{
                val author = etAuthorName.text.toString()
                val book = etBookName.text.toString()
                val description = etDescription.text.toString()
                val isbn = isbn.text.toString()

                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, book)
                resultIntent.putExtra(NEW_ISBN, isbn)

                resultIntent.putExtra(NEW_DESCRIPTION, description)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }

    companion object{
        const val NEW_AUTHOR = "new_author"
        const val NEW_BOOK = "new_book"
        const val NEW_DESCRIPTION = "new_description"
        const val  NEW_ISBN = "new isbn"
    }
}