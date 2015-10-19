package com.example.arjun.hw05;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arjun on 10/13/2015.
 */
public class Podcast implements Parcelable {

    String title;
    String description;
    String date;
    String img_url;
    String duration;
    String mp3_url;

    public Podcast(String title, String description, String date, String img_url, String duration, String mp3_url) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.img_url = img_url;
        this.duration = duration;
        this.mp3_url = mp3_url;
    }

    public Podcast() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMp3_url() {
        return mp3_url;
    }

    public void setMp3_url(String mp3_url) {
        this.mp3_url = mp3_url;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", img_url='" + img_url + '\'' +
                ", duration='" + duration + '\'' +
                ", mp3_url='" + mp3_url + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Podcast> CREATOR = new Parcelable.Creator<Podcast>() {
        public Podcast createFromParcel(Parcel in) {
            return new Podcast(in);
        }

        @Override
        public Podcast[] newArray(int size) {
            return new Podcast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(img_url);
        dest.writeString(duration);
        dest.writeString(mp3_url);

    }

    private Podcast(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.img_url = in.readString();
        this.duration = in.readString();
        this.mp3_url = in.readString();
    }
}
