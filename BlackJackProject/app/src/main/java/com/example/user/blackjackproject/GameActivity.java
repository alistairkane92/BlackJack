package com.example.user.blackjackproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    Button startButton;
    Game newGame;
    Deck deck;
    Integer betPlaced;

    TextView handDisplayTv;
    TextView handValueTv;
    TextView dealerHandTv;
    TextView dealerHandValueTv;
    TextView showWinnerTv;
    TextView checkBustTv;
    TextView showFundsTv;
    TextView selectedBetTv;

    SeekBar betBar;

    private void initializeVariables(){
        startButton = (Button) findViewById(R.id.playBtn);
        betBar = (SeekBar) findViewById(R.id.pickABetSb);
        handDisplayTv = (TextView) findViewById(R.id.handDisplayTv);
        handValueTv = (TextView) findViewById(R.id.handValueTv);
        dealerHandTv = (TextView) findViewById(R.id.dealerHandDisplayTv);
        dealerHandValueTv = (TextView) findViewById(R.id.dealerHandValueTv);
        showWinnerTv = (TextView) findViewById(R.id.showWinnerTv);
        checkBustTv = (TextView) findViewById(R.id.checkBustTv);
        showFundsTv = (TextView) findViewById(R.id.displayFunds);
        showFundsTv.setText(newGame.showUserFunds().toString());
        selectedBetTv = (TextView) findViewById(R.id.selectedBetTV);
        betPlaced = 0;
        deck = new Deck();
        newGame = new Game(deck);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);
        initializeVariables();

        selectedBetTv.setText("Bet amount: " + betBar.getProgress() + "/" + betBar.getMax());


        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                Toast.makeText(getApplicationContext(), "Why don't you bet a little bit more?", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "PLACE YOUR BET!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                selectedBetTv.setText("Bet amount: " + progress + "/" + betBar.getMax());
                Toast.makeText(getApplicationContext(), "Are you sure that's enough?", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //steps of Game
    //startGame button should display user hand
    //user then picks stick or twist button
    //dealer logic gets carried out and we see their cards
    //show results win loss
    //update round counter


    public void startGame(View view) {
        dealerHandTv.setText("");
        dealerHandValueTv.setText("");
        showWinnerTv.setText("");
        checkBustTv.setText("");
        showFundsTv.setText(newGame.showUserFunds().toString());


        newGame.deal();
        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(newGame.showUserHandValue().toString());
    }


    public void stick(View view) {
        newGame.stick();
        betPlaced = 5;
        newGame.placeBet(betPlaced);

        //Dealer Move
        newGame.dealerMove();

        //update Views
        dealerHandTv.setText(newGame.showDealerHand());
        dealerHandValueTv.setText(newGame.showDealerHandValue().toString());
        showWinnerTv.setText(newGame.displayWinner().toString() + " wins!");

        newGame.payOut(betPlaced);
        showFundsTv.setText(newGame.showUserFunds().toString());

    }


    public void twist(View view) {
        newGame.twist();
        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(newGame.showUserHandValue().toString());
        checkBustTv.setText(newGame.displayBust());

        //deals new card and displays new result
    }
}
