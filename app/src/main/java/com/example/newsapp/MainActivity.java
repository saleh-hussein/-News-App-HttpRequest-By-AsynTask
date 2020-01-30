package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   //declare arrayList to store the news (Article name, article section and article date)
    private static  ArrayList<Article> data;

    private static final String   ARTICLE_REQUEST_URL ="https://content.guardianapis.com/search?api-key=182ede61-281d-438a-8a01-cf7c1c662a74" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance task object from ArticleAsyncTask class
        //ans execute task by send ARTICLE_REQUEST_URL
        ArticleAsyncTask task = new ArticleAsyncTask();
        task.execute(ARTICLE_REQUEST_URL);



        //declare adapter
        CustomAdapter adapter = new CustomAdapter(this, data);

        //declare list item
        ListView listView = (ListView) findViewById(R.id.list_view);

        //set list item with adapter
        listView.setAdapter(adapter);


    }


    //update the arrayList with article, so we can use it with out adapter
    private static void updateUi (ArrayList<Article> articles){
        data = articles;
    }


    //declare inner class ArticleAsyncTask which inherit from AsyncTask
    //input: String url
    //output: ArrayList<Article>
    private class ArticleAsyncTask extends AsyncTask<String, Void, ArrayList<Article>>{

        @Override
        protected ArrayList<Article> doInBackground(String... url) {
            //sent the string url to JSONData.jsonExtractData and
            // get the arrayList <article>, then store it in result variable
            ArrayList<Article> result = JSONData.jsonExtractData(url[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articles) {
            //receive the ArrayList<Article> from doInBackground() method and update Ui thread.
            updateUi(articles);

        }
    }
}
