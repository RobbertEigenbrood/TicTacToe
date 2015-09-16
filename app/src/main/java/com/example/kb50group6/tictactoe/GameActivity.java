package com.example.kb50group6.tictactoe;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private boolean humanTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        //TODO: here we need to implement a (Bundle) savedInstanceState
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        //---Get the current display info---
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        /* TODO: getWidth and getHeight are deprecated. Mind that this is the reason why we import "support.v4" */
        if(display.getWidth() > display.getHeight()){
            //---Portrait mode---
            GameActivityLandscapeFragment gameActivityLandscapeFragment = new GameActivityLandscapeFragment();
            // android.R.id.content refers to the content view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, gameActivityLandscapeFragment);
        }
        else
        {
            //---Landscape mode
            GameActivityFragment gameActivityFragment = new GameActivityFragment();
            // android.R.id.content refers to the content view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, gameActivityFragment);
        }
        fragmentTransaction.commit();


    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        //TODO: save data here when user turns the screen. Maybe implement onStop() as well(?)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void onClick(View v){
        TextView tv = (TextView)v;
        handleInput(tv);

    }

    public void handleInput(TextView tv){
        TextView tv_turn = (TextView) findViewById(R.id.turn_tv);
        tv.setClickable(false);
        if(humanTurn){

            tv.setText("O");
            humanTurn = false;
            tv_turn.setText(R.string.computers_turn);
        }
        else{
            tv.setText("X");
            humanTurn = true;
            tv_turn.setText(R.string.your_turn);
        }
    }
}
