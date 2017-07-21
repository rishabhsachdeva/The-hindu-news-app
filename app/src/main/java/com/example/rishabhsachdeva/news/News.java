package com.example.rishabhsachdeva.news;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
public class News extends AppCompatActivity  {

    ProgressDialog progress;
    GridView gv;
    public  ArrayList<String> prgmNameList;
    public  ArrayList<String> prgmNameList1;
    public ArrayList<String> prgmImages;
    public ArrayList<String> prgmUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(this,  ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
        progress.setMessage("Loading Badges :) ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

        gv=(GridView) findViewById(R.id.gridView1);


        prgmNameList = new ArrayList<>();
        prgmNameList1 = new ArrayList<>();
        prgmImages=new ArrayList<String>();
        prgmUrl=new ArrayList<String>();
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//       // mSwipeRefreshLayout.setColorScheme(
////                R.color.blue, R.color.colorMaroon,
////                R.color.green, R.color.orange);

        beforenews();
    }
public void beforenews() {
    AsyncHttpClient client = new AsyncHttpClient();
    String s = "https://newsapi.org/v1/articles?source=the-hindu&sortBy=top&apiKey=9136309106da49fdac24815248433fc7";
    client.get(this, s, new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            Log.e("Response Shushant GET: ", response.toString());

            try {
                news(response);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);

            progress.hide();
        }
    });
}
    public void news(JSONObject response){
//        mSwipeRefreshLayout.setRefreshing(false);
                try {

                    JSONObject baseJsonResponse = new JSONObject(String.valueOf(response));

                    JSONArray newsArray = baseJsonResponse.getJSONArray("articles");

                    for (int i = 0; i < newsArray.length(); i++) {

                        JSONObject currentNews = newsArray.getJSONObject(i);

                        String title = currentNews.getString("title");

                        String description = currentNews.getString("description");

                        String urlToImage=currentNews.getString("urlToImage");
                        String urltonews=currentNews.getString("url");

                        prgmNameList.add(title);
                        prgmNameList1.add(description);
                        prgmImages.add(urlToImage);
                        prgmUrl.add(urltonews);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        progress.hide();
        gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmNameList1,prgmImages,prgmUrl));
    }

//    @Override
//    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
//
//        // Simulate a long running activity
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                beforenews();
//            }
//        }, 5000);
//    }
}