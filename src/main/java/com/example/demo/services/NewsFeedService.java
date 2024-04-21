package com.example.demo.services;

import com.example.demo.client.INewsClient;
import com.example.demo.dto.NewsResponse;
import javafx.application.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class NewsFeedService {
    private final INewsClient client;
    public NewsFeedService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://017b4a607ffb41e3819e50020e2b9dc1.api.mockbin.io/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        this.client = retrofit.create(INewsClient.class);
    }


    public void loadData(Callable<Void> actionToBeCalled) {
        Call<NewsResponse> responseCall = this.client.getNews();
        responseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse data = response.body();
                assert data != null;
                System.out.println("Data received from API, total results " + data.getTotalResults());
                try {
                    actionToBeCalled.call();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable throwable) {
                System.out.println("Error occured getting data " + throwable.getMessage());
            }
        });


    }
}
