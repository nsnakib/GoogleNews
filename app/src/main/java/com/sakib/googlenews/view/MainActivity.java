package com.sakib.googlenews.view;

import android.os.Bundle;

import com.sakib.googlenews.R;
import com.sakib.googlenews.adapter.NewsAdapter;
import com.sakib.googlenews.model.Article;
import com.sakib.googlenews.viewmodel.MainActivityViewModel;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        refreshcontent();

    }

    void refreshcontent()
    {
        MainActivityViewModel activityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        activityViewModel.getAllArticle().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new NewsAdapter( articles, MainActivity.this));
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onRefresh() {
        refreshcontent();
    }

  void initViews()
  {
      swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
      recyclerView = findViewById(R.id.recyclerView);
  }
}
