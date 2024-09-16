package com.example.correctvoice.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

    // Retrofit Interface
    public interface NewsApiService {
        @GET("news")
        Call<MainNews> getNews(
                @Query("apikey") String apiKey,
                @Query("category") String category,
                @Query("language") String language
        );
    }
