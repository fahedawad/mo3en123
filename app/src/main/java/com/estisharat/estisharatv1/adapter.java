package com.estisharat.estisharatv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class adapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);


        Intent in = getIntent();
        String s = in.getStringExtra("key");


        if(s.equals("dint")){



        }




    }
}
