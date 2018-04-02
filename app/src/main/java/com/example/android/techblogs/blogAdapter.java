package com.example.android.techblogs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by BHATT on 4/2/2018.
 */

public class blogAdapter extends ArrayAdapter<blog> {

    private static final String LOG_TAG = blogAdapter.class.getSimpleName();

    public blogAdapter(Activity context, ArrayList<blog> androidFlavors) {

        super(context, 0, androidFlavors);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview, parent, false);
        }


        blog curr = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.yeah);

        nameTextView.setText(curr.getTitle());
        TextView nameTextView2 = (TextView) listItemView.findViewById(R.id.yeah1);

        nameTextView2.setText(curr.getPublished());
        String u=curr.getUrlimage();

        ImageView img=(ImageView)listItemView.findViewById(R.id.nahh);
        Picasso.with( getContext()).load(u).into(img);
       // Picasso.get().load(u).into(img);


        return listItemView;
    }

}
