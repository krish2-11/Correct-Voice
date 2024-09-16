package com.example.correctvoice.appPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;

import com.example.correctvoice.database.DbHandler;
import com.example.correctvoice.Model.ChoosedModel;
import com.example.correctvoice.Model.Model;
import com.example.correctvoice.R;
import com.squareup.picasso.Picasso;

public class NewsArticle extends AppCompatActivity {

    TextView title , desc;
    ImageButton ib;
    ImageView imagenews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_article);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        title = findViewById(R.id.titleofnews);
        desc = findViewById(R.id.descofnews);
        ib = (ImageButton) findViewById(R.id.bookmark);
        imagenews = findViewById(R.id.imagenews);
        Model choosed = ChoosedModel.getInstance().getArticle();
        Log.d("News Article" , choosed.getTitle());
        String Title = choosed.getTitle();
        String Description = choosed.getDescription();
        String imageurl = choosed.getImage_url();
        if(Title == null){
            title.setText("Title not found!!");
        }
        else{
            title.setText(Title);
        }
        if(Description == null){
            desc.setText("Description not found!!");
        }
        else{
            desc.setText(Description);
        }
        Picasso.get()
                .load(imageurl)
                .error(R.drawable.imagenotfound)        // Optional error image
                .into(imagenews);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model bookmarked = choosed;
                DbHandler db = new DbHandler(NewsArticle.this);
                db.insertNewsDetails(bookmarked);
                Toast.makeText(NewsArticle.this,"BookMarked!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}