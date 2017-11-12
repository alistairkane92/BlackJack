package com.example.user.blackjackproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



public class GameActivity extends AppCompatActivity {
    ImageView camera;

    ImageView userCard1;
    ImageView userCard2;
    ImageView dealerCard1;
    ImageView dealerCard2;

    ImageView dealerCard1Suit1;
    ImageView dealerCard1Suit2;
    ImageView dealerCard2Suit1;
    ImageView dealerCard2Suit2;

    ImageView userCard1Suit1;
    ImageView userCard1Suit2;
    ImageView userCard2Suit1;
    ImageView userCard2Suit2;


    Button placeBetBtn;
    Button stickBtn;
    Button twistBtn;
    Button rebuyBtn;
    Button btnCamera;

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
    TextView name;

    TextView cardOneTv;
    TextView cardTwoTv;
    TextView dealerCardOneTv;
    TextView dealerCardTwoTv;


    SeekBar betBar;

    private void initializeVariables(){

        //buttons
        placeBetBtn = (Button) findViewById(R.id.placeBetBtn);
        stickBtn = (Button) findViewById(R.id.stickBtn);
        twistBtn = (Button) findViewById(R.id.twistBtn);
        rebuyBtn = (Button) findViewById(R.id.rebuyButton);
        btnCamera = (Button) findViewById(R.id.btnCapture);

        //card imageviews
        userCard1 = (ImageView) findViewById(R.id.userCard1);
        userCard2 = (ImageView) findViewById(R.id.userCard2);
        dealerCard1 = (ImageView) findViewById(R.id.dealerCard1);
        dealerCard2 = (ImageView) findViewById(R.id.dealerCard2);

        //card suit imageviews
        userCard1Suit1 = (ImageView) findViewById(R.id.card1suit1);
        userCard1Suit2 = (ImageView) findViewById(R.id.card1suit2);
        userCard2Suit1 = (ImageView) findViewById(R.id.card2suit1);
        userCard2Suit2 = (ImageView) findViewById(R.id.card2suit2);

        dealerCard1Suit1 = (ImageView) findViewById(R.id.dealercard1suit1);
        dealerCard1Suit2 = (ImageView) findViewById(R.id.dealercard1suit2);
        dealerCard2Suit1 = (ImageView) findViewById(R.id.dealercard2suit1);
        dealerCard2Suit2 = (ImageView) findViewById(R.id.dealercard2suit2);

        //card Tvs
        cardOneTv = (TextView) findViewById(R.id.cardOneTv);
        cardTwoTv = (TextView) findViewById(R.id.cardTwoTv);
        dealerCardOneTv = (TextView) findViewById(R.id.dealerCardOneTv);
        dealerCardTwoTv = (TextView) findViewById(R.id.dealerCardTwoTv);

        //other images

        betBar = (SeekBar) findViewById(R.id.pickABetSb);
        handDisplayTv = (TextView) findViewById(R.id.handDisplayTv);
        handValueTv = (TextView) findViewById(R.id.handValueTv);
        dealerHandTv = (TextView) findViewById(R.id.dealerHandDisplayTv);
        dealerHandValueTv = (TextView) findViewById(R.id.dealerHandValueTv);
        showWinnerTv = (TextView) findViewById(R.id.showWinnerTv);
        checkBustTv = (TextView) findViewById(R.id.checkBustTv);
        showFundsTv = (TextView) findViewById(R.id.displayFunds);
        selectedBetTv = (TextView) findViewById(R.id.selectedBetTV);
        name = (TextView) findViewById(R.id.nameTv);
        camera = (ImageView) findViewById((R.id.imageView));

        betPlaced = 0;
        newFunds = 0;

        deck = new Deck();

        newGame = new Game(deck);
        extras = getIntent().getExtras();
        newFunds = extras.getInt("newFunds");
        newGame.setUserFunds(newFunds);

        String newName = extras.getString("name");
        newGame.setUserName(newName);
        name.setText(newName);

        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));
    }

//    public void changeCardViewIfCardValueIsSymbol(){
//        if (newGame.getPlayers().get(0).getHand().getCards().get(0).getRank().equals("Jack") && newGame.getPlayers().get(0).getHand().getCards().get(0).getSuit().equals(Suit.CLUBS) || newGame.getPlayers().get(0).getHand().getCards().get(0).getSuit().equals(Suit.SPADES)){
//            userCard1.setImageResource(R.drawable.Jb);
//        } else if
/// /    }

    public void makeCardSuitsInvisible(){
        userCard1Suit1.setVisibility(View.INVISIBLE);
        userCard1Suit2.setVisibility(View.INVISIBLE);
        userCard2Suit1.setVisibility(View.INVISIBLE);
        userCard2Suit2.setVisibility(View.INVISIBLE);

        dealerCard1Suit1.setVisibility(View.INVISIBLE);
        dealerCard1Suit2.setVisibility(View.INVISIBLE);
        dealerCard2Suit1.setVisibility(View.INVISIBLE);
        dealerCard2Suit2.setVisibility(View.INVISIBLE);
    }

    public void makeCardsVisible(){
        userCard1Suit1.setVisibility(View.VISIBLE);
        userCard1Suit2.setVisibility(View.VISIBLE);
        userCard2Suit1.setVisibility(View.VISIBLE);
        userCard2Suit2.setVisibility(View.VISIBLE);

        dealerCard1Suit1.setVisibility(View.VISIBLE);
        dealerCard1Suit2.setVisibility(View.VISIBLE);
    }

    public void revealDealerCard(){
        dealerCard2.setImageResource(android.R.color.white);
        dealerCard2Suit1.setVisibility(View.VISIBLE);
        dealerCard2Suit2.setVisibility(View.VISIBLE);
    }

    public void makeCardsWhite(){
        userCard1.setImageResource(android.R.color.white);
        userCard2.setImageResource(android.R.color.white);
        dealerCard1.setImageResource(android.R.color.white);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        initializeVariables();
        makeCardSuitsInvisible();

        btnCamera.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view){
              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivityForResult(intent, 0);

          }
        });

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);
        makeRebuyVisibleIfBust();

        betBar.setMax(newGame.showUserFunds());

        selectedBetTv.setText(betBar.getProgress() + "/" + betBar.getMax());

        //onSeekBar is used to keep track of the value of the seekBar


        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                selectedBetTv.setText(Integer.toString(progress) + "/" + newGame.showUserFunds());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                selectedBetTv.setText(+ progress + "/" + newGame.showUserFunds());
                Toast.makeText(getApplicationContext(), "Are you sure that's enough?", Toast.LENGTH_SHORT).show();
                betPlaced = progress;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        camera.setImageBitmap(bitmap);
    }

    public void makeRebuyVisibleIfBust(){
        if (newGame.showUserFunds() <= 0){
            rebuyBtn.setVisibility(View.VISIBLE);
            stickBtn.setVisibility(View.INVISIBLE);
            twistBtn.setVisibility(View.INVISIBLE);
            handDisplayTv.setVisibility(View.INVISIBLE);
            placeBetBtn.setVisibility(View.INVISIBLE);
        }
    }

    public void endOfRound(){
        placeBetBtn.setVisibility(View.VISIBLE);
        betBar.setVisibility(View.VISIBLE);
        selectedBetTv.setVisibility(View.VISIBLE);

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);

        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));
        betBar.setMax(newGame.showUserFunds());

        makeRebuyVisibleIfBust();

        if (!newGame.getPlayers().get(0).getHand().checkBust()){
            Toast.makeText(getApplicationContext(), "Place your bet please!", Toast.LENGTH_LONG).show();
        }

    }

    public void displayUserCardOne(){
        if (newGame.getPlayers().get(0).getHand().getCards().get(0).getSuitImage() == 1){
            userCard1Suit1.setImageResource(R.drawable.heart);
            userCard1Suit2.setImageResource(R.drawable.heart);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(0).getSuitImage() == 2){
            userCard1Suit1.setImageResource(R.drawable.diamond);
            userCard1Suit2.setImageResource(R.drawable.diamond);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(0).getSuitImage() == 3){
            userCard1Suit1.setImageResource(R.drawable.club);
            userCard1Suit2.setImageResource(R.drawable.club);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(0).getSuitImage() == 4){
            userCard1Suit1.setImageResource(R.drawable.spade);
            userCard1Suit2.setImageResource(R.drawable.spade);
        }

        cardOneTv.setText(newGame.showUserCardOneValue());
    }

    public void displayUserCardTwo(){
        if (newGame.getPlayers().get(0).getHand().getCards().get(1).getSuitImage() == 1){
            userCard2Suit1.setImageResource(R.drawable.heart);
            userCard2Suit2.setImageResource(R.drawable.heart);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(1).getSuitImage() == 2){
            userCard2Suit1.setImageResource(R.drawable.diamond);
            userCard2Suit2.setImageResource(R.drawable.diamond);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(1).getSuitImage() == 3) {
            userCard2Suit1.setImageResource(R.drawable.club);
            userCard2Suit2.setImageResource(R.drawable.club);
        } else if (newGame.getPlayers().get(0).getHand().getCards().get(1).getSuitImage() == 4) {
            userCard2Suit1.setImageResource(R.drawable.spade);
            userCard2Suit2.setImageResource(R.drawable.spade);
        }

        cardTwoTv.setText(newGame.showUserCardTwoValue());
    }

    public void displayDealerCardOne(){
        if (newGame.getPlayers().get(1).getHand().getCards().get(0).getSuitImage() == 1){
            dealerCard1Suit1.setImageResource(R.drawable.heart);
            dealerCard1Suit2.setImageResource(R.drawable.heart);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(0).getSuitImage() == 2){
            dealerCard1Suit1.setImageResource(R.drawable.diamond);
            dealerCard1Suit2.setImageResource(R.drawable.diamond);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(0).getSuitImage() == 3) {
            dealerCard1Suit1.setImageResource(R.drawable.club);
            dealerCard1Suit2.setImageResource(R.drawable.club);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(0).getSuitImage() == 4) {
            dealerCard1Suit1.setImageResource(R.drawable.spade);
            dealerCard1Suit2.setImageResource(R.drawable.spade);
        }

        dealerCardOneTv.setText(newGame.showDealerCardOneValue());
    }

    public void displayDealerCardTwo(){
        if (newGame.getPlayers().get(1).getHand().getCards().get(1).getSuitImage() == 1){
            dealerCard2Suit1.setImageResource(R.drawable.heart);
            dealerCard2Suit2.setImageResource(R.drawable.heart);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(1).getSuitImage() == 2){
            dealerCard2Suit1.setImageResource(R.drawable.diamond);
            dealerCard2Suit2.setImageResource(R.drawable.diamond);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(1).getSuitImage() == 3) {
            dealerCard2Suit1.setImageResource(R.drawable.club);
            dealerCard2Suit2.setImageResource(R.drawable.club);
        } else if (newGame.getPlayers().get(1).getHand().getCards().get(1).getSuitImage() == 4) {
            dealerCard2Suit1.setImageResource(R.drawable.spade);
            dealerCard2Suit2.setImageResource(R.drawable.spade);
        }

        dealerCardTwoTv.setText(newGame.showDealerCardTwoValue());
    }
    public void dealerMove(){
        //Dealer Move
        revealDealerCard();
        displayDealerCardTwo();
        newGame.dealerMove();

        //update Views
        dealerHandTv.setText(newGame.showDealerHand());
        dealerHandValueTv.setText(newGame.showDealerHandValue().toString());
        showWinnerTv.setText(newGame.displayWinner().toString() + " wins!");

        newGame.payOut(betPlaced);
    }

    public void stick(View view) {
        placeBetBtn.setVisibility(View.VISIBLE);
        betBar.setVisibility(View.VISIBLE);
        selectedBetTv.setVisibility(View.VISIBLE);

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);

        dealerMove();
        endOfRound();
    }

    public void twist(View view) {
        newGame.twist();
        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(Integer.toString(newGame.showUserHandValue()));
        checkBustTv.setText(newGame.displayBust());

        if (newGame.displayBust().equals("You're BUST!")){
            dealerMove();
            endOfRound();
        }

        //deals new card and displays new result
        //if bust needs to do dealer logic anyway
    }

    public void placeBet(View view) {
        newGame.placeBet(betPlaced);
        betBar.setVisibility(View.INVISIBLE);
        placeBetBtn.setVisibility(View.INVISIBLE);
        selectedBetTv.setVisibility(View.INVISIBLE);

        stickBtn.setVisibility(View.VISIBLE);
        twistBtn.setVisibility(View.VISIBLE);
        handValueTv.setVisibility(View.VISIBLE);


        dealerHandTv.setText("");
        dealerHandValueTv.setText("");
        showWinnerTv.setText("");
        checkBustTv.setText("");
        showFundsTv.setText(Integer.toString(newGame.showUserFunds()));


        newGame.deal();
        makeCardsVisible();
        makeCardsWhite();
        displayUserCardOne();
        displayUserCardTwo();
        displayDealerCardOne();

        handDisplayTv.setText(newGame.showUserHand());
        handValueTv.setText(newGame.showUserHandValue().toString());

        betBar.setMax(newGame.showUserFunds());
    }

    public void reBuyPage(View view) {
        Intent i = new Intent(this, RebuyActivity.class);
        startActivity(i);
    }

}
