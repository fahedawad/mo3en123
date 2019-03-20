package com.estisharat.estisharatv1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class action extends AppCompatActivity {

    TextView title;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    ImageView img3;
    CardView card3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        title = findViewById(R.id.title);
        txt1 =findViewById(R.id.txt1);
        txt3 =findViewById(R.id.txt3);
        txt2 =findViewById(R.id.txt2);

        ImageView img1 =findViewById(R.id.img1);
        img3 =findViewById(R.id.img3);
        ImageView img2 =findViewById(R.id.img2);

        CardView cardView =findViewById(R.id.card1);
        CardView cardView1 =findViewById(R.id.card2);
        card3 =findViewById(R.id.card3);


        ScrollView scrollView=findViewById(R.id.scrol);
        Intent in = getIntent();
        String s = in.getStringExtra("color");

        if(s.contains("pink")){

            title.setText(R.string.Medicine);
            txt1.setText(R.string.General);
           txt2.setText(R.string.dentistry);
           img1.setImageResource(R.drawable.doctor);
            img2.setImageResource(R.drawable.tooth);
        }
        if(s.equals("blue")){


            title.setText(R.string.Technology);
            txt1.setText(R.string.smartphones);
            txt2.setText(R.string.computer);
            img1.setImageResource(R.drawable.smartphone);
            img2.setImageResource(R.drawable.responsive);

        }
        if(s.equals("royalblue")){


            title.setText(R.string.Socialworker);
            txt1.setText(R.string.Famely);
            txt2.setText(R.string.Child);
            img1.setImageResource(R.drawable.family);
            img2.setImageResource(R.drawable.baby);
        }
        if(s.equals("boldgreen")){


            title.setText(R.string.Law);
            txt1.setText(R.string.CommercialLaw);
            txt2.setText(R.string.ForensicLaw);
            img1.setImageResource(R.drawable.lawco);
            img2.setImageResource(R.drawable.lawbook);

        }
        if(s.equals("green")){


            title.setText(R.string.EducationalSpecialist);
            txt1.setText(R.string.Learning);
            txt2.setText(R.string.learningdifficulties);
            img1.setImageResource(R.drawable.classroom);
            img2.setImageResource(R.drawable.books);
            card3.setVisibility(View.VISIBLE);
            txt3.setText(R.string.Twjihee);
            img3.setImageResource(R.drawable.studying);
        }
        if(s.equals("asfar")){

            title.setText(R.string.Ask);
            txt1.setText("");
            txt2.setText("");
        }


        cardView.setOnClickListener(this.onClickListener(R.id.card1, this, "dint"));
        cardView1.setOnClickListener(this.onClickListener(R.id.card2, this, "general"));

    }


    public View.OnClickListener onClickListener(Integer id, final Context con, final String content){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(con, adapter.class);
                in.putExtra("key", content);
                startActivity(in);
            }
        };
    }

}
