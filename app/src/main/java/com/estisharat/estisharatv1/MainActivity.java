package com.estisharat.estisharatv1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    CardView medicine;
    CardView tech;
    CardView social;
    CardView law;
    CardView leran;
    CardView help;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    RelativeLayout relativeLayout;
    TextView name;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.DrawerLayout);
        relativeLayout = findViewById(R.id.rel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                relativeLayout.setLayoutParams(layoutParams);
            }
            @Override
            public void onDrawerOpened(@NonNull View view) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                relativeLayout.setLayoutParams(layoutParams);
            }
            @Override
            public void onDrawerClosed(@NonNull View view) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        170);
                relativeLayout.setLayoutParams(layoutParams);
            }
            @Override
            public void onDrawerStateChanged(int i) {
            }
        });
        View view = navigationView.getHeaderView(0);
        name =  view.findViewById(R.id.name);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        imageView = view.findViewById(R.id.image);
        System.out.println(intent.getStringExtra("image"));
        Picasso.get().load(intent.getStringExtra("image")).into(imageView);
        medicine = findViewById(R.id.mid);
        tech = findViewById(R.id.tech);
        social=findViewById(R.id.social);
        law=findViewById(R.id.law);
        leran=findViewById(R.id.leran);
        help=findViewById(R.id.help);
        medicine.setOnClickListener(this.onClickListener(R.id.mid, this, "pink"));
        tech.setOnClickListener(this.onClickListener(R.id.tech, this, "blue"));
        social.setOnClickListener(this.onClickListener(R.id.social, this, "royalblue"));
       law.setOnClickListener(this.onClickListener(R.id.law, this, "boldgreen"));
        leran.setOnClickListener(this.onClickListener(R.id.leran, this, "green"));
        help.setOnClickListener(this.onClickListener(R.id.help, this, "asfar"));




    }

    public View.OnClickListener onClickListener( Integer id,  final Context con, final String content){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent in = new Intent(con, action.class);
                    in.putExtra("color", content);
                    startActivity(in);
            }
        };
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,auth.class));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true ;
    }
}

