package com.example.bookapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookapplication.dao.BookDAO;
import com.example.bookapplication.models.Book;

@Database(entities = Book.class, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    private static final String DB_NAME = "book_db";
    private static BookDatabase instance;

    public static synchronized BookDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration() //khong cung cap Migrations va k quan tam den du lieu cu khi update
                    .build();
        }
        return instance;
    }
    public abstract BookDAO bookDAO();
}
