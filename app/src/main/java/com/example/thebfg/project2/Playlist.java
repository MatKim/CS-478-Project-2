package com.example.thebfg.project2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends AppCompatActivity {
    ListView songList;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.song_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_playlist:
                createPlaylist();
                return true;
            case R.id.check_all:
                checkAll();
                return true;
            case R.id.clear_selections:
                clearAll();
                return true;
            case R.id.invert_selections:
                invertSelections();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ArrayList <Song> musicLibrary = makeSongList();

        SongAdapter songAdapter = new SongAdapter(this, musicLibrary);

        songList = (ListView) findViewById(R.id.song_list);
        songList.setAdapter(songAdapter);
    }

    public void createPlaylist(){
        ArrayList<Song> musicSelected = new ArrayList<>();
        for(int i = 0; i < songList.getChildCount(); i++){
            if(songList.isItemChecked(i))
                musicSelected.add((Song) songList.getAdapter().getItem(i));
        }
        Intent i = new Intent(this.getApplicationContext(), GridPlaylist.class);
        i.putExtra("selectedMusic", musicSelected);
        startActivity(i);
    }
    public void checkAll(){
        for ( int i=0; i < songList.getChildCount(); i++) {
            songList.setItemChecked(i, true);
        }
    }
    public void clearAll(){
        for ( int i=0; i < songList.getChildCount(); i++) {
            songList.setItemChecked(i, false);
        }
    }
    public void invertSelections(){
        for ( int i=0; i < songList.getChildCount(); i++) {
            if(songList.isItemChecked(i) == false)
                songList.setItemChecked(i, true);
            else
                songList.setItemChecked(i, false);
        }
    }

    public ArrayList<Song> makeSongList(){
        ArrayList<Song> musicLibrary = new ArrayList<Song>();
        Song number1 = new Song("Rob $tone - Chill Bill", "https://www.youtube.com/watch?v=J7IMwop3RHs", "https://en.wikipedia.org/wiki/Rob_Stone_(rapper)", "https://en.wikipedia.org/wiki/Chill_Bill", R.drawable.chillbill);
        Song number2 = new Song("Yung Lean - Kyoto", "https://www.youtube.com/watch?v=tMgkt9jdjTU", "https://en.wikipedia.org/wiki/Yung_Lean", "https://en.wikipedia.org/wiki/Kyoto_(Yung_Lean_song)", R.drawable.kyoto);
        Song number3 = new Song("xxxtentacion - Look at me!", "https://www.youtube.com/watch?v=Wmjpp0_6kb0", "https://en.wikipedia.org/wiki/xxxtentacion", "https://en.wikipedia.org/wiki/Look_at_Me_(XXXTENTACION_song)", R.drawable.xxxtentacion);
        Song number4 = new Song("Waka Flocka Flame - Hard In The Paint (suicideyear remix)", "https://www.youtube.com/watch?v=1rDkmlo_oio", "https://en.wikipedia.org/wiki/Waka_Flocka_Flame", "https://en.wikipedia.org/wiki/hard_in_da_paint", R.drawable.hard_in_da_paint);
        Song number5 = new Song("Denzel Curry - Threatz (Feat. Yung Simmie & Robb Bank$) Prod. Ronny J", "https://www.youtube.com/watch?v=vwnVI_x5g0I", "https://en.wikipedia.org/wiki/Denzel_Curry", "https://en.wikipedia.org/wiki/Nostalgic_64", R.drawable.threatz);
        Song number6 = new Song("Travis Scott - Antidote", "https://www.youtube.com/watch?v=KnZ8h3MRuYg", "https://en.wikipedia.org/wiki/Travis_Scott", "https://en.wikipedia.org/wiki/Antidote_(Travis_Scott_song)", R.drawable.antidote);

        musicLibrary.add(number1);
        musicLibrary.add(number2);
        musicLibrary.add(number3);
        musicLibrary.add(number4);
        musicLibrary.add(number5);
        musicLibrary.add(number6);

        return musicLibrary;
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

class Song implements Serializable{
    public String title;
    public String url;
    public String artistwikiurl;
    public String albumWikiUrl;
    public int image;

    public Song(String name, String url, String artistwikiurl, String albumWikiUrl, int image){
        this.title = name;
        this.url = url;
        this.artistwikiurl = artistwikiurl;
        this.albumWikiUrl = albumWikiUrl;
        this.image = image;
    }
}