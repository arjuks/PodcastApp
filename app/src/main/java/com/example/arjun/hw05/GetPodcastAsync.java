package com.example.arjun.hw05;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


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
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Podcast> result) {
            super.onPostExecute(result);
            if(result != null){
                Log.d("demo", result.toString());
            }
        }
    }

