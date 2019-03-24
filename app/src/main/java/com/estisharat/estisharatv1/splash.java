package com.estisharat.estisharatv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView img=findViewById(R.id.img);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.anim);
        img.setAnimation(anim);

        Thread thread= new Thread(){
            @Override
            public void run() {
                try{
                sleep(2500);
                Intent intent =new Intent(getApplicationContext(),auth.class);
                startActivity(intent);
                }


                catch (InterruptedException e){e.printStackTrace();}
                finish();
            }
        };thread.start();

    }
}
