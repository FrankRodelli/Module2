package com.bignerdranch.android.module2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateCustomer extends AppCompatActivity {

    private Button mAddCustomerButton;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private ModuleFiveDatabase mfd;
    private String mFirstName;
    private String mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);

        setTitle("Create Customer");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement camera functionality
                Toast.makeText(CreateCustomer.this, "This will allow you to take" +
                                " customer picture with fragment overlay",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mAddCustomerButton = findViewById(R.id.create_customer_button);
        mFirstNameEditText = findViewById(R.id.firstNameInput);
        mLastNameEditText = findViewById(R.id.lastNameInput);

        mfd = new ModuleFiveDatabase(this);

        mAddCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirstName = mFirstNameEditText.getText().toString();
                mLastName = mLastNameEditText.getText().toString();

                mfd.addCustomer(mFirstName,mLastName);

                //Just here for testing
                Log.e("Customer added", mFirstName+ " " + mLastName);
                String newList = DatabaseUtils.dumpCursorToString(mfd.getCustotmers());
                Log.e("This is the list", newList);

                //Back to customer page
                finish();
            }
        });
    }
}
