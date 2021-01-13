package com.example.galgelegreallyfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity implements View.OnClickListener {

    Button menuBTN, newgameBTN;
    TextView winORloseTV, endgameInfo;
    ImageView endGameimg;

    // skal bruge userScore, userTries, prevWord, state
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    int userScore, userTries, state;
    String prevWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_layout);

        menuBTN = findViewById(R.id.MenuBTN2);
        newgameBTN = findViewById(R.id.newGame);

        menuBTN.setOnClickListener(this);
        newgameBTN.setOnClickListener(this);

        winORloseTV = findViewById(R.id.winLosetext);
        endgameInfo = findViewById(R.id.EndGameInfo);

        endGameimg = findViewById(R.id.endGameimg);

        sp = getSharedPreferences("score_pref", Context.MODE_PRIVATE);

        userScore = sp.getInt("userScore", 0);
        userTries = sp.getInt("userTries", 0);
        state = sp.getInt("state", -1);

        prevWord = sp.getString("prevWord", null);

        if (state == 1){
            winORloseTV.setText("You have won!\nwith a score of: " + userScore);
            endGameimg.setImageResource(R.drawable.winemoji);
            endgameInfo.setText("You have used " + userTries + " tries.");
        } else if (state == 0) {
            winORloseTV.setText("You have lost!");
            endGameimg.setImageResource(R.drawable.loseemoji);
            endgameInfo.setText("the word was '" + prevWord + "'.");
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.MenuBTN2:
                startActivity(new Intent(GameOver.this, MainActivity.class));
                finish();
                break;
            case R.id.newGame:
                startActivity(new Intent(GameOver.this, Play.class));
                finish();
        }

    }
}
