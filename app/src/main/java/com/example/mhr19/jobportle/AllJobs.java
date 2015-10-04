package com.example.mhr19.jobportle;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.app.ListFragment;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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


public class AllJobs extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ListView list ;
    ArrayList<Item> job_list;
    ArrayList<String> descriptions = new ArrayList<>();
    SwipeRefreshLayout refresh ;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_all_jobs, container, false);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresher);
        refresh.setOnRefreshListener(this);
        descriptions = new ArrayList<>();
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

       ListAdapter adapter = new ListAdapter(this.getActivity(),R.layout.list_layout,job_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Start_JobDetailsActivity( i);
            }
        });

    }

    private void getRSSFeed()
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

                job_list=setList(items);
                SetList(view);

            }
        }.execute();
    }


    private ArrayList<Item> setList(List<Item> items)
    {
        ArrayList<Item> temp = new ArrayList<Item>(items);


        for(Item i : temp)
        {
           String description = i.getDescription();

            this.descriptions.add(description);

            String t_desc = "";
            if(description.length() >= 100)
              t_desc= description.substring(0,40);
            else
            t_desc = description.substring(0,description.length()-1);


            t_desc +="...";

            i.setDescription(t_desc);

        }

        return temp;
    }


    private List<Item> executeRSSFeed() {
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


    public void Start_JobDetailsActivity(int j )
    {
        Intent i = new Intent(getActivity(),JobDetails.class);
        i.putExtra("desc",descriptions.get(j));
        i.putExtra("title",job_list.get(j).getTitle());
        i.putExtra("link",job_list.get(j).getLink());
        i.putExtra("date",job_list.get(j).getDate());
        startActivity(i);

    }

    @Override
    public void onRefresh() {
        getRSSFeed();
    }


    public class ListAdapter extends ArrayAdapter<Item> {
        private int layoutResourceId;
        private List<Item> job;

        public ListAdapter(Context context, int layoutId,List<Item> list) {
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

           // TextView date = (TextView) row.findViewById(R.id.date);
            // date.setText(job.get(index).getDate());

            return row;
        }
}}


