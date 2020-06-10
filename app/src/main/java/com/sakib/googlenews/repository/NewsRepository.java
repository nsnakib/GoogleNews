package com.sakib.googlenews.repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.util.Log;
import com.sakib.googlenews.R;
import com.sakib.googlenews.db.DBAdapter;
import com.sakib.googlenews.model.Article;
import com.sakib.googlenews.model.News;
import com.sakib.googlenews.remote.NewsServiceApi;
import com.sakib.googlenews.remote.RetrofitClient;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    MutableLiveData<List<Article>> mutableLiveData;
    private Application application;
    private DBAdapter db;
    public final static String BASE_URL = "https://newsapi.org/v2/";

    String apiKey = String.valueOf(R.string.api_key);
    String news_url = String.format("top-headlines?country=us&category=business&apiKey=78e088eee2a74423a9e70b2b67d353de");
    //String news_url = String.format("everything?q=bitcoin&from=2020-05-09&sortBy=publishedAt&apiKey=78e088eee2a74423a9e70b2b67d353de");

    public NewsRepository(Application application)
    {
        this.application = application;
        db=new DBAdapter(application);
    }

    public MutableLiveData<List<Article>> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        NewsServiceApi serviceApi = RetrofitClient.getClient(BASE_URL).create(NewsServiceApi.class);
        serviceApi.getAllNews(news_url)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()){

                            News newsRes = response.body();
                            List<Article> articleList = newsRes.getArticles();
                           //insert to sqlite db
                            db.openDB();
                            db.clearDatabase();
                            for(int i=0;i<articleList.size();i++)
                            {
                                db.add( articleList.get(i).getTitle(), articleList.get(i).getDescription(), articleList.get(i).getUrlToImage(),articleList.get(i).getUrl());
                            }
                            db.close();

                           // retrieve data from sqlite
                            db.openDB();
                            articleList.clear();
                            Cursor c=db.getAll();

                            while (c.moveToNext())
                            {
                                int id=c.getInt(0);
                                String title=c.getString(1);
                                String description=c.getString(2);
                                String img=c.getString(3);
                                String url=c.getString(4);

                                Article p=new Article(id,title,description,img,url);
                                articleList.add(p);

                            }
                            db.close();

                            //use data from sqlite
                            mutableLiveData.setValue(articleList);
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                    }
                });
        return mutableLiveData;
    }

}
