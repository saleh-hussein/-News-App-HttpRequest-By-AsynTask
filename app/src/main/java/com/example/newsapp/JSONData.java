package com.example.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSONData  {

    //declare LOG_TAG
    private static final String LOG_TAG = JSONData.class.getSimpleName();


    //receive the parameter stringUrl, then extract the data fields and return arrayList with articles
    public static ArrayList<Article> jsonExtractData(String stringUrl){

        URL url = createUrl(stringUrl);
        String jsonResponse = "";
        try {
            jsonResponse = httpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,"Error in jsonExtractData() method",e);
        }


        //declare arrayList to store the articles
        ArrayList<Article> jsonExtractedData = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(jsonResponse);
            JSONObject response = rootJson.getJSONObject("response");
            JSONArray jsonResultArray = response.getJSONArray("results");
            for(int i =0; i < jsonResultArray.length(); i++){

                JSONObject currentJsonObject = jsonResultArray.getJSONObject(i);
                String articleTitle = currentJsonObject.getString("webTitle");
                String articleSection = currentJsonObject.getString("sectionName");
                String articleDate = currentJsonObject.getString("webPublicationDate");

                jsonExtractedData.add(new Article(articleTitle,articleSection,articleDate));

            }



        } catch (JSONException e) {
           Log.e(LOG_TAG,"Error in jsonExtractData() method",e);
        }


        return jsonExtractedData;

    }


    //create url object from url String
    private static URL createUrl (String url){

        URL urlObject = null;
        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Error in creating URL Object from url string",e);
        }

        return urlObject;
    }


    //make http request and receive jsonResponse
    private static String httpRequest(URL url) throws IOException {

        //declare jsonResponse to store the our query data from the server
        String jsonResponse = "";

        //declare http connection and input stream
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {

            //prepare http connection and establish the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            //if the ResponseCode = 200 (we receive correct data), the get the data and parse it
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = parseInputStreamToString(inputStream);
            } else {
                Log.e(LOG_TAG,"response code not equal 200");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG,"Error in httpRequest method", e);
        } finally {
            //disconnect the connection
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            //close the input stream
            if (inputStream != null){
                inputStream.close();
            }
        }

       //return jsonResponse
        return jsonResponse;
    }


    // parse inputStream to string and return the output as string
    private static String parseInputStreamToString(InputStream inputStream){

        //declare stringBuilder to store the output of parsing inputStream
        StringBuilder output = new StringBuilder();

        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            try {
                String line = bufferedReader.readLine();
                while (line != null){
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error in parsing parseInputStreamToString() method",e);
            }
        }

        //return the output of parsing as string
        return output.toString() ;
    }

}
