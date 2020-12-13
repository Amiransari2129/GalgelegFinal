package com.example.galgelegreallyfinal;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordList extends AppCompatActivity {
    public static final String TAG = "WordList";

    Galgelogik obj = new Galgelogik();

    RecyclerView rv;
    RecyclerView.LayoutManager rvLayout;
    RecyclerView.Adapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_layout);

        Button menuBTN = (Button) findViewById(R.id.menuBTN);

        menuBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WordList.this, MainActivity.class));
            }
        });

        // hent og vis ord
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                obj.hentOrdFraDr();
                //  Log.i("muligeord_wordList", obj.muligeOrd.toString());

                rv = (RecyclerView) findViewById(R.id.RecyclerViewWordList);

                rvLayout = new LinearLayoutManager(this);
                rvAdapter = new MainAdapter(obj.muligeOrd);
                rv.setHasFixedSize(true); // performance sætter fixed size

                // ny handler fordi vi ændrer den fra ikke UI thread.
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        rv.setLayoutManager(rvLayout);
                        rv.setAdapter(rvAdapter);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

}
