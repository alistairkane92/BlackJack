package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RebuyActivity extends AppCompatActivity {
    EditText rebuyAmount;
    Button rebuyBtn;
    Bundle extras;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebuy);

        extras = getIntent().getExtras();
        name = extras.getString("name");

        rebuyAmount = (EditText) findViewById(R.id.buyInAmountEt);
        rebuyBtn = (Button) findViewById(R.id.buyInBtn);
    }

    public void goToGame(View view) {
        Intent i = new Intent(this, GameActivity.class);
        Integer reBuy = Integer.parseInt(rebuyAmount.getText().toString().toUpperCase());
        i.putExtra("name", name);
        i.putExtra("newFunds", reBuy);
        startActivity(i);
    }
}
