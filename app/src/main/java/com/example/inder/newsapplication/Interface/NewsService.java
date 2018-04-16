package com.example.inder.newsapplication.Interface;

import com.example.inder.newsapplication.Common;
import com.example.inder.newsapplication.model.News;
import com.example.inder.newsapplication.model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)
    Call<Website> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
