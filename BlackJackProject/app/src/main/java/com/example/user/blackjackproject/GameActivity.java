package com.example.user.blackjackproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    Button startButton;
    Game newGame;
    Deck deck;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        newGame = new Game(deck);
        dealer = new Dealer("Villain");
        startButton = (Button) findViewById(R.id.playBtn);
    }

    public void startGame(View view) {
        newGame.play();


    }
}
