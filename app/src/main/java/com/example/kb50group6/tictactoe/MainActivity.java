package com.example.kb50group6.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClickButtonHowToPlay(View view){

        //TODO: decide which one of the following implementations we're going to use

        /* We create a new DialogFragment of our "HowToPlayDialogFragment" Class -- */
              //DialogFragment newFragment = new HowToPlayDialogFragment();
        /* --and call show(). We give it a tag so we can do something with it afterwards (or simply because we have to, anyway): */
              //newFragment.show(getSupportFragmentManager(), "howtoplay");

        /*
            The following code is another (better?) way of achieving the above. This way,
            we don't need the "HowToPlayDialogFragment" anymore.
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(this); //this Activity / This Context
        builder.setMessage(R.string.how_to_play_text)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Empty.
                        // Here we decide what happens when the user clicks "Ok"
                        Toast.makeText(getBaseContext(), "OK clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    public void onClickButtonExit(View view){
        finish();
    }

    public static class HowToPlayDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.how_to_play_text)
                    .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Empty.
                            // Here we decide what happens when the user clicks "Ok"
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
