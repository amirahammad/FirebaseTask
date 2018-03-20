package tasks.com.firebasetask.model;

/**
 * Created by Freeware Sys on 19/03/2018.
 */

public class Track {
    private  String id;
    private  String trackName;
    private int rating;


    public Track(String id, String trackName, int rating) {
        this.id = id;
        this.trackName = trackName;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getTrackName() {
        return trackName;
    }
}
