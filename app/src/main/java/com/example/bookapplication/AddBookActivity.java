package com.example.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookapplication.database.BookDatabase;
import com.example.bookapplication.models.Book;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    private TextInputEditText etTitle, etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initView();

    }

    public void initView(){
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
    }

    public void saveBook(View view){
        String strTitle = etTitle.getText().toString().trim();
        String strContent = etContent.getText().toString().trim();

        //Checking for validity
        if (TextUtils.isEmpty(strTitle) || TextUtils.isEmpty(strContent)){
            return;
        }

        Book book = new Book(strTitle, strContent);

        //Check book exist
        if (isBookExist(book)){
            Log.e("DATA_EXIST", "====>" + isBookExist(book));
            Toast.makeText(AddBookActivity.this, "Book exist", Toast.LENGTH_SHORT).show();
            return;
        }

        BookDatabase.getInstance(this).bookDAO().addBook(book);
        Toast.makeText(AddBookActivity.this, "Add book successfully", Toast.LENGTH_SHORT).show();

        etTitle.setText("");
        etContent.setText("");
    }

    public void backHome(View view){
        startActivity(new Intent(AddBookActivity.this, MainActivity.class));
    }

    private boolean isBookExist(Book book){
        List<Book> list = BookDatabase.getInstance(this).bookDAO().checkBook(book.getTitle());
        return list != null && !list.isEmpty();
    }
}