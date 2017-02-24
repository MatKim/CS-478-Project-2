package com.example.thebfg.project2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Playlist extends AppCompatActivity {
    public ArrayList<Song> makeSongList(){
        ArrayList<Song> musicLibrary = new ArrayList<Song>();
        Song number1 = new Song("Playboi Carti x Da$H x Maxo Kream - \"FETTI\" [OFFICIAL MUSIC VIDEO]", "https://www.youtube.com/watch?v=A5IvyeAeEOM");
        Song number2 = new Song("Yung Lean ♦ Ginseng Strip 2002 ♦", "https://www.youtube.com/watch?v=vrQWhFysPKY");
        Song number3 = new Song("$ha Hef \"Super Villain\" (Official Video)", "https://www.youtube.com/watch?v=2RVeCNqkVbk");
        Song number4 = new Song("Waka Flocka Flame - Hard In The Paint (suicideyear remix)", "https://www.youtube.com/watch?v=1rDkmlo_oio");
        Song number5 = new Song("Denzel Curry - Threatz (Feat. Yung Simmie & Robb Bank$) Prod. Ronny J", "https://www.youtube.com/watch?v=vwnVI_x5g0I");
        Song number6 = new Song("Secret Circle (Antwon & Wiki) - Satellite (Ft. Despot) [Prod. By Shawn Kemp]", "https://www.youtube.com/watch?v=QAfI8KBYbzM");

        musicLibrary.add(number1);
        musicLibrary.add(number2);
        musicLibrary.add(number3);
        musicLibrary.add(number4);
        musicLibrary.add(number5);
        musicLibrary.add(number6);

        return musicLibrary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ArrayList <Song> musicLibrary = makeSongList();

        SongAdapter songAdapter = new SongAdapter(this, musicLibrary);

        ListView songList = (ListView) findViewById(R.id.song_list);
        songList.setAdapter(songAdapter);
    }
}

class SongAdapter extends ArrayAdapter<Song>{
    public SongAdapter(Context context, ArrayList<Song> songs){
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Song song = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.songlist, parent, false);
        }
        CheckedTextView title = (CheckedTextView) convertView.findViewById(R.id.title);
        title.setText(song.title);
        return convertView;
    }
}

class Song{
    public String title;
    public String url;

    public Song(String name, String url){
        this.title = name;
        this.url = url;
    }
}