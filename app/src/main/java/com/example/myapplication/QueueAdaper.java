package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QueueAdaper extends RecyclerView.Adapter<QueueAdaper.QueueViewHolder> {
    private String[] data;
    public QueueAdaper(String[] data) {
        this.data=data;
    }

    @NonNull
    @Override
    public QueueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new QueueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueViewHolder holder, int position) {
                String title=data[position];

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class QueueViewHolder extends RecyclerView.ViewHolder{
            TextView t1,t2;
        public QueueViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.Name);
           // t2=(TextView)itemView.findViewById(R.id.address);
        }
    }
}
