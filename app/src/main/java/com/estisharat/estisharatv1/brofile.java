package com.estisharat.estisharatv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class brofile extends AppCompatActivity {
    ImageView profil;
    TextView name,rating;
    TextView bio;
    String img;
    ImageView message;
    ImageView rate;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brofile);
        profil =findViewById(R.id.imgpro);
        name =findViewById(R.id.name);
        bio =findViewById(R.id.bio);
        name.setText(getIntent().getStringExtra("name"));
        bio.setText(getIntent().getStringExtra("bio"));
        img = getIntent().getStringExtra("img");
        Picasso.get().load(img).resize(500,500).into(profil);
        rate = findViewById(R.id.imageButton);
        String em = getIntent().getStringExtra("email");
        final String email1 = (em).substring(0, (em).indexOf("."));
        message = findViewById(R.id.message);
        rating = findViewById(R.id.rating);
        db = FirebaseDatabase.getInstance().getReference("agent").child(email1);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(brofile.this,message_activity.class)
                        .putExtra("email",getIntent().getStringExtra("email")));
            }
        });
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 rating.setText(dataSnapshot.child("rating").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating rating = new rating(brofile.this,email1);
                rating.show();
            }
        });



    }
}
