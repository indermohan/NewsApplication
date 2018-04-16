package com.example.inder.newsapplication;

import com.example.inder.newsapplication.Interface.IconBetterIdeaService;
import com.example.inder.newsapplication.retrofit.IconBetterIdeaClient;
import com.example.inder.newsapplication.Interface.NewsService;
import com.example.inder.newsapplication.retrofit.RetrofitClient;

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY = "214b11e12b4648308f7e3f055bdcce29";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=214b11e12b4648308f7e3f055bdcce29
    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }


}
