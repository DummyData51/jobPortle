package com.example.mhr19.jobportle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mateen on 9/10/2015.
 */
public class ProfileListAdapdet extends ArrayAdapter<UserData>{

    private List<UserData> data;


    public ProfileListAdapdet(Context context, List<UserData> list) {
        super(context, R.layout.profile_list_layout, list);
        data = list;
    }


    @Override
    public View getView(final int index, View row, ViewGroup parent) {

        LayoutInflater infl = LayoutInflater.from(getContext());
        View currentview = infl.inflate(R.layout.profile_list_layout,parent,false);

        TextView Ti = (TextView) currentview.findViewById(R.id.p_title);
        Ti.setText(data.get(index).title);

        EditText Des = (EditText) currentview.findViewById(R.id.p_content);
        Des.setText(data.get(index).content);

        return currentview;
    }
}
