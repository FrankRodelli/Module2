package com.bignerdranch.android.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Customers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        setTitle("Customers");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customers.this, CreateCustomer.class);
                startActivity(intent);
            }
        });


        //Populate list with sessions
        ListView myListView = findViewById(R.id.session_list);
        final ArrayList<String> sessionItems = new ArrayList<String>();

        for (int i = 0; i < 20; i++){
            sessionItems.add("Sample Customer");
        }

        final ArrayAdapter<String > aa;
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,sessionItems);

        myListView.setAdapter(aa);

        //TODO: Add the ability to pass what customer you've clicked on to display proper content
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Customers.this,CustomerView.class);
                startActivity(intent);
            }
        });
    }

}
