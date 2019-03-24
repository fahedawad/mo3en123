package com.estisharat.estisharatv1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapteragent extends RecyclerView.Adapter<adapteragent.MyViewHolder> {
    ArrayList<info> dataset;
    Context context;

    public adapteragent(ArrayList<info> dataset) {
        this.dataset = dataset;
    }

    public ArrayList<info> getDataset() {
        return dataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent,parent,false);
        view.setOnClickListener(adapter.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        context = parent.getContext();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        TextView name = myViewHolder.name;
        ImageView imageView = myViewHolder.imageView;
        CardView cardView = myViewHolder.card;
        name.setText(dataset.get(i).getName());
        Picasso.get().load(dataset.get(i).getImg()).resize(300,300).into(imageView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,brofile.class);
                intent.putExtra("name",dataset.get(i).getName());
                intent.putExtra("img",dataset.get(i).getImg());
                intent.putExtra("bio",dataset.get(i).getBio());
                intent.putExtra("email",dataset.get(i).getEmail());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return (dataset == null) ? 0 : dataset.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.txt1);
            this.imageView = itemView.findViewById(R.id.img1);
            this.card = itemView.findViewById(R.id.card1);
        }

    }
}
