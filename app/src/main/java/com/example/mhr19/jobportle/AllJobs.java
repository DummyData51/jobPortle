package com.example.mhr19.jobportle;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AllJobs extends Fragment {

    ListView list ;
    ArrayList<Job> job_list = new ArrayList<Job>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_all_jobs,container,false);

        SetList(view);

        return  view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    public void SetList(View view)
    {
        list = (ListView)view.findViewById(R.id.alljoblist);

        Job temp = new Job();
        temp.setTitle("Electrical Engg");
        temp.setDescription("Here are the Description....");
        job_list.add(temp);
        Job temp2 = new Job();
        temp2.setTitle("Electrical Engg");
        temp2.setDescription("Here are the Description....");
        job_list.add(temp2);

        DevicesAdapter adapter = new DevicesAdapter(this.getActivity(),R.layout.list_layout,job_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Start_JobDetailsActivity();
            }
        });

    }

    public void Start_JobDetailsActivity()
    {
        Intent i = new Intent(getActivity(),JobDetails.class);
        startActivity(i);

    }


    public class DevicesAdapter extends ArrayAdapter<Job> {
        private int layoutResourceId;
        private List<Job> job;

        public DevicesAdapter(Context context, int layoutId,List<Job> list) {
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
            Ti.setText(job.get(index).title);

            TextView Des = (TextView) row.findViewById(R.id.desp);
            Des.setText(job.get(index).Description);

            return row;
        }
}}


