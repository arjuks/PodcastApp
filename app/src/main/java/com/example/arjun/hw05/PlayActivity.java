package com.example.arjun.hw05;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayActivity extends Activity {

    SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Podcast pfinal = getIntent().getExtras().getParcelable(MainActivity.PODOBJ);
        TextView title = (TextView) findViewById(R.id.EpisodeTitle);
        TextView desc = (TextView) findViewById(R.id.Description_Value);
        TextView date = (TextView) findViewById(R.id.Date_Value);
        TextView dur = (TextView) findViewById(R.id.Duration_Value);

        String dateform = format.format(Date.parse(pfinal.getDate()));
        title.setText(pfinal.getTitle());
        desc.setText(pfinal.getDescription());
        date.setText(dateform);
        dur.setText(pfinal.getDuration());
    }
}
