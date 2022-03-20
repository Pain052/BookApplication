package com.example.bookapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookapplication.adapter.BookAdapter;
import com.example.bookapplication.database.BookDatabase;
import com.example.bookapplication.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    private ListView lvBookList;
    private BookAdapter bookAdapter;
    public List<Book> mList;
    private EditText edSearchBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //init array to save data
        mList = new ArrayList<>();

        setAdapterLv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mList = BookDatabase.getInstance(this).bookDAO().getAllBook();
        bookAdapter.setList(mList);
    }

    private void initView(){
        //mapping xml
        lvBookList = findViewById(R.id.lvBookList);
        edSearchBook = findViewById(R.id.edSearchBook);
    }

    private void setAdapterLv(){
        //instantiate the book list adapter
        mList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, mList);
        bookAdapter.notifyDataSetChanged();
        lvBookList.setAdapter(bookAdapter);
        //handle click item listview
        lvBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = (Book) lvBookList.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, UpdateBookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Book_obj", book);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
    }

    public void goToAddBook(View view){
        startActivity(new Intent(MainActivity.this, AddBookActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE && requestCode == Activity.RESULT_OK){
            setAdapterLv();
        }
    }

    public void searchBook(View view) {
        String strSearch = edSearchBook.getText().toString().trim();
        mList = BookDatabase.getInstance(this).bookDAO().searchBook(strSearch);

        bookAdapter = new BookAdapter(this, mList);
        lvBookList.setAdapter(bookAdapter);
    }
}