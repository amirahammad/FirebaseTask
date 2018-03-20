package tasks.com.firebasetask.model;

/**
 * Created by Freeware Sys on 19/03/2018.
 */

public class Artist {
    private  String artistId;
    private String artistName;
    private String artistGenre;


    public Artist(String id, String artistName, String artistGenre) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}
