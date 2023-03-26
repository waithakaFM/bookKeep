package com.room.ps.bookkeeper

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Book::class], version = 3)
//@TypeConverters(DataTypeConverter::class)
abstract class BookRoomDataBase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object{

        private var bookRoomInstance: BookRoomDataBase? = null

//        val MIGRATION_1_2: Migration = object : Migration(1, 2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE books "
//                + " ADD COLUMN description TEXT DEFAULT 'Add Description' "+
//                " NOT NULL ")
//            }
//
//        }
//
//        val MIGRATION_2_3: Migration = object : Migration(2, 3){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TAB   LE books "
//                        + " ADD COLUMN last_updated INTEGER DEFAULT NULL ")
//            }
//
//        }

        fun getDataBase(context: Context): BookRoomDataBase?{
            if (bookRoomInstance == null) {

                synchronized(BookRoomDataBase::class.java){
                    if (bookRoomInstance == null){
                        bookRoomInstance = Room.databaseBuilder<BookRoomDataBase>(context.applicationContext,
                        BookRoomDataBase::class.java, "crud_book")
                            .build()
                    }
                }
            }
            return bookRoomInstance
        }
    }
}