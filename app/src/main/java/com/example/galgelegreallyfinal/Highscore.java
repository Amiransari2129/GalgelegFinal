package com.example.galgelegreallyfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Highscore extends AppCompatActivity {
    public static final String TAG = "Highscore";

    RecyclerView rv;
    RecyclerView.LayoutManager rvLayout;
    RecyclerView.Adapter rvAdapter;

    ArrayList<String> allHSList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hs_layout);

        TextView HSbig = findViewById(R.id.HighscoreTV);

        SharedPreferences sp = getSharedPreferences("score_pref", Context.MODE_PRIVATE);

        Button menuBTN = (Button) findViewById(R.id.menuBTN);

        menuBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Highscore.this, MainActivity.class));
                finish();
            }
        });
        String prevGameHS = String.valueOf(sp.getInt("userScore", 0));
        HSbig.setText("Previous game score: " + prevGameHS);

        // dummy data
        allHSList.add(prevGameHS);
        allHSList.add("1");
        allHSList.add("2");
        allHSList.add("3");
        allHSList.add("4");
        allHSList.add("5");

//        loadScoreData();


        rv = (RecyclerView) findViewById(R.id.hsRV);

        rvLayout = new LinearLayoutManager(this);

        //loadScoreData();
        Log.d(TAG, "allHSlist: " + allHSList);
        rvAdapter = new MainAdapter(allHSList);
        rv.setHasFixedSize(true); // performance sætter fixed size

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                rv.setLayoutManager(rvLayout);
                rv.setAdapter(rvAdapter);
            }
        });
    }

    private void loadScoreData() {
        SharedPreferences sp = getSharedPreferences("score_pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("scoreList", null);
        Type type = new TypeToken<ArrayList<ScoreExample>>() {
        }.getType();
        allHSList = gson.fromJson(json, type);

        if (allHSList == null){
            allHSList = new ArrayList<>();
        }
    }
}
