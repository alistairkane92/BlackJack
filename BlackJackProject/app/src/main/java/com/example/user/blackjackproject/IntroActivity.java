package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    Button goToMain;
    Bundle extras;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        name = (TextView) findViewById(R.id.nameTv);
        goToMain = (Button) findViewById(R.id.button);
    }

    public void goToMain(View view) {
        Intent i = new Intent(this, RebuyActivity.class);
        i.putExtra("name", RebuyActivity.class);
        startActivity(i);
    }
}
