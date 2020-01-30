package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends ArrayAdapter<Article> {

    //constructor
    public CustomAdapter(@NonNull Context context, @NonNull List<Article> objects) {
        super(context, 0, objects);
    }

    //getView method

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get current position
        Article currentObject = getItem(position);

        //link article title, article section and article date
        TextView articleTitle = (TextView) convertView.findViewById(R.id.title);
        TextView articleSection = (TextView) convertView.findViewById(R.id.section);
        TextView articleDate = (TextView) convertView.findViewById(R.id.date);

        //set text for article title, article section and article date
        articleTitle.setText(currentObject.getmArticleTitle());
        articleSection.setText(currentObject.getmArticleSection());
        articleDate.setText(currentObject.getmArticleDate());

        //return list item
        return convertView;
    }
}
