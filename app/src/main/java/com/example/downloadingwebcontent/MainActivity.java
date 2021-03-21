package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
     public class DownloadTask extends AsyncTask<String , Void ,String>{

         @Override
         protected String doInBackground(String... urls) {
             String result = "";
             URL url ;
             HttpURLConnection urlConnection = null;
             try {
                 url =new URL(urls[0]);
                 urlConnection = (HttpURLConnection) url.openConnection();
                 InputStream in = urlConnection.getInputStream();
                 InputStreamReader reader = new InputStreamReader(in);
                 int data = reader.read();
                 while (data!=-1){
                     char Current = (char) data;
                     result += Current;
                     data =reader.read();
                 }
                 return result;
             }
             catch (Exception e){
                 e.printStackTrace();
                 return "Failed" ;
             }
         }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
           result = task.execute("https://github.com/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Contents of Url",result);
    }
}