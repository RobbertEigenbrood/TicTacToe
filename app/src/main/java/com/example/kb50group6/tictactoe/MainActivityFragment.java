package com.example.kb50group6.tictactoe;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
/*
        TextView textViewPlay = (TextView) rootView.findViewById(R.id.textViewPlay);
        TextView textViewSettings = (TextView) rootView.findViewById(R.id.textViewSettings);
        TextView textViewExit = (TextView) rootView.findViewById(R.id.textViewExit);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "tangledupinyou.ttf");
        textViewPlay.setTypeface(font);
        textViewSettings.setTypeface(font);
        textViewExit.setTypeface(font);


*/
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        String syncConnPref = sharedPref.getString("playername", "");
        TextView playerTextview = (TextView)rootView.findViewById(R.id.playernameTextview);
        playerTextview.setText(syncConnPref);

        return rootView;
    }
}
