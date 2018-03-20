package tasks.com.firebasetask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tasks.com.firebasetask.model.Track;

public class ArtistActivity extends AppCompatActivity {


    Button btn1;
    EditText d1;
    SeekBar seekBarRating;
    TextView textViewRating, textViewArtist;
    ListView list1;

    DatabaseReference databaseTracks;

    List<Track> trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        Intent intent = getIntent();

        /*
        * this line is important
        * this time we are not getting the reference of a direct node
        * but inside the node track we are creating a new child with the artist id
        * and inside that node we will store all the tracks with unique ids
        * */
        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(intent.getStringExtra(MainActivity.artist_id));

        btn1 = (Button) findViewById(R.id.btn1);
        d1 = (EditText) findViewById(R.id.d2);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        textViewArtist = (TextView) findViewById(R.id.textViewArtist);
        list1 = (ListView) findViewById(R.id.list2);

        trackList = new ArrayList<>();

        textViewArtist.setText(intent.getStringExtra(MainActivity.artist_name));

        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewRating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trackList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Track track = postSnapshot.getValue(Track.class);
                    trackList.add(track);
                }
                TrackList trackListAdapter = new TrackList(ArtistActivity.this, trackList);
                list1.setAdapter(trackListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

    }

    private void saveTrack() {
        String trackName = d1.getText().toString().trim();
        int rating = seekBarRating.getProgress();
        if (!TextUtils.isEmpty(trackName)) {
            String id  = databaseTracks.push().getKey();
            Track track = new Track(id, trackName, rating);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this, "Track saved", Toast.LENGTH_LONG).show();
            d1.setText("");
        } else {
            Toast.makeText(this, "Please enter track name", Toast.LENGTH_LONG).show();
        }
    }
    }

