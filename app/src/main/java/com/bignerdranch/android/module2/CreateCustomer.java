package com.bignerdranch.android.module2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);

        setTitle("Create Customer");
    }
}