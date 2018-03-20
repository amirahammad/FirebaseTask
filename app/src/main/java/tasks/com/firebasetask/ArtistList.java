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

import tasks.com.firebasetask.model.Artist;

/**
 * Created by Freeware Sys on 19/03/2018.
 */

public class ArtistList extends ArrayAdapter<Artist> {
    private Activity context;
    List<Artist>artistList;
    TextView textName;
    TextView textGenre;


    public ArtistList(Activity context, List<Artist>artistList) {
        super(context,R.layout.artist_list, artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.artist_list, null, true);

        textName= (TextView) row.findViewById(R.id.textName);
        textGenre= (TextView) row.findViewById(R.id.textGenre);

        Artist artist=artistList.get(position);

        textName.setText(artist.getArtistName());
        textGenre.setText(artist.getArtistGenre());

        return row;


    }
}
