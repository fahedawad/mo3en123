package com.estisharat.estisharatv1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class message_activity extends AppCompatActivity {
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    MemberData data;
    int countmessage=0;
    Calendar calander ;
    DatabaseReference dbr;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        calander = Calendar.getInstance();
        editText = (EditText) findViewById(R.id.editText);
        data= new MemberData(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRandomColor());
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        String em = getIntent().getStringExtra("email");
        String email1 = (em).substring(0, (em).indexOf("."));
        dbr= FirebaseDatabase.getInstance().getReference("chat").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                +"_"+email1);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    messageAdapter.removeall();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        boolean belong = (ds.getKey().substring(ds.getKey().indexOf("@")+1)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        String mesaage = ds.getValue(String.class);
                        Message message = new Message(mesaage,data,belong);
                        messageAdapter.add(message);
                        messagesView.setSelection(messagesView.getCount() - 1);}

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    public void sendMessage(View view) {
        final String message = editText.getText().toString();
        String time;
        calander = Calendar.getInstance();
        int cHour = calander.get(Calendar.HOUR);
        int cMinute = calander.get(Calendar.MINUTE);
        int cSecond = calander.get(Calendar.SECOND);
        if (cSecond<10){
            time= cHour+"_"+cMinute+"_0"+cSecond;
        }
        else {
            time= cHour+"_"+cMinute+"_"+cSecond;
        }
        if (message.length() > 0) {
            if(countmessage<1){
                dbr.child(time+"@"+data.getName()).setValue(message);
                Message messag = new Message(message,data,true);
                messageAdapter.add(messag);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
                editText.getText().clear();
                countmessage++;
            }
            else {
                dbr.child(time+"@"+data.getName()).setValue(message);
                Message messag = new Message(message,data,true);
                messageAdapter.add(messag);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
                editText.getText().clear();
                countmessage++;
            }
        }
    }
}

class MemberData {
    private String name;
    private String color;
    public MemberData(String name, String color) {
        this.name = name;
        this.color = color;
    }
    public MemberData() {
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
}