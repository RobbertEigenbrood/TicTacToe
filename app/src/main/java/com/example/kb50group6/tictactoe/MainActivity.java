package com.example.kb50group6.tictactoe;

import android.app.Dialog;
//import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setIcon(R.drawable.tictactoe_logo);

        //setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        //---Get the current display info---
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        /* TODO: getWidth and getHeight are deprecated. Mind that this is the reason why we import "support.v4" */
        if(display.getWidth() > display.getHeight()){
            //---Portrait mode---
            MainActivityLandscapeFragment mainActivityLandscapeFragment = new MainActivityLandscapeFragment();
            // android.R.id.content refers to the content view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, mainActivityLandscapeFragment);
        }
        else
        {
            //---Landscape mode
            MainActivityFragment mainActivityFragment = new MainActivityFragment();
            // android.R.id.content refers to the content view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, mainActivityFragment);
        }
        fragmentTransaction.commit();

        //Replace the default font with our own chalk font
        ReplaceFont.overrideFont(getApplicationContext(), "SERIF", "tangledupinyou.ttf");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClickButtonPlay(View view){
        Intent i = new Intent(this,GameActivity.class);
        startActivity(i);
    }

    public void onClickButtonSettings(View view){

        /* Create a dynamic AlertDialog instead of instantiating an entire class
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //this Activity / This Context
        builder.setTitle(R.string.how_to_play_title)
                .setMessage(R.string.how_to_play_text)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Empty.
                        // Here we decide what happens when the user clicks "Ok"
                    }
                })
                .show();
        */
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);



    }

    public void onClickButtonExit(View view){
        finish();
    }

}
