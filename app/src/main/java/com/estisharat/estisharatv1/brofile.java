package com.estisharat.estisharatv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class brofile extends AppCompatActivity {
    ImageView profil;
    TextView name;
    TextView bio;
    String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brofile);
        profil =findViewById(R.id.imgpro);
        name =findViewById(R.id.name);
        bio =findViewById(R.id.bio);
        name.setText("Name : "+getIntent().getStringExtra("name"));
        bio.setText("Bio : "+getIntent().getStringExtra("bio"));
        img = getIntent().getStringExtra("img");
        new ImageLoadTask(img, profil).execute();


    }
}
