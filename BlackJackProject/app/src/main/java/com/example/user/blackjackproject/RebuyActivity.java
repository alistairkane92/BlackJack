package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RebuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebuy);
    }


    public void goToGame(View view) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("newFunds", R.id.rebuyAmountNum);
        startActivity(i);
    }
}
