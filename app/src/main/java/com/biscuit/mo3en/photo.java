package com.biscuit.mo3en;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class photo extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        img = findViewById(R.id.imageView);
        Picasso.get().load(getIntent().getStringExtra("img")).resize(1000,1000).into(img);
    }
}
