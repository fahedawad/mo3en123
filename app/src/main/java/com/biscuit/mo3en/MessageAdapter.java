package com.biscuit.mo3en;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<message> messages = new ArrayList<message>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(message message) {
        this.messages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    public void removeall(){
        this.messages.clear();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) {// this message_activity was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.my_message, null);
            if (message.getText().contains(".jpg")){
                holder.img = convertView.findViewById(R.id.img);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                holder.img.setImageResource(R.drawable.image);
                holder.img.setVisibility(View.VISIBLE);
                holder.cardView = convertView.findViewById(R.id.card);
                holder.messageBody.setVisibility(View.GONE);
                Picasso.get().load(message.getText()).resize(500,500).into(holder.img);
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,photo.class);
                        intent.putExtra("img",message.getText());
                        context.startActivity(intent);
                    }
                });
                convertView.setTag(holder);
                holder.messageBody.setText(message.getText());
                holder.cardView.setVisibility(View.VISIBLE);

            }
            else {
                holder.cardView = convertView.findViewById(R.id.card);
                holder.cardView.setVisibility(View.GONE);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getText());
            }
        } else { // this message_activity was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.avatar =  convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.img = convertView.findViewById(R.id.img);
            if(!(message.getData().getImage()).isEmpty()){
                Picasso.get().load(message.getData().getImage()).resize(100,100).into(holder.avatar);}
            else {
                GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
                drawable.setColor(Color.parseColor(message.getData().getColor()));}
            if (message.getText().contains(".jpg")){
                holder.img.setVisibility(View.VISIBLE);
                Picasso.get().load(message.getText()).resize(500,500).into(holder.img);
                holder.messageBody.setVisibility(View.GONE);
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,photo.class);
                        intent.putExtra("img",message.getText());
                        context.startActivity(intent);
                    }
                });
            }
            else {
            holder.img.setVisibility(View.GONE);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
            }
            holder.name.setText("");

        }

        return convertView;
    }

}

class MessageViewHolder {
    public ImageView avatar;
    public TextView name;
    public TextView messageBody;
    public ImageView img;
    public CardView cardView;
}