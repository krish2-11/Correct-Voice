package com.example.correctvoice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.correctvoice.Model.Model;

public class NewsArticle extends AppCompatActivity {

    TextView title , desc;
    ImageButton ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_article);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.titleofnews);
        desc = findViewById(R.id.descofnews);
        ib = (ImageButton) findViewById(R.id.bookmark);
        Model choosed = ChoosedModel.getInstance().getArticle();
        Log.d("News Article" , choosed.getTitle());
        title.setText(choosed.getTitle());
        desc.setText(choosed.getDescription());
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model bookmarked = choosed;
                DbHandler db = new DbHandler(NewsArticle.this);
                db.insertNewsDetails(bookmarked);
                Toast.makeText(NewsArticle.this,"BookMarked!1",Toast.LENGTH_SHORT).show();
            }
        });
    }
}