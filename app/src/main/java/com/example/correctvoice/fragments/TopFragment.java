package com.example.correctvoice.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.correctvoice.Model.AllNewsList;
import com.example.correctvoice.Model.MainNews;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.Model.NewsApiClient;
import com.example.correctvoice.Model.NewsApiService;
import com.example.correctvoice.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopFragment extends Fragment {
    ListView newsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top,container , false);
        newsList = view.findViewById(R.id.list_news);
        AllNewsList allNewsList = AllNewsList.getInstance();
        ArrayList<Model> models = allNewsList.getGeneralNews();
        NewsAdapter adapter = new NewsAdapter(getContext() , models);
        newsList.setAdapter(adapter);
        return view;
    }
}