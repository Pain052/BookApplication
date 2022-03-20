package com.example.bookapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookapplication.models.Book;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void addBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT * FROM book")
    List<Book> getAllBook();

    @Query("SELECT * FROM book WHERE title= :title")
    List<Book> checkBook(String title);

    @Query("SELECT * FROM book WHERE title LIKE '%' || :title || '%'")
    List<Book> searchBook(String title);
}
