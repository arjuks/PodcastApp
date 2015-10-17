package com.example.arjun.hw05;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder>{

    List<Podcast> pod;

    MyAdapter(List<Podcast> pod){
        this.pod = pod;
    }

    SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(pod.get(i).getTitle());
        String date = format.format(Date.parse(pod.get(i).getDate()));
        personViewHolder.date.setText(date);
        //personViewHolder.imgbtn.setImageResource(Integer.parseInt(pod.get(i).getMp3_url()));

        //personViewHolder.imgbtn.setImageURI(Uri.parse(pod.get(i).getMp3_url()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pod.size();
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        //CardView cv;
        TextView title;
        TextView date;
        ImageButton imgbtn;
        //ImageView img;


        PersonViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.Title_name);
            date = (TextView)itemView.findViewById(R.id.Date);
            //imgbtn = (ImageButton) itemView.findViewById(R.id.imageButton);
            //img = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}