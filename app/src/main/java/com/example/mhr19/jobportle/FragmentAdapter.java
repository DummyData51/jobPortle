package com.example.mhr19.jobportle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by Mateen on 9/3/2015.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                AllJobs tab1 = new AllJobs();
                return tab1;
            case 1:

                SavedJobs tab2 = new SavedJobs();
                return tab2;
            case 2:
                LocalJobs tab3 = new LocalJobs();
                return tab3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }//set the number of tabs

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "JOBSS";
            case 1:

                return "SAVED JOBS";
            case 2:

                return "LOCAL JOBS";
        }
        return null;
    }
}
