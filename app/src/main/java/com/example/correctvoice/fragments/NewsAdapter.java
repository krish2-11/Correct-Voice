package com.example.correctvoice.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.correctvoice.Model.Model;
import com.example.correctvoice.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<Model> {
    public NewsAdapter(@NonNull Context context, @NonNull List<Model> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Model md = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
        }
        TextView title = convertView.findViewById(R.id.titlenews);
        TextView auth = convertView.findViewById(R.id.author);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        title.setText(md.getTitle());
        Log.d("News uploading", "News is being uploaded");
        return convertView;
    }


}
