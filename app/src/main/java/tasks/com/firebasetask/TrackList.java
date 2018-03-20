package tasks.com.firebasetask;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tasks.com.firebasetask.model.Track;

/**
 * Created by Freeware Sys on 20/03/2018.
 */

public class TrackList extends ArrayAdapter<Track> {
    private Activity context;
    List<Track> trackList;
    public TrackList(Context context, List<Track> trackList) {
        super(context, R.layout.artist_list,trackList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
      View  row = inflater.inflate(R.layout.artist_list, null, true);


        TextView textViewName = (TextView) row.findViewById(R.id.textName);
        TextView textViewRating = (TextView) row.findViewById(R.id.textGenre);


        Track track = trackList.get(position);
        textViewName.setText(track.getTrackName());
        textViewRating.setText(String.valueOf(track.getRating()));

        return row;
    }
}
