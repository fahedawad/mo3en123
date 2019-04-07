package com.biscuit.mo3en;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class adapter extends AppCompatActivity {
    FirebaseFirestore db;
    public static View.OnClickListener myOnClickListener;
    private String TAG = "mosab";
    ArrayList<info> arrayList;
    adapteragent adapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    String name,img;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        databaseReference = FirebaseDatabase.getInstance().getReference("agent");
        db.collection("agent")
                .document(getIntent().getStringExtra("collection"))
                .collection(getIntent().getStringExtra("sub_collection"))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                arrayList.add(new info(document.get("email")+"",
                                        document.get("name")+"",
                                        document.get("img")+"",
                                        document.get("bio")+""));
                                adapter = new adapteragent(arrayList);
                                recyclerView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}