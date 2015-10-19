package com.example.arjun.hw05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {
    private List<Podcast> pod;
    private Context callingContext;
    public MediaPlayer player = new MediaPlayer();
    private SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");

    public MyAdapter(Context context, List<Podcast> pod){
        this.pod = pod;
        this.callingContext = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = MainActivity.rv.getChildPosition(v);
                String title = pod.get(pos).getTitle();
                Log.d("demo", "item clicked"+" "+title);
                String desc = pod.get(pos).getDescription();
                String date = pod.get(pos).getDate();
                String duration = pod.get(pos).getDuration();
                String img = pod.get(pos).getImg_url();
                String mp3 = pod.get(pos).getMp3_url();

                Podcast p = new Podcast(title, desc, date, img, duration, mp3);
                Intent intent = new Intent(MainActivity.getContext(), PlayActivity.class);
                intent.putExtra(MainActivity.PODOBJ, p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.getContext().startActivity(intent);
            }
        });

        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
        personViewHolder.title.setText(pod.get(i).getTitle());
        String date = format.format(Date.parse(pod.get(i).getDate()));

        personViewHolder.date.setText(date);
        Picasso.with(callingContext).load(pod.get(i).getImg_url()).resize(100, 100).centerCrop().into(personViewHolder.img);
        personViewHolder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProgressDialog progressDialog = new ProgressDialog(callingContext);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("Loading Episodes ...");
//                progressDialog.show();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(pod.get(i).getMp3_url());
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        TextView title;
        TextView date;
        ImageButton imgbtn;
        ImageView img;

        PersonViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Title_name);
            date = (TextView) itemView.findViewById(R.id.Date);
            imgbtn = (ImageButton) itemView.findViewById(R.id.imageButton);
            img = (ImageView) itemView.findViewById(R.id.podcastImage);
        }
    }

    public MediaPlayer getPlayer() {
        return player;
    }
}
