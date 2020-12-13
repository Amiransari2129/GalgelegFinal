package com.example.galgelegreallyfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Highscore extends AppCompatActivity {
    public static final String TAG = "Highscore";

    TextView HSbig = findViewById(R.id.textView5);
    int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hs_layout);

        Button menuBTN = (Button) findViewById(R.id.menuBTN);

        menuBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Highscore.this, MainActivity.class));
            }
        });

        int userScore = getIntent().getIntExtra("score",-1);
        HSbig.setText(userScore);


    }
}
