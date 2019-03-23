package com.estisharat.estisharatv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class brofile extends AppCompatActivity {
    ImageView profil;
    TextView name;
    TextView bio;
    String img;
    ImageView message;
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
        message = findViewById(R.id.message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(brofile.this,message_activity.class)
                        .putExtra("email",getIntent().getStringExtra("email")));
            }
        });


    }
}
