package tasks.com.firebasetask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tasks.com.firebasetask.model.Artist;

public class MainActivity extends AppCompatActivity {
    public  static final String artist_name="tasks.com.firebasetask.artistName";
    public static final String artist_id="tasks.com.firebasetask.artistId";
    //declare views in xml
    EditText d;
    Spinner spinner;
    Button btn;
    ListView list;
    List<Artist>artistList;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseArtists= FirebaseDatabase.getInstance().getReference("artists");
        initializeViews();
        artistList=new ArrayList<>();
        onClick();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist=artistList.get(position);

                Intent i=new Intent(getApplicationContext(),ArtistActivity.class);

                i.putExtra(artist_id,artist.getArtistId());
                i.putExtra(artist_name,artist.getArtistName());

                startActivity(i);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Artist artist = postSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                ArtistList artistAdapter = new ArtistList(MainActivity.this, artistList);

                list.setAdapter(artistAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {




            }
        });
    }
    public void initializeViews(){
        d= (EditText) findViewById(R.id.d);
        spinner= (Spinner) findViewById(R.id.spinner);
        btn= (Button) findViewById(R.id.btn);
        list= (ListView) findViewById(R.id.list);
    }
    public void onClick(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });
    }
    private void  addArtist(){
        String name=d.getText().toString().trim();
        String genre=spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){

            String id=databaseArtists.push().getKey();
            Artist artist=new Artist(id,name,genre);

            databaseArtists.child(id).setValue(artist);
            d.setText("");

            Toast.makeText(this,"artist added",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this,"please enter name",Toast.LENGTH_LONG).show();
        }

    }


}
