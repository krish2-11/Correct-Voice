package com.example.correctvoice.Adapter;

import android.content.Context;
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
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        title.setText(md.getTitle());
        String imageUrl = md.getImage_url();
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.imagenotfound)        // Optional error image
                .into(image);
        Log.d("News uploading", "News is being uploaded");
        return convertView;
    }
}
