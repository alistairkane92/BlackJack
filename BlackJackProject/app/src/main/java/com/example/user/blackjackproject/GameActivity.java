package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    Button startButton;
    Game newGame;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        newGame = new Game(deck);
        startButton = (Button) findViewById(R.id.play);
    }

    public void startGame(View view) {
        newGame.play();

        
    }
}
