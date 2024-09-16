package com.example.correctvoice.appPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.correctvoice.Model.ChoosedModel;
import com.example.correctvoice.database.DbHandler;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.Adapter.NewsAdapter;
import com.example.correctvoice.R;

import java.util.ArrayList;

public class BookMarkedNews extends AppCompatActivity {

    ListView newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_marked_news);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        newsList = findViewById(R.id.newslist);
        DbHandler db = new DbHandler(BookMarkedNews.this);
        ArrayList<Model> models = db.GetAllNews();
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Model selected = (Model) adapterView.getItemAtPosition(i);
                ChoosedModel.getInstance().setArticle(selected);
                startActivity(new Intent(BookMarkedNews.this, NewsArticle.class));
            }
        });
        NewsAdapter adapter = new NewsAdapter(BookMarkedNews.this , models);
        newsList.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        onBackPressed();
        return true;
    }
}