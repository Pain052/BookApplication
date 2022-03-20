package com.example.bookapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookapplication.R;
import com.example.bookapplication.models.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private Context context;
    private List<Book> mList;

    public BookAdapter(Context context, List<Book> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setList(List<Book> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_book, viewGroup, false);
        }
        //get item to be displayed
        Book book = (Book) getItem(i);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvContent = view.findViewById(R.id.tvContent);
        //sets the text for title and content from the current item object
        tvTitle.setText(book.getTitle());
        tvContent.setText(book.getContent());

        //returns the view for the current now
        return view;
    }
}
