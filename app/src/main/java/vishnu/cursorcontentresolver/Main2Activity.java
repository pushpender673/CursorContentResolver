package vishnu.cursorcontentresolver;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    ViewPager viewPager = null;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new fragmentAdapter(fragmentManager));


    }
}

class fragmentAdapter extends FragmentPagerAdapter {

    public fragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Vishnu", "Viewpager getitem");
        if (position == 0)
            return (new frament_songslist());
        if (position == 1)
            return (new fragment_albumlist());
        if (position == 2)
            return (new fragment_artistlist());

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Songs";
        if (position == 1)
            return "Album";
        if (position == 2)
            return "Artist";
        return null;
    }
}