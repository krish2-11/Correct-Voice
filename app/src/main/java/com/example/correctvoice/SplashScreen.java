package com.example.correctvoice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.correctvoice.Model.AllNewsList;
import com.example.correctvoice.Model.MainNews;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.Model.NewsApiClient;
import com.example.correctvoice.Model.NewsApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends AppCompatActivity {

    private String API_KEY = "0303163d028c4dd295d64e946d81dc92";
    private static int SPLASH_TIME_OUT = 3000;
    Call<MainNews> call;
    NewsApiService apiService;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);
        iv = (ImageView)findViewById(R.id.imageView);
        Animation anime = AnimationUtils.loadAnimation(SplashScreen.this ,R.anim.fade_in);
        iv.startAnimation(anime);

        loadNewsLists();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent mainIntent;
                if(user != null){
                    mainIntent = new Intent(getApplicationContext(), Home.class);
                }
                else{
                    mainIntent = new Intent(getApplicationContext(), Login.class);
                }
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void loadNewsLists() {
        apiService = NewsApiClient.getClient().create(NewsApiService.class);
        String[] Category = { "General" , "Business" , "Entertainment" , "Health" , "Science" , "Sports" , "Technology" };
        for(String selectedCategory : Category ){
            call = apiService.getTopHeadlines("us", selectedCategory ,  API_KEY);
            loadModel(selectedCategory);
        }
    }

    private void loadModel(String selectedcategory) {
            call.enqueue(new Callback<MainNews>() {
                @Override
                public void onResponse(@NonNull Call<MainNews> call, @NonNull Response<MainNews> response) {
                    if (!response.isSuccessful() && response.errorBody() != null) {
                        try {
                            Log.e("News", "Error Body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        List<Model> articles = response.body().getArticles();
                        ArrayList<Model> models = new ArrayList<>(articles);
                        Log.d("News", "Fetched");
                        AllNewsList allNewsList = AllNewsList.getInstance();
                        switch (selectedcategory){
                            case "General" :
                                allNewsList.setGeneralNews(models);
                                break;
                            case "Business" :
                                allNewsList.setBusinessNews(models);
                                break;
                            case "Entertainment" :
                                allNewsList.setEntertaimentNews(models);
                                break;
                            case "Health" :
                                allNewsList.setHealthNews(models);
                                break;
                            case "Science" :
                                allNewsList.setScienceNews(models);
                                break;
                            case "Sports" :
                                allNewsList.setSportsNews(models);
                                break;
                            case "Technology" :
                                allNewsList.setTechnologyNews(models);
                        }
                    } else {
                        Log.e("News", "Failed to fetch news");
                    }
                }

                @Override
                public void onFailure(Call<MainNews> call, Throwable t) {
                    Log.e("News", "Error: " + t.getMessage());
                }
            });
    }
}
