package com.bignerdranch.android.module2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mCustomersButton;
    private Button mSessionsButton;
    private Button mLogoutButton;
    private Button mExitButton;
    private ModuleFiveDatabase mfd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign buttons to variables
        mCustomersButton = findViewById(R.id.customers_button);
        mSessionsButton = findViewById(R.id.sessions_button);
        mLogoutButton = findViewById(R.id.logout_button);
        mExitButton = findViewById(R.id.exit_button);
        mfd = new ModuleFiveDatabase(this);

        setTitle("Home");

        mCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Customers.class);
                startActivity(intent);
            }
        });

        mSessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sessions.class);
                startActivity(intent);
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do something when clicked
            }
        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfd.purge();
            }
        });
    }

}
