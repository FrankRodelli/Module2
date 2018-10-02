package com.bignerdranch.android.module2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class Customers extends AppCompatActivity {

    private ModuleFiveDatabase mfd;
    private ListView myListView;

    private ImageView mCustomerImage;

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
                startActivityForResult(intent,0);
            }
        });

        populateListView();

        //TODO: Add the ability to pass what customer you've clicked on to display proper content
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Customers.this,CustomerView.class);
                startActivity(intent);
            }
        });

        mCustomerImage = findViewById(R.id.customerImage);

        /*try {
            FileInputStream cusim = openFileInput("Frank.jpeg");
        } catch (FileNotFoundException e) {
            e.printar;
        }*/

        Log.e("Here", Arrays.toString(fileList()));
    }

    private void populateListView() {
        mfd = new ModuleFiveDatabase(this);

        Cursor cursor = mfd.getCustotmers();

        //Populate list with sessions
        myListView = findViewById(R.id.session_list);
        final ArrayList<String> sessionItems = new ArrayList<String>();
        final ArrayList<String> sessionIcons = new ArrayList<>();


        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            String customer = cursor.getString(1) + " " +
                    cursor.getString(2);
            Log.d("this is the customer", customer);
            sessionItems.add(customer);

            //add items to customer picture arraylist
            String icon = cursor.getString(1) + ".jpg";
            sessionIcons.add(icon);

        }

        myListView.setAdapter(new ArrayAdapter<String>(
                this, R.layout.customer_list, R.id.Itemname, sessionItems));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        populateListView();
        myListView.invalidate();
    }
}
