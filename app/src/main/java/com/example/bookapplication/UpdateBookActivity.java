package com.example.bookapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.bookapplication.database.BookDatabase;
import com.example.bookapplication.models.Book;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateBookActivity extends AppCompatActivity {
    private TextInputEditText etTitle, etContent;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        Intent intent = getIntent();
        mBook = (Book) getIntent().getExtras().get("Book_obj");

        initView();
    }

    public void initView(){
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);

        //set date on edit text
        if (mBook != null){
            etTitle.setText(mBook.getTitle());
            etContent.setText(mBook.getContent());
        }
    }


    public void backHomeFromUpdate(View view) {
        startActivity(new Intent(UpdateBookActivity.this, MainActivity.class));
    }

    public void updateBook(View view) {
        String strTitle = etTitle.getText().toString().trim();
        String strContent = etContent.getText().toString().trim();

        //Checking for validity
        if (TextUtils.isEmpty(strTitle) || TextUtils.isEmpty(strContent)){
            return;
        }

        //set data for obj
        mBook.setTitle(strTitle);
        mBook.setContent(strContent);

        BookDatabase.getInstance(this).bookDAO().updateBook(mBook);
//        Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show();
//
//        Intent intentResult = new Intent();
//        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

    public void removeBook(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("Are you sure to delete the book?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Delete Book
                        BookDatabase.getInstance(UpdateBookActivity.this).bookDAO().deleteBook(mBook);
                        Toast.makeText(UpdateBookActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateBookActivity.this, MainActivity.class));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}