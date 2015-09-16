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

    //TODO: don't these case-statements all do exactly the same?
    public void onClick(View v){
        //Sooooo...why don't we just do this?
        handleInput((TextView)v);

        //Instead of this:
        /*
        TextView tv = (TextView)v;
        switch(v.getId()){
            case R.id.textView:
               handleInput(tv);
                break;
            case R.id.textView2:
                handleInput(tv);
                break;
            case R.id.textView3:
                handleInput(tv);
            case R.id.textView4:
                handleInput(tv);
                break;
            case R.id.textView5:
                handleInput(tv);
                break;
            case R.id.textView6:
                handleInput(tv);
                break;
            case R.id.textView7:
                handleInput(tv);
                break;
            case R.id.textView8:
                handleInput(tv);
                break;
            case R.id.textView9:
                handleInput(tv);
                break;
        } */
    }

    public void handleInput(TextView tv){
        TextView tv_turn = (TextView) findViewById(R.id.turn_tv);

        //If we turn off the clickable (which is good)...
        tv.setClickable(false);
        if(humanTurn){

            tv.setText("O");
            humanTurn = false;

            //dit niet hardcoded maken DONE
            tv_turn.setText(R.string.computers_turn);
            //tv_turn.setText("De computer is aan de beurt!");
        }
        else{
            tv.setText("X");
            humanTurn = true;

            //dit niet hardcoded maken DONE
            tv_turn.setText(R.string.your_turn);
            //tv_turn.setText("Jij bent aan de beurt!");
        }
        //...we have to turn it back on again
        tv.setClickable(true);
    }
}
