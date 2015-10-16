package com.example.arjun.hw05;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

ArrayList<Podcast> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetPodcastAsync().execute("http://www.npr.org/rss/podcast.php?id=510298");
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(llm);



    }

    public class GetPodcastAsync extends AsyncTask<String, Void, ArrayList<Podcast>> {

        @Override
        protected ArrayList<Podcast> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = con.getInputStream();

                    return PodcastUtil.PodcastParser.parsePodcast(in);//data obtained is sent for parsing
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Podcast> result) {
            super.onPostExecute(result);
            if(result != null){
                Log.d("demo", result.toString());
                //list.addAll(result);
                RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
                MyAdapter adapter = new MyAdapter(result);

                rv.setAdapter(adapter);
            }
        }
    }

}
