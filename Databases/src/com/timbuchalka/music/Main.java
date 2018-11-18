package com.timbuchalka.music;

import com.timbuchalka.music.model.Artist;
import com.timbuchalka.music.model.Datasource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.openConnection()) {
            System.out.println("Can't open datasource.");
            return;
        }

        List<Artist> artists = datasource.queryArtists();
        if(artists == null) {
            System.out.println("No artists loaded!");
            return;
        }
        for(Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        datasource.closeConnection();
    }
}
