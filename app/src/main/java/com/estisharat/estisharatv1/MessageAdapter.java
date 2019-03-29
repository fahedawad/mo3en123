package com.estisharat.estisharatv1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) {// this message_activity was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.my_message, null);
            if (message.getText().contains(".jpg")){
                holder.img = convertView.findViewById(R.id.img);
                holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
                holder.cardView = convertView.findViewById(R.id.card);
                holder.messageBody.setVisibility(View.GONE);
                new ImageLoadTask(message.getText(),holder.img);
                convertView.setTag(holder);
                holder.messageBody.setText(message.getText());
                holder.img.setVisibility(View.VISIBLE);
                holder.cardView.setVisibility(View.VISIBLE);
                Picasso.get().load(message.getText()).resize(500,500).into(holder.img);
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
            holder.avatar = (View) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.img = convertView.findViewById(R.id.img);
            if (message.getText().contains(".jpg")){
                holder.img.setVisibility(View.VISIBLE);
                Picasso.get().load(message.getText()).resize(500,500).into(holder.img);
                holder.messageBody.setVisibility(View.GONE);
            }
            else {
            holder.img.setVisibility(View.GONE);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
            }
            holder.name.setText("");
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getData().getColor()));
        }

        return convertView;
    }

}

class MessageViewHolder {
    public View avatar;
    public TextView name;
    public TextView messageBody;
    public ImageView img;
    public CardView cardView;
}