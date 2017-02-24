package com.example.thebfg.project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_playlist);

        ArrayList<Song> playlist = new ArrayList<Song>();
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            playlist = (ArrayList<Song>) extras.get("selectedMusic");

        SongGrid grid = new SongGrid(this, playlist);

        GridView view = (GridView) findViewById(R.id.song_grid);
        view.setAdapter(grid);
        registerForContextMenu(view);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

    }
}

class SongGrid extends ArrayAdapter<Song>{
    Context c;
    public SongGrid(Context c,ArrayList<Song> musicList) {
        super(c,0,musicList);
        this.c = c;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        final Song song = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.songgrid, parent, false);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.song_image);
        image.setImageResource(song.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(song.url)));
            }
        });
        return convertView;
    }
}