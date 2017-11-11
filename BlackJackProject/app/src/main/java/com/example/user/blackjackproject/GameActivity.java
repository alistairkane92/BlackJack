package com.example.user.blackjackproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    Button startButton;
    Game newGame;
    Deck deck;
    TextView handDisplayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        deck = new Deck();
        newGame = new Game(deck);
        startButton = (Button) findViewById(R.id.playBtn);
        handDisplayTv = (TextView) findViewById(R.id.handDisplayTv);
    }

    //steps of Game
    //startGame button should display user hand
    //user then picks stick or twist button
    //dealer logic gets carried out and we see their cards
    //show results win loss
    //update round counter


    public void startGame(View view) {
        newGame.deal();
        handDisplayTv.setText(newGame.showUserHand());
    }
}
