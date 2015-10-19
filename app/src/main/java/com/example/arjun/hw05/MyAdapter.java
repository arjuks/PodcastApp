package com.example.arjun.hw05;

import android.content.Intent;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {

    List<Podcast> pod;
    final static int POD_CODE = 222;



    MyAdapter(List<Podcast> pod) {
        this.pod = pod;
        //context = context.getApplicationContext();
    }

    SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");
    Uri uri;
    Bitmap bmp;



    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.println(v.getTag(i));
                int pos = MainActivity.rv.getChildPosition(v);
                String title = pod.get(pos).getTitle();
                Log.d("demo", "item clicked"+" "+title);
                String desc = pod.get(pos).getDescription();
                String date = pod.get(pos).getDate();
                String duration = pod.get(pos).getDuration();
                String img = pod.get(pos).getImg_url();
                String mp3 = pod.get(pos).getMp3_url();

                Podcast p = new Podcast(title,desc,date,img,duration,mp3);
                Intent intent = new Intent(MainActivity.getContext(),PlayActivity.class);
                intent.putExtra(MainActivity.PODOBJ, p);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.getContext().startActivity(intent);

            }
        });
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
