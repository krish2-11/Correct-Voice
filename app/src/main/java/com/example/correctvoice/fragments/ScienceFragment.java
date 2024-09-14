package com.example.correctvoice.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.correctvoice.Model.AllNewsList;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScienceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScienceFragment extends Fragment {
    ListView newsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_science,container , false);
        newsList = view.findViewById(R.id.list_news);
        AllNewsList allNewsList = AllNewsList.getInstance();
        ArrayList<Model> models = allNewsList.getScienceNews();
        NewsAdapter adapter = new NewsAdapter(getContext() , models);
        newsList.setAdapter(adapter);
        return view;
    }
}