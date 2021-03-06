package com.biscuit.mo3en;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
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
    TextView txt3,txt4;
    ImageView img3,img4,big,big2,b3,b4;
    CardView card3,card4;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        big =findViewById(R.id.big);
        big2 = findViewById(R.id.big2);
        b3 =findViewById(R.id.big3);
        b4 = findViewById(R.id.big4);
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
        card4 = findViewById(R.id.card4);
        txt4 = findViewById(R.id.txt4);
        img4 = findViewById(R.id.img4);
        CoordinatorLayout scrollView=findViewById(R.id.scrol);
        Intent in = getIntent();
        String s = in.getStringExtra("color");

        if(s.contains("pink")){
            title.setText("إختر القسم المناسب:");
            title.setTag("Medicine");

           txt2.setText(R.string.dentistry);
            txt1.setTag("General");
            txt2.setTag("dentistry");
            big.setImageResource(R.drawable.backdoc);
            cardView1.setVisibility(View.GONE);
           img1.setImageResource(R.drawable.doctor);
           img2.setImageResource(R.drawable.tooth);
        }
        if(s.equals("blue")){
            title.setText("إختر القسم المناسب:");
            title.setTag("Technology");
            txt1.setTag("smart phones");
            txt2.setTag("computer");
            big.setImageResource(R.drawable.pacphon);
            big2.setImageResource(R.drawable.baccom);
            img1.setImageResource(R.drawable.smartphone);
            img2.setImageResource(R.drawable.responsive);

        }
        if(s.equals("royalblue")){
            title.setText("إختر القسم المناسب:");
            title.setTag("Social worker");

            txt1.setTag("Family");
            txt2.setTag("social project");

            txt3.setTag("community youth");
            card3.setVisibility(View.VISIBLE);
            img1.setImageResource(R.drawable.family);
            big.setImageResource(R.drawable.fam);
            big2.setImageResource(R.drawable.proj);
            img2.setImageResource(R.drawable.social_project);
            img3.setImageResource(R.drawable.community);
            cardView.setVisibility(View.GONE);
        }
        if(s.equals("boldgreen")){
            title.setText("إختر القسم المناسب:");
            title.setTag("Law");

            //txt2.setText(R.string.ForensicLaw);
            txt1.setTag("formal and legal Law");
            //txt2.setTag("Forensic Law");
            img1.setImageResource(R.drawable.lawco);
            cardView1.setVisibility(View.GONE);
            big.setImageResource(R.drawable.backlaw);
            //img2.setImageResource(R.drawable.lawbook);
        }
        if(s.equals("green")){
            title.setText("إختر القسم المناسب:");
            title.setTag("Educational Specialist");
            txt4.setTag("university");
            txt2.setTag("learning difficulties");
            txt3.setTag("Twjihee");
            txt1.setTag("psychological guidance");
            img1.setImageResource(R.drawable.psychologue);
            img2.setImageResource(R.drawable.books);
            big2.setImageResource(R.drawable.hardlearn);
            b3.setImageResource(R.drawable.twg);
            card3.setVisibility(View.VISIBLE);
            big.setImageResource(R.drawable.xlx);
            img3.setImageResource(R.drawable.studying);
            card4.setVisibility(View.VISIBLE);
            b4.setImageResource(R.drawable.univ);
            img4.setImageResource(R.drawable.university);
            img4.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if(s.equals("asfar")){

            title.setText(R.string.Ask);
            txt1.setText("");
            txt2.setText("");
        }
        if (cardView.getVisibility()!= View.GONE){
        cardView.setOnClickListener(this.onClickListener(R.id.card1, this,txt1.getTag().toString()));}
        if (cardView1.getVisibility()!= View.GONE){
        cardView1.setOnClickListener(this.onClickListener(R.id.card2, this, txt2.getTag().toString()));}
        if (card3.getVisibility()!= View.GONE){
        card3.setOnClickListener(this.onClickListener(R.id.card3, this, txt3.getTag().toString()));}
        if (card4.getVisibility()!= View.GONE){
        card4.setOnClickListener(this.onClickListener(R.id.card4, this, txt4.getTag().toString()));}
    }


    public View.OnClickListener onClickListener(Integer id, final Context con, final String content){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(con, adapter.class);
                in.putExtra("sub_collection", content);
                in.putExtra("collection",title.getTag().toString());
                startActivity(in);
            }
        };
    }
}
