package com.example.android.techblogs;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class onClickActivity extends AppCompatActivity {
    String urltorticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click);
        Intent intent = getIntent();
        String description = intent.getStringExtra("Description");
        TextView txt =(TextView)findViewById(R.id.Description);
        txt.setText(description);
        String url=intent.getStringExtra("Image");
        urltorticle=intent.getStringExtra("urltoarticle");
        ImageView img=(ImageView)findViewById(R.id.Image);
        Picasso.with( this).load(url).into(img);


    }

    public void readit(View view) {
        Uri uri = Uri.parse(urltorticle);

        // Create a new intent to view the earthquake URI
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);

        // Send the intent to launch a new activity
        startActivity(websiteIntent);
        //startActivity(browserIntent);
    }
}
