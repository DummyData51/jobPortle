package com.example.mhr19.jobportle;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class profile_activity extends AppCompatActivity {

    ListView list ;
    ArrayList<UserData> userdata_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(("#d200b1fd"))));
        actionBar.setDisplayShowHomeEnabled(true);


        list = (ListView)findViewById(R.id.profile_list);

        UserData temp = new UserData();
        temp.setTitle("Name");
        temp.setContent("Mateen yaseen");


        UserData temp2 = new UserData();
        temp2.setTitle("Country");
        temp2.setContent("USA");


        UserData temp3 = new UserData();
        temp3.setTitle("City");
        temp3.setContent("L.A");


        UserData temp4 = new UserData();
        temp4.setTitle("Skills");
        temp4.setContent("c, c++ , c#");

        userdata_list.add(temp);
        userdata_list.add(temp2);
        userdata_list.add(temp3);
        userdata_list.add(temp4);


        ProfileListAdapdet adapter = new ProfileListAdapdet(this,userdata_list);
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_activity, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
