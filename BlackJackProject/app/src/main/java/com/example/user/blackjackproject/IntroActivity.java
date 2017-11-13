package com.example.user.blackjackproject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        name = (TextView) findViewById(R.id.nameTv);

    }

    public void goToMain(View view) {
        Intent i = new Intent(this, RebuyActivity.class);
        String passableName = name.getText().toString().toUpperCase();
        i.putExtra("name", passableName);
        startActivity(i);
    }

}
