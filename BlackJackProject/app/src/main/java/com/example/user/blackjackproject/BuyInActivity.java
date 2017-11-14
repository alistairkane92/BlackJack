package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyInActivity extends AppCompatActivity {
    EditText rebuyAmount;
    Button rebuyBtn;
    Bundle extras;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_in);


        extras = getIntent().getExtras();
        name = extras.getString("name");

        rebuyAmount = (EditText) findViewById(R.id.buyInAmount);
        rebuyBtn = (Button) findViewById(R.id.buyInBtn);
    }

    public void goToGame(View view) {
        Intent i = new Intent(this, GameActivity.class);
        Integer reBuy = Integer.parseInt(rebuyAmount.getText().toString());

        if (reBuy > 0) {
            i.putExtra("name", name);
            i.putExtra("newFunds", reBuy);
            startActivity(i);
        } else  Toast.makeText(getApplicationContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
    }
}
