package com.example.galgelegreallyfinal;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;


public class Play extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Play";

    Galgelogik spil = new Galgelogik();
    TextView skjultOrd, tvScore;
    ImageView galgeIMG;
    AlertDialog.Builder afslutSpil;
    int score = 0, tempScore = 0;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

    // Sp til HS fyldt med score.

    private ArrayList<String> scoreArrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        //loadScoreData();

        Button menuBTN = (Button) findViewById(R.id.menuBTN);

        menuBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Play.this, MainActivity.class));
                finish();
            }
        });

        skjultOrd = (TextView) findViewById(R.id.skjultOrd);
        galgeIMG = (ImageView) findViewById(R.id.imgGalge);
        tvScore = (TextView) findViewById(R.id.textScore);
        tvScore.setText("Score: " + score);

        afslutSpil = new AlertDialog.Builder(this);

        // start spillet

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                spil.hentOrdFraDr();
                // spil.nulstil();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        skjultOrd.setText(spil.getSynligtOrd());
                    }
                });
                spil.logStatus();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Knapper med listener
        Button A = findViewById(R.id.A);
        A.setOnClickListener(this);

        Button B = findViewById(R.id.B);
        B.setOnClickListener(this);

        Button C = findViewById(R.id.C);
        C.setOnClickListener(this);

        Button D = findViewById(R.id.D);
        D.setOnClickListener(this);

        Button E = findViewById(R.id.E);
        E.setOnClickListener(this);

        Button F = findViewById(R.id.F);
        F.setOnClickListener(this);

        Button G = findViewById(R.id.G);
        G.setOnClickListener(this);

        Button H = findViewById(R.id.H);
        H.setOnClickListener(this);

        Button I = findViewById(R.id.I);
        I.setOnClickListener(this);

        Button J = findViewById(R.id.J);
        J.setOnClickListener(this);

        Button K = findViewById(R.id.K);
        K.setOnClickListener(this);

        Button L = findViewById(R.id.L);
        L.setOnClickListener(this);

        Button M = findViewById(R.id.M);
        M.setOnClickListener(this);

        Button N = findViewById(R.id.N);
        N.setOnClickListener(this);

        Button O = findViewById(R.id.O);
        O.setOnClickListener(this);

        Button P = findViewById(R.id.P);
        P.setOnClickListener(this);

        Button Q = findViewById(R.id.Q);
        Q.setOnClickListener(this);

        Button argh = findViewById(R.id.argh);
        argh.setOnClickListener(this);

        Button S = findViewById(R.id.S);
        S.setOnClickListener(this);

        Button T = findViewById(R.id.T);
        T.setOnClickListener(this);

        Button U = findViewById(R.id.U);
        U.setOnClickListener(this);

        Button V = findViewById(R.id.V);
        V.setOnClickListener(this);

        Button W = findViewById(R.id.W);
        W.setOnClickListener(this);

        Button X = findViewById(R.id.X);
        X.setOnClickListener(this);

        Button Y = findViewById(R.id.Y);
        Y.setOnClickListener(this);

        Button Z = findViewById(R.id.Z);
        Z.setOnClickListener(this);

    }

    private int points() {

        if (spil.erSidsteBogstavKorrekt()) {
            score = score + 10;
        } else {
            score = score - 10;
        }
        tvScore.setText("Score: " + score);

        return score;
    }

    private void slutSpil() {
        sp = getSharedPreferences("score_pref", Context.MODE_PRIVATE);
        edit = sp.edit();

        int stateIndic = 0;

        tempScore = score;

        if (spil.erSpilletVundet()) {
            stateIndic = 1;
            edit.putInt("userScore", tempScore);
            edit.putInt("userTries", spil.getAntalForsøg());
            edit.putInt("state", stateIndic);
            edit.commit();
            startActivity(new Intent(Play.this, GameOver.class));
            finish();
        } else if (spil.erSpilletTabt()){
            stateIndic = 0;
            edit.putInt("userScore", tempScore);
            edit.putString("prevWord", spil.getOrdet());
            edit.putInt("state", stateIndic);
            edit.commit();
            startActivity(new Intent(Play.this, GameOver.class));
            finish();
        }

        /*AlertDialog slutBesked;

        if (spil.erSpilletVundet()) {

            afslutSpil.setMessage("You Have won! with a score of: " + score + "\nYou used " + spil.getAntalForsøg() + " tries.");
            afslutSpil.setCancelable(true);

            afslutSpil.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                   startActivity(new Intent(Play.this, Play.class));
                    dialog.cancel();
                    spil.nulstil();
                    skjultOrd.setText(spil.getSynligtOrd());
                    resetKeyboard();
                    tvScore.setText("Score: " + score);
                    spil.logStatus();
                  //  finish();
                }
            });

            afslutSpil.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(Play.this, MainActivity.class));
                    dialog.cancel();
                    scoreData();
                    finish();
                }
            });

            slutBesked = afslutSpil.create();
            slutBesked.show();


        } else if (spil.erSpilletTabt()) {

            afslutSpil.setMessage("You Have Lost! with a score of " + score + "\nThe word was '" + spil.getOrdet() + "'");
            afslutSpil.setCancelable(true);

            afslutSpil.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                   // startActivity(new Intent(Play.this, Play.class));
                    dialog.cancel();
                    spil.nulstil();
                    skjultOrd.setText(spil.getSynligtOrd());
                    resetKeyboard();
                    tvScore.setText("Score: " + score);
                    spil.logStatus();
                   // finish();
                }
            });

            afslutSpil.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(Play.this, MainActivity.class));
                    dialog.cancel();
                    scoreData();
                    finish();
                }
            });

            slutBesked = afslutSpil.create();
            slutBesked.show();
        }
                */

    }

    private void imgChanger() {
        switch (spil.getAntalForkerteBogstaver()) {
            case 0:
                galgeIMG.setImageResource(R.drawable.galge);
                break;
            case 1:
                galgeIMG.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                galgeIMG.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                galgeIMG.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                galgeIMG.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                galgeIMG.setImageResource(R.drawable.forkert5);
                break;
            case 7:
                galgeIMG.setImageResource(R.drawable.forkert6);
                break;
        }
    }

    // nåede ikke at få det til at virke.
    private void scoreData() {
        sp = getSharedPreferences("score_pref", Context.MODE_PRIVATE);
        edit = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(scoreArrList);
        edit.putString("scoreList", json);
        edit.putInt("userScore", tempScore);
        edit.commit();
    }

    private void loadScoreData() {
        edit = sp.edit();
        Gson gson = new Gson();
        String json = sp.getString("score_pref", null);
        Type type = new TypeToken<ArrayList<ScoreExample>>() {
        }.getType();
        scoreArrList = gson.fromJson(json, type);

        if (scoreArrList == null) {
            scoreArrList = new ArrayList<>();
        }
    }

    // gør dette når der trykkes
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.A:
                spil.gætBogstav("a");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.B:
                spil.gætBogstav("b");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.C:
                spil.gætBogstav("c");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.D:
                spil.gætBogstav("d");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.E:
                spil.gætBogstav("e");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.F:
                spil.gætBogstav("f");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.G:
                spil.gætBogstav("g");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.H:
                spil.gætBogstav("h");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.I:
                spil.gætBogstav("i");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.J:
                spil.gætBogstav("j");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.K:
                spil.gætBogstav("k");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.L:
                spil.gætBogstav("l");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.M:
                spil.gætBogstav("m");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.N:
                spil.gætBogstav("n");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.O:
                spil.gætBogstav("o");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.P:
                spil.gætBogstav("p");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.Q:
                spil.gætBogstav("q");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.argh:
                spil.gætBogstav("r");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.S:
                spil.gætBogstav("s");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.T:
                spil.gætBogstav("t");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.U:
                spil.gætBogstav("u");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.V:
                spil.gætBogstav("v");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.W:
                spil.gætBogstav("w");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.X:
                spil.gætBogstav("x");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.Y:
                spil.gætBogstav("y");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            case R.id.Z:
                spil.gætBogstav("z");
                skjultOrd.setText(spil.getSynligtOrd());
                points();
                imgChanger();
                slutSpil();
                spil.logStatus();
                v.getBackground().setAlpha(75);
                v.setEnabled(false);
                break;
            default:
                System.out.println("Unavailable");
                break;
        }
    }

    public void resetgame() {
        scoreArrList.add(String.valueOf(score));
        score = 0;
        spil.nulstil();
        skjultOrd.setText(spil.getSynligtOrd());
        tvScore.setText("Score: " + score);
        spil.logStatus();

        View v;
        v = findViewById(R.id.A);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.B);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.C);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.D);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.E);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.F);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.G);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.H);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.I);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.J);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.K);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.L);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.M);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.N);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.O);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.P);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.Q);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.argh);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.S);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.T);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.U);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.V);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.W);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.X);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.Y);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);

        v = findViewById(R.id.Z);
        v.getBackground().setAlpha(250);
        v.setEnabled(true);
    }
}

