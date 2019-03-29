package com.estisharat.estisharatv1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class message_activity extends AppCompatActivity {
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    MemberData data,memberData;
    static long countmessage;
    Calendar calander ;
    DatabaseReference dbr;
    FirebaseDatabase db;
    Uri filepath;
    private StorageReference mStorageRef;
    static String sender,email1;
    static Bitmap bitmap1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        calander = Calendar.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        editText = (EditText) findViewById(R.id.editText);
        data= new MemberData(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRandomColor());
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        String em = getIntent().getStringExtra("email");
        email1 = (em).substring(0, (em).indexOf("."));
        sender = FirebaseAuth.getInstance().getCurrentUser().getUid()+"";
        dbr= FirebaseDatabase.getInstance().getReference("chat").child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                +"_"+email1);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    messageAdapter.removeall();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        countmessage = dataSnapshot.getChildrenCount();
                        boolean belong = (ds.getKey().substring(ds.getKey().indexOf("@")+1)).equals(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        String mesaage = ds.getValue(String.class);
                        message message = new message(mesaage,data,belong);
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
        int cday = calander.get(Calendar.DAY_OF_MONTH);
        if (cSecond<10){
            time= cday +"_"+cHour+"_"+cMinute+"_0"+cSecond;
        }
        else {
            time= cday +"_"+cHour+"_"+cMinute+"_0"+cSecond;
        }
        if (message.length() > 0) {
            if(countmessage<1){
                dbr.child(time+"@"+data.getName()).setValue(message);
                com.estisharat.estisharatv1.message messag = new message(message,data,true);
                messageAdapter.add(messag);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
                editText.getText().clear();
                //countmessage++;
            }
            else {
                dbr.child(time+"@"+data.getName()).setValue(message);
                com.estisharat.estisharatv1.message messag = new message(message,data,true);
                messageAdapter.add(messag);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
                editText.getText().clear();
                //countmessage++;
            }
        }
    }

    public void attach(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null ){
            filepath = data.getData();
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            final StorageReference profilepic = mStorageRef.child("chat/"+sender + "_"+email1+".jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            final byte[] data1 = baos.toByteArray();
            UploadTask uploadTask = profilepic.putBytes(data1);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return profilepic.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        String time;
                        calander = Calendar.getInstance();
                        int cHour = calander.get(Calendar.HOUR);
                        int cMinute = calander.get(Calendar.MINUTE);
                        int cSecond = calander.get(Calendar.SECOND);
                        int cday = calander.get(Calendar.DAY_OF_MONTH);
                        if (cSecond<10){
                            time= cday +"_"+cHour+"_"+cMinute+"_0"+cSecond;
                        }
                        else {
                            time= cday +"_"+cHour+"_"+cMinute+"_0"+cSecond;
                        }
                        memberData =  new MemberData(FirebaseAuth.getInstance().getCurrentUser().getUid(), getRandomColor());
                        dbr.child(time+"@"+memberData.getName()).setValue(task.getResult()+"");
                        message messag = new message(task.getResult()+"",memberData,true);
                        messageAdapter.add(messag);
                        // scroll the ListView to the last added element
                        messagesView.setSelection(messagesView.getCount() - 1);
                        editText.getText().clear();
                        //countmessage++;
                    }

                }
            });


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