package com.example.user.blackjackproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    Button placeBetBtn;
    Button stickBtn;
    Button twistBtn;
    Button rebuyBtn;

    Bundle extras;
    Integer newFunds;

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
        placeBetBtn = (Button) findViewById(R.id.placeBetBtn);
        stickBtn = (Button) findViewById(R.id.stickBtn);
        twistBtn = (Button) findViewById(R.id.twistBtn);
        rebuyBtn = (Button) findViewById(R.id.rebuyButton);

        betBar = (SeekBar) findViewById(R.id.pickABetSb);
        handDisplayTv = (TextView) findViewById(R.id.handDisplayTv);
        handValueTv = (TextView) findViewById(R.id.handValueTv);
        dealerHandTv = (TextView) findViewById(R.id.dealerHandDisplayTv);
        dealerHandValueTv = (TextView) findViewById(R.id.dealerHandValueTv);
        showWinnerTv = (TextView) findViewById(R.id.showWinnerTv);
        checkBustTv = (TextView) findViewById(R.id.checkBustTv);
        showFundsTv = (TextView) findViewById(R.id.displayFunds);
        selectedBetTv = (TextView) findViewById(R.id.selectedBetTV);
        betPlaced = 0;
        newFunds = 0;

        deck = new Deck();

        newGame = new Game(deck);
        extras = getIntent().getExtras();
        newFunds = extras.getInt("newFunds");
        newGame.setUserFunds(newFunds);

        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);
        initializeVariables();

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);


        betBar.setMax(newGame.showUserFunds());

        selectedBetTv.setText("Bet amount: " + betBar.getProgress() + "/" + betBar.getMax());

        //onSeekBar is used to keep track of the value of the seekBar


        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                selectedBetTv.setText("Bet amount: " + Integer.toString(progress) + "/" + newGame.showUserFunds());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Are you sure that's enough?", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                selectedBetTv.setText("Bet amount: " + progress + "/" + newGame.showUserFunds());
                betPlaced = progress;
            }
        });
    }

    //steps of Game
    //startGame button should display user hand
    //user then picks stick or twist button
    //dealer logic gets carried out and we see their cards
    //show results win loss
    //update round counter


    public void makeRebuyVisibleIfBust(){
        if (newGame.showUserFunds() < 0){
            rebuyBtn.setVisibility(View.VISIBLE);
            stickBtn.setVisibility(View.INVISIBLE);
            twistBtn.setVisibility(View.INVISIBLE);
            handDisplayTv.setVisibility(View.INVISIBLE);



        }
    }

    public void stick(View view) {
        placeBetBtn.setVisibility(View.VISIBLE);
        betBar.setVisibility(View.VISIBLE);
        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);
        handValueTv.setVisibility(View.INVISIBLE);



        newGame.stick();
        newGame.placeBet(betPlaced);

        //Dealer Move
        newGame.dealerMove();

        //update Views
        dealerHandTv.setText(newGame.showDealerHand());
        dealerHandValueTv.setText(newGame.showDealerHandValue().toString());
        showWinnerTv.setText(newGame.displayWinner().toString() + " wins!");

        newGame.payOut(betPlaced);
        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));
        betBar.setMax(newGame.showUserFunds());

        makeRebuyVisibleIfBust();

    }

    public void twist(View view) {
        newGame.twist();
        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(Integer.toString(newGame.showUserFunds()));
        checkBustTv.setText(newGame.displayBust());
        //deals new card and displays new result
    }

    public void placeBet(View view) {
        betBar.setVisibility(View.INVISIBLE);
        placeBetBtn.setVisibility(View.INVISIBLE);
        stickBtn.setVisibility(View.VISIBLE);
        twistBtn.setVisibility(View.VISIBLE);


        dealerHandTv.setText("");
        dealerHandValueTv.setText("");
        showWinnerTv.setText("");
        checkBustTv.setText("");
        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));


        newGame.deal();
        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(newGame.showUserHandValue().toString());

        betBar.setMax(newGame.showUserFunds());
    }
}
