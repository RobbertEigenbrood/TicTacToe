package com.example.kb50group6.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private boolean humanTurn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
        ImageView iv = (ImageView)v;
        switch(v.getId()){
            case R.id.imageView:
               handleInput(iv);
                break;
            case R.id.imageView2:
                handleInput(iv);
                break;
            case R.id.imageView3:
                handleInput(iv);
            case R.id.imageView4:
                handleInput(iv);
                break;
            case R.id.imageView5:
                handleInput(iv);
                break;
            case R.id.imageView6:
                handleInput(iv);
                break;
            case R.id.imageView7:
                handleInput(iv);
                break;
            case R.id.imageView8:
                handleInput(iv);
                break;
        }
    }

    public void handleInput(ImageView iv){
        if(humanTurn){
            iv.setImageResource(R.drawable.black_circle);
            humanTurn = false;
        }
        else{
            iv.setImageResource(R.drawable.black_cross);
            humanTurn = true;
        }
    }
}
