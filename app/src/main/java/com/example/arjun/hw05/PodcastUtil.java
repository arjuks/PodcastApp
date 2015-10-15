package com.example.arjun.hw05;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by arjun on 10/13/2015.
 */
public class PodcastUtil {

    static String title, description, pubDate,duration, img_url, enc_url;

        static public class PodcastParser extends DefaultHandler {
            ArrayList<Podcast> podcastList;
            Podcast pod;
            StringBuilder xmlInnerText;

            static public ArrayList<Podcast> parsePodcast(InputStream in) throws IOException, SAXException {
                PodcastParser parser = new PodcastParser();
                Xml.parse(in, Xml.Encoding.UTF_8, parser);
                return parser.getPodcastList();
            }

            public ArrayList<Podcast> getPodcastList() {
                return podcastList;
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
                if(localName.equals("item")) {
                    pod = new Podcast();
                    //pod.setId(attributes.getValue("id"));
                }
                else if(localName.equals("image")) {
                    //pod.setImg_url(attributes.getValue("href"));
                    img_url = attributes.getValue("href");
                }
                else if(localName.equals("enclosure")) {
                    //pod.setMp3_url(attributes.getValue("url"));
                    enc_url = attributes.getValue("url");
                }

            }

            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
                xmlInnerText = new StringBuilder();
                podcastList = new ArrayList<Podcast>();
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);

                if(localName.equals("title")) {
                    //pod.setTitle(xmlInnerText.toString().trim());
                    title = xmlInnerText.toString().trim();
                }
                else if(localName.equals("description")) {
                    //pod.setDescription(xmlInnerText.toString().trim());
                    description = xmlInnerText.toString().trim();
                }
                else if(localName.equals("pubDate")) {
                    //pod.setDate(xmlInnerText.toString().trim());
                    pubDate = xmlInnerText.toString().trim();
                }
                else if(localName.equals("duration")) {
                    //Log.d("demo","duration"+xmlInnerText.toString().trim());
                    duration = xmlInnerText.toString().trim();
                }
                else if(localName.equals("item")) {
                    pod = new Podcast(title, description, pubDate, img_url, duration, enc_url);
                    podcastList.add(pod);
                }
                xmlInnerText.setLength(0);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);
                xmlInnerText.append(ch,start,length);
            }
        }
    }
