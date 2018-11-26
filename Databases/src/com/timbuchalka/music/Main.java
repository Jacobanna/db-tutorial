package com.timbuchalka.music;

import com.timbuchalka.music.model.Artist;
import com.timbuchalka.music.model.Datasource;
import com.timbuchalka.music.model.SongArtist;

import java.util.List;
import java.util.Scanner;

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

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_DESC);
        if(albumsForArtist == null) {
            System.out.println("No albums for this artist!");
            return;
        }
        for(String album : albumsForArtist) {
            System.out.println(album);
        }

        List<SongArtist> songArtists1 = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_DESC);
        if(songArtists1 == null) {
            System.out.println("Couldn't find the artist for the song");
        }

        for(SongArtist songArtist : songArtists1) {
            System.out.println("Artist name = " + songArtist.getArtistName() +
            " Album name = " + songArtist.getAlbumName() +
            " Track = " + songArtist.getTrack());
        }

        datasource.querySongsMetadata();

        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasource.createViewForSongArtists();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();
        List<SongArtist> songArtists2 = datasource.querySongInfoView(title);

        if(songArtists2.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
        }

        for(SongArtist songArtist : songArtists2) {
            System.out.println("FROM VIEW Artist name = " + songArtist.getArtistName() +
                    " Album name = " + songArtist.getAlbumName() +
                    " Track = " + songArtist.getTrack());
        }

        datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);

        datasource.closeConnection();
    }
}
