package com.example.mhr19.jobportle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;


public class MainMenu extends AppCompatActivity {

    ViewPager pager;
    PagerTabStrip tab_strp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(("#d200b1fd"))));
        actionBar.setDisplayShowHomeEnabled(true);


        Set_MainMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.profile)
        {
            Intent p_activity = new Intent(this ,profile_activity.class );
            startActivity(p_activity);
        }

        return super.onOptionsItemSelected(item);
    }

    public void Set_MainMenu()
    {
        FragmentAdapter fa =new FragmentAdapter(getSupportFragmentManager());
        pager=(ViewPager)findViewById(R.id.pager);

        pager.setAdapter(fa);
        tab_strp=(PagerTabStrip)findViewById(R.id.tab_strip);
        tab_strp.setTextColor(Color.WHITE);
        tab_strp.setTextSize(3, 7);
        tab_strp.setTabIndicatorColor(Color.WHITE);



    }
}
