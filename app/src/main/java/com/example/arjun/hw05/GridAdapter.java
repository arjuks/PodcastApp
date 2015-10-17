package com.example.arjun.hw05;

import android.support.v7.widget.RecyclerView;
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

//    public GridAdapter() {
//        super();
//        mItems = new ArrayList<Podcast>();
//        EndangeredItem species = new EndangeredItem();
//        species.setName("Amur Leopard");
//        species.setThumbnail(R.drawable.butterfly);
//        mItems.add(species);
//
//        species = new EndangeredItem();
//        species.setName("Black Rhino");
//        species.setThumbnail(R.drawable.butterfly);
//        mItems.add(species);
//
//        species = new EndangeredItem();
//        species.setName("Orangutan");
//        species.setThumbnail(R.drawable.butterfly);
//        mItems.add(species);
//
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
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
