package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        name = (TextView) findViewById(R.id.nameTv);

    }

    public void goToMain(View view) {
        Intent i = new Intent(this, BuyInActivity.class);
        String passableName = name.getText().toString().toUpperCase();

        if (passableName.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your name (or make one up)", Toast.LENGTH_SHORT).show();
             } else {
            i.putExtra("name", passableName);
            startActivity(i);
        }
    }
}
