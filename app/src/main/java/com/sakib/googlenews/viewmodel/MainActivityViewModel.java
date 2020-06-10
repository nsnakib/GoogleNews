package com.sakib.googlenews.viewmodel;

import android.app.Application;
import com.sakib.googlenews.model.Article;
import com.sakib.googlenews.repository.NewsRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    NewsRepository newsRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<Article>> getAllArticle(){
        return newsRepository.getMutableLiveData();
    }


}
