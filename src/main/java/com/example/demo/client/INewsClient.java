package com.example.demo.client;

import com.example.demo.dto.NewsResponse;
import com.example.demo.dto.NewsResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface INewsClient {
    @GET("/")
    Call<NewsResponse> getNews();
}
