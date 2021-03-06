package com.biscuit.mo3en;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class rating extends Dialog {
    Context context1;
    private String id;
    DatabaseReference db;
    RatingBar ratingRatingBar;
    public rating( Context context ,String id) {
        super(context);
        this.context1 = context;
        this.id=id;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingRatingBar = findViewById(R.id.rating_rating_bar);
        Button submitButton =findViewById(R.id.submit_button);
        db = FirebaseDatabase.getInstance().getReference("agent");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        float sum_rating = Float.parseFloat(dataSnapshot.child(id).child("sum_rating").getValue(String.class));
                        int rating_count = Integer.parseInt(dataSnapshot.child(id).child("rating_count").getValue(String.class));
                        float prerating = Float.parseFloat(dataSnapshot.child(id).child("rating").getValue(String.class));
                        float new_sum_rating = sum_rating+ratingRatingBar.getRating();
                        db.child(id).child("sum_rating").setValue(new_sum_rating+"");
                        db.child(id).child("rating_count").setValue((rating_count+1)+"");
                        db.child(id).child("rating").setValue((new_sum_rating/(rating_count+1)+""));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                rating.this.cancel();
            }
        });
    }
}
