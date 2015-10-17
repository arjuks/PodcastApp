package com.example.arjun.hw05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {

    List<Podcast> pod;
    Context context;

    MyAdapter(List<Podcast> pod) {
        this.pod = pod;
        //context = context.getApplicationContext();
    }

    SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");
    Uri uri;
    Bitmap bmp;


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        //img = personViewHolder.findViewById(R.id.imageView);
        personViewHolder.title.setText(pod.get(i).getTitle());
        String date = format.format(Date.parse(pod.get(i).getDate()));
        Uri uri = Uri.parse(pod.get(i).getImg_url());
        //personViewHolder.imgbtn.setImageURI(uri);

        personViewHolder.date.setText(date);
        String path = pod.get(i).getImg_url();

//        Picasso.with(context.getApplicationContext()).load(path).into(personViewHolder.img);

        Log.d("demo", "urls" + " " + path);

        //new GetImageWithParams().execute(path);

       // personViewHolder.img.setImageBitmap(bmp);

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
            img = (ImageView) itemView.findViewById(R.id.imageView);
            imgbtn = (ImageButton) itemView.findViewById(R.id.imageButton);
        }
    }


//    class GetImageWithParams extends AsyncTask<String, Integer, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            InputStream in = null;
//
//            try {
//
//                URL url = new URL(params[0]);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
//                in = con.getInputStream();
//                Bitmap img = BitmapFactory.decodeStream(in);
//
//                return img;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (in != null) {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return null;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//
//            if (result != null) {
//                bmp = result;
//            }
//
//        }

    }
