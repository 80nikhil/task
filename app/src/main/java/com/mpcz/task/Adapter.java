package com.mpcz.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Data> arrayList = new ArrayList<>();

    public Adapter(Context context, ArrayList<Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_list, parent, false);
        return new Adapter.ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.reponame.setText(arrayList.get(position).getRepo_name());
        holder.repolink.setText(arrayList.get(position).getRepo_link());
        holder.repodesc.setText(arrayList.get(position).getRepo_description());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= arrayList.get(position).getRepo_name()+"\n"+arrayList.get(position).getRepo_link()+"\n"+arrayList.get(position).getRepo_description() ;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reponame, repolink, repodesc;
        ImageView share;

        ViewHolder(View itemView) {
            super(itemView);
            reponame = itemView.findViewById(R.id.repo_name);
            repolink = itemView.findViewById(R.id.repo_link);
            repodesc = itemView.findViewById(R.id.repo_des);
            share = itemView.findViewById(R.id.img_share);
        }
    }
}

