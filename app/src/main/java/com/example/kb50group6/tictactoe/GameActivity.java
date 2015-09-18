package com.example.kb50group6.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class GameActivity extends AppCompatActivity{

    private boolean humanTurn = true;

    public ArrayList<TextView> tvlist = new ArrayList<TextView>();
    TextView[][] textViewArray = new TextView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Replace the default font with our own chalk font
        ReplaceFont.overrideFont(getApplicationContext(), "SERIF", "tangledupinyou.ttf");

        fillList();
        //Toast.makeText(this,"List Filled",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void onClick(View v){
        TextView textView = (TextView)v;
        handleTurns(textView);
    }


    public void handleTurns(TextView tv) {
        playerPressed(tv);

        /* The computer makes a move in a random time between 0 and 3 seconds */
        int random = (int) Math.floor(Math.random() * 3000);

        Handler handler = new Handler();

        /* We check if the game is won to prevent showing a dialog twice when game is finished */
        if (checkForWin()) {
            /* Turn off clickables and wait out the random delay in a Runnable() */
            for (int x = 1; x <= tvlist.size(); x++) {
                tvlist.get(x - 1).setClickable(false);
            }
               handler.postDelayed(new Runnable() {
                   public void run() {
                       computerTurn();
                       //Turn on clickables
                       for (int x = 1; x <= tvlist.size(); x++) {
                           tvlist.get(x - 1).setClickable(true);
                       }
                   }
               },random);

            for (int x = 1; x <= tvlist.size(); x++) {
                // Someone won, so we are not allowed to click the buttons anymore (or both could still win)
                tvlist.get(x - 1).setClickable(false);
            }
        }
        else {
            /* Turn off clickables and wait out the random delay in a Runnable() */
            for (int x = 1; x <= tvlist.size(); x++) {
                tvlist.get(x - 1).setClickable(false);
            }
            handler.postDelayed(new Runnable() {
                public void run() {
                    computerTurn();
                    //Turn on clickables
                    for (int x = 1; x <= tvlist.size(); x++) {
                        tvlist.get(x - 1).setClickable(true);
                    }
                }
            }, random);

            if (checkForWin()) {
                for (int x = 1; x <= tvlist.size(); x++) {
                    // Someone won, so we are not allowed to click the buttons anymore (or both could still win)
                    tvlist.get(x - 1).setClickable(false);
                }
            }
        }
    }

    public void makeToast(){
       // Toast.makeText(this,"Computer heeft gezet",Toast.LENGTH_SHORT).show();
    }

    public void playerPressed(TextView tv){
        tv.setText("x");
        tv.setClickable(false);
        tvlist.remove(tv);
        TextView tv_turn = (TextView)findViewById(R.id.turn_tv);
        tv_turn.setText(R.string.computers_turn);
    }

    public void computerTurn(){
        if(tvlist.size()!=0) {
            int rand = (int) Math.floor(Math.random() * tvlist.size());
            TextView textView = tvlist.get(rand);
            textView.setText("0");
            textView.setClickable(false);
            tvlist.remove(rand);TextView tv_turn = (TextView)findViewById(R.id.turn_tv);
            tv_turn.setText(R.string.your_turn);

        }
    }

    private boolean checkForWin()
    {
        //Toast.makeText(this,"I DONT CARE",Toast.LENGTH_SHORT).show();

        String who_won = "No one ";

        /* Dit kan geheid een stuk korter maar "for the sake of simplicity": */
        TextView tv1 = (TextView)findViewById(R.id.textView);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        TextView tv3 = (TextView)findViewById(R.id.textView3);
        TextView tv4 = (TextView)findViewById(R.id.textView4);
        TextView tv5 = (TextView)findViewById(R.id.textView5);
        TextView tv6 = (TextView)findViewById(R.id.textView6);
        TextView tv7 = (TextView)findViewById(R.id.textView7);
        TextView tv8 = (TextView)findViewById(R.id.textView8);
        TextView tv9 = (TextView)findViewById(R.id.textView9);

        /* Start to check the rows... */
        //Row 1
        if( tv1.getText() == tv2.getText() && tv2.getText() == tv3.getText() ){
            who_won = tv1.getText().toString();
        }
        //Row 2
        if( tv4.getText() == tv5.getText() && tv5.getText() == tv6.getText() ){
            who_won = tv4.getText().toString();
        }
        //Row 3
        if(tv7.getText() == tv8.getText() && tv8.getText() == tv9.getText()){
            who_won = tv7.getText().toString();
        }

        /* ...then the columns... */
        //Row 1
        if( tv1.getText() == tv4.getText() && tv4.getText() == tv7.getText() ){
            who_won = tv1.getText().toString();
        }
        //Row 2
        if( tv2.getText() == tv5.getText() && tv5.getText() == tv8.getText() ){
            who_won = tv2.getText().toString();
        }
        //Row 3
        if(tv3.getText() == tv6.getText() && tv6.getText() == tv9.getText()){
            who_won = tv3.getText().toString();
        }

        /* ...and finally crosswise. */
        //Upper left to down right
        if( tv1.getText() == tv5.getText() && tv5.getText() == tv9.getText() ){
            who_won = tv1.getText().toString();
        }
        //Down left to upper right
        if( tv7.getText() == tv5.getText() && tv5.getText() == tv3.getText() ){
            who_won = tv7.getText().toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this); //this Activity / This Context

        if(who_won == "x" || who_won == "X"){
            //Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show();
            builder.setTitle("Hooray!")
                    .setMessage("You won.")
                    .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Empty.
                            // Here we decide what happens when the user clicks "Ok"
                        }
                    })
                    .show();
            return true;
        }
        else if(who_won == "o" || who_won == "O" || who_won == "0"){
            //Toast.makeText(this, "Haha you lost!", Toast.LENGTH_LONG).show();
            builder.setTitle("Potverdorie")
                    .setMessage("You lost :(")
                    .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Empty.
                            // Here we decide what happens when the user clicks "Ok"
                        }
                    })
                    .show();
            return true;
        }
        else{
            if(tvlist.size() == 0){
                //Toast.makeText(this, "Tie game", Toast.LENGTH_LONG).show();
                builder.setTitle("It's a tie.")
                        .setMessage("Damn you suck :/")
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Empty.
                                // Here we decide what happens when the user clicks "Ok"
                            }
                        })
                        .show();
                return true;
            }
            //Empty
        }
        return false;

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

    private void fillList()
    {

        TextView tv1 = (TextView)findViewById(R.id.textView);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        TextView tv3 = (TextView)findViewById(R.id.textView3);
        TextView tv4 = (TextView)findViewById(R.id.textView4);
        TextView tv5 = (TextView)findViewById(R.id.textView5);
        TextView tv6 = (TextView)findViewById(R.id.textView6);
        TextView tv7 = (TextView)findViewById(R.id.textView7);
        TextView tv8 = (TextView)findViewById(R.id.textView8);
        TextView tv9 = (TextView)findViewById(R.id.textView9);

        tvlist.add(tv1);
        tvlist.add(tv2);
        tvlist.add(tv3);
        tvlist.add(tv4);
        tvlist.add(tv5);
        tvlist.add(tv6);
        tvlist.add(tv7);
        tvlist.add(tv8);
        tvlist.add(tv9);

            int tvi=0;
            for(int x=0; x< 3 ; x++)
            {
                for(int i = 0;i<3;i++)
                {
                    textViewArray[x][i]=tvlist.get(tvi);
                    tvi++;
                }
            }

        }

    public void onClickReset(View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    public void onPause()
    {
        super.onPause();
        //Toast.makeText(this,"Pauze",Toast.LENGTH_SHORT).show();

    }
}





