package vishnu.cursorcontentresolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class frament_songslist extends ListFragment {
    ListView song_listview;
    Cursor cursor;
    CursorAdapter cursoradapter;
    /*
    List<String> songs = new ArrayList<String>();
    List<String> artist = new ArrayList<String>();
    List<String> duration = new ArrayList<>();
    List<String> album_name = new ArrayList<String>();
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Vishnu","Songlist createView");
        return inflater.inflate(R.layout.songslist_frament,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parsesongslist();

        cursoradapter = new SimpleCursorAdapter(getContext(),R.layout.songlist_fragment_row,cursor,
                new String[] {"TITLE", "ARTIST", "DURATION","ALBUM"}, new int[] {R.id.song_name,
                R.id.artist,R.id.duration,R.id.albumname},0);
        setListAdapter(cursoradapter);
    }

    public void parsesongslist(){
        //Retrieve a list of Music files currently listed in the Media store DB via URI.
        //Some audio may be explicitly marked as not being music so using ISMUSIC tag to check for it
        // using cursorLoader to load data so that it reflect the changes individually

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM };

        ContentResolver content = getActivity().getContentResolver();
        cursor = content.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        Log.d("Vishnu","Parse()");
 /*
        while(cursor.moveToNext()){
            songs.add(cursor.getString(1));
            artist.add(cursor.getString(2));
            duration.add(cursor.getString(3));
            album_name.add(cursor.getString(4));
        }
        Log.d("Vishnu",songs.toString());
        Log.d("Vishnu",artist.toString());
        Log.d("Vishnu",album_name.toString());
        Log.d("Vishnu",duration.toString());*/
    }
}