package com.example.arjun.hw05;

import android.app.Activity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {

ArrayList<Podcast> list = new ArrayList<>();

    ProgressDialog progressDialog;
    Boolean sw = true;
    static RecyclerView rv;
    static final String PODOBJ = "Podobj";
    private ImageButton pauseButton;
    private ProgressBar progressBar;
    public static Context mContext;
    MyAdapter myAdapter;
    private boolean adapterSet = false;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getBaseContext();

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        if (isConnectedOnline()) {
            Toast.makeText(MainActivity.this, "Its Connected", Toast.LENGTH_SHORT).show();
            new GetPodcastAsync().execute("http://www.npr.org/rss/podcast.php?id=510298");

            if(sw == true) {
                LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(llm);
            }
            else {
                GridLayoutManager glm = new GridLayoutManager(this, 2);
                rv.setLayoutManager(glm);
            }

//            seekBar = (SeekBar) findViewById(R.id.mainSeekBar);
//            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    if (fromUser) {
//                        if (player != null) {
//                            if (progress <= seekBar.getSecondaryProgress()) {
//                            } else {
//                                seekBar.setProgress(progress);
//                            }
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {}
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {}
//            });


//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                layoutParams.addRule(RelativeLayout.ABOVE, R.id.mainSeekBar);
//                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//                RelativeLayout mainRV = (RelativeLayout) findViewById(R.id.mainActRelativeLayout);
//                pauseButton = new ImageButton(this);
//                pauseButton.setImageDrawable(getDrawable(R.drawable.button_pause));
//                pauseButton.setLayoutParams(layoutParams);
//                mainRV.addView(pauseButton);
            Log.d("demo", "before adapter set if clause");
            if(adapterSet) {
                Log.d("demo", "adapter set");
                if(myAdapter.isPlayerPlaying()) {
                    Log.d("demo", "player is playing");
                } else {
                    Log.d("demo", "player is not playing");
                }
            } else {
                Log.d("demo", "adapter not set");
            }
        } else {
            Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
        }
    }

    public static Context getContext() {
        return mContext;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.switchView){
            if(sw == true) {
                sw = false;
            }
            else {
                sw = true;
            }
            new GetPodcastAsync().execute("http://www.npr.org/rss/podcast.php?id=510298");
        }
        return super.onOptionsItemSelected(item);
    }

    public class GetPodcastAsync extends AsyncTask<String, Void, ArrayList<Podcast>> {

        @Override
        protected ArrayList<Podcast> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                publishProgress();
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = con.getInputStream();
                    return PodcastUtil.PodcastParser.parsePodcast(in);//data obtained is sent for parsing
                }
            } catch (MalformedURLException | ProtocolException | SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Podcast> result) {
            super.onPostExecute(result);
            if(result != null){
                Log.d("demo", result.toString());
                if(sw == true) {
                    RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    rv.setLayoutManager(llm);
                    myAdapter = new MyAdapter(MainActivity.this, result);
                    rv.setAdapter(myAdapter);
                } else {
                    RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
                    GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 2);
                    rv.setLayoutManager(glm);
                     gridAdapter = new GridAdapter(result, MainActivity.this);
                    rv.setAdapter(gridAdapter);
                }
                progressDialog.dismiss();
                adapterSet = true;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //will execute before dointhe background method
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Episodes ...");
            progressDialog.show();
        }
    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ninfo = cm.getActiveNetworkInfo();
        if (ninfo != null && ninfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
