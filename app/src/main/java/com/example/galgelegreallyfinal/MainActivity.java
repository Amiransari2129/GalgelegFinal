package com.example.galgelegreallyfinal;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menu knapper
        Button playBTN = (Button) findViewById(R.id.playBTN);
        Button highscoreBTN = (Button) findViewById(R.id.highscoreBTN);
        Button helpBTN = (Button) findViewById(R.id.helpBTN);
        Button wordlistBTN = (Button) findViewById(R.id.wordlistBTN);

        // sæt click listener på
        playBTN.setOnClickListener(this);
        highscoreBTN.setOnClickListener(this);
        helpBTN.setOnClickListener(this);
        wordlistBTN.setOnClickListener(this);


    }

    // skift activity ved tryk på knap
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playBTN:

                startActivity(new Intent(MainActivity.this, Play.class));
                break;
            case R.id.highscoreBTN:
                startActivity(new Intent(MainActivity.this, Highscore.class));
                break;
            case R.id.helpBTN:
                startActivity(new Intent(MainActivity.this, Help.class));
                break;
            case R.id.wordlistBTN:
                startActivity(new Intent(MainActivity.this, WordList.class));
                break;
        }
    }
}
