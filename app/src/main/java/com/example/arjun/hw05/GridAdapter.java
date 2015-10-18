package com.example.arjun.hw05;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 28/02/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<Podcast> podlist;

    GridAdapter(List<Podcast> pod) {
        this.podlist = pod;
        //context = context.getApplicationContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = MainActivity.rv.getChildPosition(v);
                String title = podlist.get(pos).getTitle();
                Log.d("demo", "item clicked" + " " + title);
                String desc = podlist.get(pos).getDescription();
                String date = podlist.get(pos).getDate();
                String duration = podlist.get(pos).getDuration();
                String img = podlist.get(pos).getImg_url();
                String mp3 = podlist.get(pos).getMp3_url();

                Podcast p = new Podcast(title,desc,date,img,duration,mp3);
                Intent intent = new Intent(MainActivity.getContext(),PlayActivity.class);
                intent.putExtra(MainActivity.PODOBJ, p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.getContext().startActivity(intent);

            }
        });

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Podcast plist = podlist.get(i);
        viewHolder.title.setText(plist.getTitle());
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return podlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            //imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            title = (TextView)itemView.findViewById(R.id.titleName);
        }
    }
}
