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

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_ASC);
        if(artists == null) {
            System.out.println("No artists loaded!");
            return;
        }
        for(Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Pink Floyd", Datasource.ORDER_BY_DESC);
        if(albumsForArtist == null) {
            System.out.println("No albums for this artist!");
            return;
        }
        for(String album : albumsForArtist) {
            System.out.println(album);
        }

        datasource.closeConnection();
    }
}
