package com.example.bookapplication.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "book")
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int book_id;

    @ColumnInfo(name = "book_description") // column name will be "book_description" instead of "content" in table
    private String content;

    private String title;

    @Ignore
    public Book() {
    }

    @Ignore
    public Book(int book_id, String title) {
        this.book_id = book_id;
        this.title = title;
    }

    @Ignore
    public Book(int book_id, String content, String title) {
        this.book_id = book_id;
        this.content = content;
        this.title = title;
    }

    public Book(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Note{" +
                "book_id=" + book_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
