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

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
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
        Toast.makeText(this,"List Filled",Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v){
        TextView textView = (TextView)v;
        handleTurns(textView);
    }


    public void handleTurns(TextView tv){
        playerPressed(tv);
        checkForWin();
        computerTurn();
        checkForWin();
    }


    public void playerPressed(TextView tv){
        tv.setText("x");
        tv.setClickable(false);
        tvlist.remove(tv);


    }


    public void computerTurn(){
        if(tvlist.size()!=0) {
            int rand = (int) Math.floor(Math.random() * tvlist.size());
            TextView textView = tvlist.get(rand);
            textView.setText("0");
            textView.setClickable(false);
            tvlist.remove(rand);
        }
    }

    private void checkForWin()
    {
        Toast.makeText(this,"I DONT CARE",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPause()
    {
        super.onPause();
        Toast.makeText(this,"Pauze",Toast.LENGTH_SHORT).show();

    }
}





