package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReBuyActivity extends AppCompatActivity {
    Button reBuyBtn;
    EditText reBuyAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_buy);

        reBuyBtn = (Button) findViewById(R.id.buyInBtn);
        reBuyAmount = (EditText) findViewById(R.id.buyInAmount);
    }

    public void goToGame(View view) {
        Integer reBuy = Integer.parseInt(reBuyAmount.getText().toString());

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("newFunds", reBuy);
        startActivity(i);

    }
}
