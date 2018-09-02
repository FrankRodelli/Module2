package com.bignerdranch.android.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Sessions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        setTitle("Sessions");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO: Open Create Sessions fragment/activity here somehow
                // Intent intent = new Intent(MainActivity.this, Sessions.class);
                // startActivity(intent);
            }
        });

       //Populate list with sessions
        ListView myListView = findViewById(R.id.session_list);
        final ArrayList<String> sessionItems = new ArrayList<String>();

        for (int i = 0; i < 20; i++){
            sessionItems.add("Sample session");
        }

        final ArrayAdapter<String > aa;
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,sessionItems);

        myListView.setAdapter(aa);
    }

}
