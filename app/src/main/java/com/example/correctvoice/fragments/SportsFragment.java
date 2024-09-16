package com.example.correctvoice.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.correctvoice.ChoosedModel;
import com.example.correctvoice.Model.AllNewsList;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.NewsArticle;
import com.example.correctvoice.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SportsFragment extends Fragment {
    ListView newsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports,container , false);
        newsList = view.findViewById(R.id.list_news);
        AllNewsList allNewsList = AllNewsList.getInstance();
        ArrayList<Model> models = allNewsList.getSportsNews();
        NewsAdapter adapter = new NewsAdapter(getContext() , models);
        newsList.setAdapter(adapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Model selected = (Model) adapterView.getItemAtPosition(i);
                ChoosedModel.getInstance().setArticle(selected);
                Toast.makeText(getContext(),selected.getDescription().length() + "",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext() , NewsArticle.class));
            }
        });
        return view;
    }
}