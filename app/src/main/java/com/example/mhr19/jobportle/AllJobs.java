package com.example.mhr19.jobportle;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mhr19.jobportle.RSSFeed.Item;
import com.example.mhr19.jobportle.RSSFeed.RssReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AllJobs extends Fragment {

    ListView list ;
    ArrayList<Item> job_list;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_all_jobs, container, false);
        getRSSFeed();

        return  view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void SetList(View view)
    {
        list = (ListView)view.findViewById(R.id.alljoblist);



        DevicesAdapter adapter = new DevicesAdapter(this.getActivity(),R.layout.list_layout,job_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Start_JobDetailsActivity();
            }
        });

    }

    void getRSSFeed()
    {
        new AsyncTask<String, Void, List<Item>>() {


            protected List<Item> doInBackground(String... params) {

                Boolean verified = false;
                try {

                    return executeRSSFeed();

                } catch (Exception e) {

                    e.printStackTrace();

                }
                return null;
            }

            protected void onPostExecute(List<Item> items) {
                //do something with response

                job_list=new ArrayList<Item>(items);
                SetList(view);
                // Set list adapter for the ListView


                // Set list view item click listener


            }
        }.execute();
    }
    List<Item> executeRSSFeed() {
        try {
            // Create RSS reader
            RssReader rssReader = new RssReader("http://rss.jobsearch.monster.com/rssquery.ashx?brd=1&cy=us&baseurl=jobview.monster.com#");
            // Get a ListView from main view

            return rssReader.getItems();
        } catch (Exception e) {
            Log.e("ITCRssReader", e.getMessage(), e);
        }
        return null;
    }
    public void Start_JobDetailsActivity()
    {
        Intent i = new Intent(getActivity(),JobDetails.class);
        startActivity(i);

    }


    public class DevicesAdapter extends ArrayAdapter<Item> {
        private int layoutResourceId;
        private List<Item> job;

        public DevicesAdapter(Context context, int layoutId,List<Item> list) {
            super(context, layoutId, list);
            layoutResourceId = layoutId;
            job = list;


        }


        @Override
        public View getView(final int index, View row, ViewGroup parent) {

            if(row == null) {
                row = getActivity().getLayoutInflater().inflate(layoutResourceId, parent, false);
            }

            TextView Ti = (TextView) row.findViewById(R.id.title);
            Ti.setText(job.get(index).getTitle());

            TextView Des = (TextView) row.findViewById(R.id.desp);
            Des.setText(job.get(index).getDescription());

            return row;
        }
}}


