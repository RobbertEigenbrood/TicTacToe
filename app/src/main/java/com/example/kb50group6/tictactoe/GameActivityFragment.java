package com.example.kb50group6.tictactoe;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameActivityFragment extends Fragment {
    private View rootView;
    private static ArrayList<TextView> tvlist = new ArrayList<>();
    private ArrayList<TextView> txtViewList;
    public GameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_game, container, false);
        fillList();
        txtViewList = new ArrayList<>(tvlist);
        return rootView;
    }


    public void fillList(){
        TextView tv1 = (TextView)rootView.findViewById(R.id.textView);
        TextView tv2 = (TextView)rootView.findViewById(R.id.textView2);
        TextView tv3 = (TextView)rootView.findViewById(R.id.textView3);
        TextView tv4 = (TextView)rootView.findViewById(R.id.textView4);
        TextView tv5 = (TextView)rootView.findViewById(R.id.textView5);
        TextView tv6 = (TextView)rootView.findViewById(R.id.textView6);
        TextView tv7 = (TextView)rootView.findViewById(R.id.textView7);
        TextView tv8 = (TextView)rootView.findViewById(R.id.textView8);
        TextView tv9 = (TextView)rootView.findViewById(R.id.textView9);

        tvlist.add(tv1);
        tvlist.add(tv2);
        tvlist.add(tv3);
        tvlist.add(tv4);
        tvlist.add(tv5);
        tvlist.add(tv6);
        tvlist.add(tv7);
        tvlist.add(tv8);
        tvlist.add(tv9);
    }

    public static void playerPressed(View v){
        TextView tv = (TextView) v;
        tv.setClickable(false);
        tvlist.remove(tv);
        tv.setText("X");

        if(tvlist.size()!=0)
        computerTurn();
    }




    public static void computerTurn(){

        int rand = (int)Math.floor(Math.random() * tvlist.size());
        TextView textView = tvlist.get(rand);
        textView.setText("0");
        textView.setClickable(false);
        tvlist.remove(rand);
    }
}
