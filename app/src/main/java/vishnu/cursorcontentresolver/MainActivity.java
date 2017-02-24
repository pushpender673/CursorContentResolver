package vishnu.cursorcontentresolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> songs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parsesongs();
        Collections.sort(songs, String.CASE_INSENSITIVE_ORDER);
        Adapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,songs);
        ListView listView = (ListView) findViewById(R.id.list_songs);
        listView.setAdapter((ListAdapter) adapter);
    }

    public void parsesongs(){
        //Retrieve a list of Music files currently listed in the Media store DB via URI.
        //Some audio may be explicitly marked as not being music

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
        };
        ContentResolver content = getContentResolver();
        Cursor cursor = content.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        while(cursor.moveToNext()){
            songs.add(cursor.getString(0));
        }
        Log.d("Vishnu",songs.toString());
    }
}
