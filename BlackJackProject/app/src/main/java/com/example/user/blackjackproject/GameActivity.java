package com.example.user.blackjackproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
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
    private ImageView camera;
    private ImageView chips;

    //Cards
    private ImageView userCard1;
    private ImageView userCard2;
    private ImageView dealerCard1;
    private ImageView dealerCard2;
    private ImageView communalCard;

    //Card Suits
    private ImageView dealerCard1Suit2;
    private ImageView dealerCard2Suit2;
    private ImageView userCard1Suit2;
    private ImageView userCard2Suit2;
    private ImageView communalCardSuit;

    private ImageView sadFrog;

    private Button placeBetBtn;
    private Button stickBtn;
    private Button twistBtn;
    private Button rebuyBtn;
    private Button btnCamera;

    private Participant dealer;
    private Participant player;

    private Bundle extras;

    private Integer newFunds;
    private Integer beforeWinAmount;

    private Game newGame;
    private Deck deck;
    private Integer betPlaced;

    private TextView userTotalTv;
    private TextView dealerTotalTv;

    private TextView communalDrawTextTv;

    private TextView handValueTv;
    private TextView dealerHandValueTv;
    private TextView showWinnerTv;
    private TextView checkBustTv;
    private TextView dealerBust;
    private TextView showFundsTv;
    private TextView selectedBetTv;
    private TextView name;

    //Card Values
    private TextView cardOneTv;
    private TextView cardTwoTv;
    private TextView dealerCardOneTv;
    private TextView dealerCardTwoTv;
    private TextView communalCardTv;

    //Betting Bar
    private SeekBar betBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        initializeVariables();
        setUpViews();
        createBetBar();
        setUpCamera();

    }

    private void setUpCamera() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void initializeVariables() {

        //Buttons
        placeBetBtn = (Button) findViewById(R.id.placeBetBtn);
        stickBtn = (Button) findViewById(R.id.stickBtn);
        twistBtn = (Button) findViewById(R.id.twistBtn);
        rebuyBtn = (Button) findViewById(R.id.rebuyButton);
        btnCamera = (Button) findViewById(R.id.btnCapture);

        //Placeholder Card imageViews
        userCard1 = (ImageView) findViewById(R.id.userCard1);
        userCard2 = (ImageView) findViewById(R.id.userCard2);
        dealerCard1 = (ImageView) findViewById(R.id.dealerCard1);
        dealerCard2 = (ImageView) findViewById(R.id.dealerCard2);
        communalCard = (ImageView) findViewById(R.id.communalCard);


        //Suits next to value on card
        userCard1Suit2 = (ImageView) findViewById(R.id.card1suit2);
        userCard2Suit2 = (ImageView) findViewById(R.id.card2suit2);

        dealerCard1Suit2 = (ImageView) findViewById(R.id.dealercard1suit2);
        dealerCard2Suit2 = (ImageView) findViewById(R.id.dealercard2suit2);

        communalCardSuit = (ImageView) findViewById(R.id.communalCardSuit);

        //Display Value on cards
        cardOneTv = (TextView) findViewById(R.id.cardOneTv);
        cardTwoTv = (TextView) findViewById(R.id.cardTwoTv);
        dealerCardOneTv = (TextView) findViewById(R.id.dealerCardOneTv);
        dealerCardTwoTv = (TextView) findViewById(R.id.dealerCardTwoTv);
        communalCardTv = (TextView) findViewById(R.id.communalValueTv);

        //Sad frog if bust
        sadFrog = (ImageView) findViewById(R.id.sadFrog);

        //Image of chips in centre
        chips = (ImageView) findViewById(R.id.chips);

        //Bet Bar
        betBar = (SeekBar) findViewById(R.id.pickABetSb);

        //Total value text views
        handValueTv = (TextView) findViewById(R.id.handValueTv);
        dealerHandValueTv = (TextView) findViewById(R.id.dealerHandValueTv);

        //Communal "You Drew" text
        communalDrawTextTv = (TextView) findViewById(R.id.communalDrawTextTv);

        //Win or bust TextViews
        showWinnerTv = (TextView) findViewById(R.id.showWinnerTv);
        checkBustTv = (TextView) findViewById(R.id.checkBustTv);
        dealerBust = (TextView) findViewById(R.id.dealerBust);
        showFundsTv = (TextView) findViewById(R.id.displayFunds);
        selectedBetTv = (TextView) findViewById(R.id.selectedBetTV);
        name = (TextView) findViewById(R.id.nameTv);
        camera = (ImageView) findViewById((R.id.introImage));

        // Integer Init
        betPlaced = 0;
        newFunds = 0;

        //Game Init
        deck = new Deck();
        newGame = new Game(deck);

        player = (newGame.getPlayers().get(0));
        dealer = (newGame.getPlayers().get(1));

        //Extras from Buy in Page
        extras = getIntent().getExtras();
        newFunds = extras.getInt("newFunds");
        newGame.setUserFunds(newFunds);

        //Extras from Intro Page
        String newName = extras.getString("name");
        newGame.setUserName(newName);
        name.setText(newName);

        //Text containing "Total:"
        userTotalTv = (TextView) findViewById(R.id.userTotalTv);
        dealerTotalTv = (TextView) findViewById(R.id.dealerTotalTv);
    }

    public void setUpViews() {
        showWinnerTv.setVisibility(View.INVISIBLE);
        chips.setVisibility(View.INVISIBLE);
        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);
        sadFrog.setVisibility(View.INVISIBLE);

        handValueTv.setVisibility(View.GONE);
        dealerHandValueTv.setVisibility(View.GONE);
        //Make card suits invisible

        userCard1Suit2.setVisibility(View.INVISIBLE);
        userCard2Suit2.setVisibility(View.INVISIBLE);

        dealerCard1Suit2.setVisibility(View.INVISIBLE);
        dealerCard2Suit2.setVisibility(View.INVISIBLE);

        dealerTotalTv.setVisibility(View.INVISIBLE);
        userTotalTv.setVisibility(View.INVISIBLE);

        communalCard.setVisibility(View.INVISIBLE);
        communalCardSuit.setVisibility(View.INVISIBLE);
        communalDrawTextTv.setVisibility(View.INVISIBLE);
        communalCardTv.setVisibility(View.INVISIBLE);

        //Display user funds

        showFundsTv.setText("£" + Integer.toString(newGame.showUserFunds()));
    }


    public void createBetBar() {
        betBar.setMax(newGame.showUserFunds());

        selectedBetTv.setText("£" + betBar.getProgress() + "/" + "£" + betBar.getMax());

        //onSeekBar is used to keep track of the value of the seekBar


        betBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                selectedBetTv.setText("£" + Integer.toString(progress) + "/" + "£" + newGame.showUserFunds());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                selectedBetTv.setText("£" + progress + "/" + newGame.showUserFunds());
                if (progress < 5) {
                    Toast.makeText(getApplicationContext(), "Are you sure that's enough?", Toast.LENGTH_SHORT).show();
                }
                betPlaced = progress;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        camera.setImageBitmap(bitmap);
    }


    //Game Logic - Stick, Twist, Place Bet, Dealer Move


    public void stick(View view) {
        beforeWinAmount = (player.getFunds());

        placeBetBtn.setVisibility(View.INVISIBLE);
        betBar.setVisibility(View.INVISIBLE);
        selectedBetTv.setVisibility(View.INVISIBLE);

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);

        communalCard.setVisibility(View.INVISIBLE);
        communalCardSuit.setVisibility(View.INVISIBLE);
        communalDrawTextTv.setVisibility(View.INVISIBLE);
        communalCardTv.setVisibility(View.INVISIBLE);


        dealerMove();
    }

    public void twist(View view) {
        //deals new card and displays new result
        //if bust needs to do dealer logic anyway
        communalDrawTextTv.setVisibility(View.VISIBLE);
        communalDrawTextTv.setText("You drew:");
        communalCard.setVisibility(View.VISIBLE);
        communalCard.setImageResource(R.drawable.back);
        communalCardTv.setText("");
        communalCardSuit.setVisibility(View.INVISIBLE);

        newGame.twist();

        new CountDownTimer(1000, 1000) {
            public void onFinish() {

                beforeWinAmount = (player.getFunds());

                communalCard.setVisibility(View.VISIBLE);
                communalCard.setImageResource(android.R.color.white);
                communalCardSuit.setVisibility(View.VISIBLE);
                communalCardTv.setVisibility(View.VISIBLE);

                //sets communal card to most recent twist

                communalCardTv.setText(player.getHand().getCards().get(player.getHand().getNumberOfCards() - 1).getRank().getName());
                communalCardSuit.setImageResource(player.getHand().getCards().get(player.getHand().getNumberOfCards() - 1).getSuitImage());


                if (player.getHand().checkBust()){
                    sadFrog.setVisibility(View.VISIBLE);
                    checkBustTv.setText(newGame.displayBust());
                    dealerMove();
                }
                handValueTv.setText(Integer.toString(newGame.showUserHandValue()));

            }

            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }

    public void placeBet(View view) {
        if (betPlaced > 0) {
            newGame.placeBet(betPlaced);

            hideDealerCardTwo();

            resetTable();

            revealTheDeal();

            setChipImageSize();
            betBar.setMax(newGame.showUserFunds());
            chips.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid bet", Toast.LENGTH_SHORT).show();
        }
    }

    public void dealerMove() {
        displayDealerCardTwo();

        dealerHandValueTv.setVisibility(View.VISIBLE);
        dealerHandValueTv.setText(newGame.showDealerHandValue().toString());

        newGame.dealerMove();

        //communal card reveal step one


        if (dealer.getHand().getNumberOfCards() > 2) {
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    //update Views
                    communalCard.setVisibility(View.VISIBLE);
                    communalCard.setImageResource(R.drawable.back);
                    communalCardSuit.setVisibility(View.VISIBLE);
                    communalCardTv.setVisibility(View.VISIBLE);
                    communalDrawTextTv.setVisibility(View.VISIBLE);
                    communalDrawTextTv.setText("Dealer drew:");
                    communalCard.setImageResource(android.R.color.white);
                    communalCardTv.setText(dealer.getHand().getCards().get(dealer.getHand().getNumberOfCards() - 1).getRank().getName());
                }
            }.start();
        }

        new CountDownTimer(2000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                dealerTotalTv.setVisibility(View.VISIBLE);
                dealerHandValueTv.setVisibility(View.VISIBLE);
                dealerHandValueTv.setText(newGame.showDealerHandValue().toString());
                sadFrog.setVisibility(View.INVISIBLE);
                newGame.payOut(betPlaced);
                showWinnerTv.setVisibility(View.VISIBLE);
                showWinnerTv.setText(newGame.displayWinner().toString() + " wins!");

                if (dealer.getHandValue() > 21 && dealer.getHandValue() < 21) {
                    dealerBust.setText("Dealer is Bust!");
                }

                endOfRound();
            }
        }.start();

    }


    //VIEW CHANGES

    public void setChipImageSize(){
        chips.setVisibility(View.VISIBLE);
        if (betPlaced < 100) {
            chips.setImageResource(R.drawable.somechips);
        } else if (betPlaced < 250) {
            chips.setImageResource(R.drawable.morechips);
        } else if (betPlaced >= 251) {
            chips.setImageResource(R.drawable.all_in);
        }
    }

    public void makeRebuyVisibleIfBust(){
        if (newGame.showUserFunds() <= 0){
            rebuyBtn.setVisibility(View.VISIBLE);
            stickBtn.setVisibility(View.GONE);
            twistBtn.setVisibility(View.GONE);
            placeBetBtn.setVisibility(View.GONE);
        }
    }

    public void endOfRound(){

        placeBetBtn.setVisibility(View.VISIBLE);
        betBar.setVisibility(View.VISIBLE);
        selectedBetTv.setVisibility(View.VISIBLE);
        showWinnerTv.setVisibility(View.VISIBLE);

        stickBtn.setVisibility(View.INVISIBLE);
        twistBtn.setVisibility(View.INVISIBLE);


        showFundsTv.setText("£" + Integer.toString(newGame.showUserFunds()));
        betBar.setMax(newGame.showUserFunds());

        makeRebuyVisibleIfBust();

        String showAmountWon = Integer.toString(betPlaced);

        if (newGame.showUserFunds() > beforeWinAmount){
            Toast.makeText(getApplicationContext(), "You just won £" + showAmountWon + "!!", Toast.LENGTH_SHORT).show();
        }

        if (!player.getHand().checkBust()){
            Toast.makeText(getApplicationContext(), "Place your bet please!", Toast.LENGTH_LONG).show();
        }

    }

    //DISPLAYING CARDS

    public void displayUserCardOne(){
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    userCard1.setImageResource(android.R.color.white);
                    userCard1Suit2.setVisibility(View.VISIBLE);
                    userCard1Suit2.setImageResource(player.getHand().getCards().get(0).getSuitImage());
                    cardOneTv.setText(player.getHand().getCards().get(0).getRank().getName());
                }
            }.start();

    }

    public void displayUserCardTwo(){
            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }
                @Override
                public void onFinish() {
                    userCard2.setImageResource(android.R.color.white);
                    userCard2Suit2.setVisibility(View.VISIBLE);
                    userCard2Suit2.setImageResource(player.getHand().getCards().get(0).getSuitImage());

                    cardTwoTv.setText(player.getHand().getCards().get(1).getRank().getName());
                }
            }.start();

    }

    public void displayDealerCardOne(){

            new CountDownTimer(3000, 1000){
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    dealerCard1.setImageResource(android.R.color.white);
                    dealerCard1Suit2.setImageResource(dealer.getHand().getCards().get(0).getSuitImage());

                    dealerCard1Suit2.setVisibility(View.VISIBLE);
                    dealerCardOneTv.setText(dealer.getHand().getCards().get(0).getRank().getName());
                }
            }.start();
    }

    public void displayDealerCardTwo(){
        dealerCard2.setImageResource(android.R.color.white);
        dealerCardTwoTv.setVisibility(View.VISIBLE);
        dealerCardTwoTv.setText(dealer.getHand().getCards().get(1).getRank().getName());
        dealerCard2Suit2.setVisibility(View.VISIBLE);
        dealerCard2Suit2.setImageResource(dealer.getHand().getCards().get(1).getSuitImage());
    }

    public void hideDealerCardTwo() {
        dealerCard2.setImageResource(R.drawable.back);
        dealerCard2Suit2.setVisibility(View.INVISIBLE);
        dealerCardTwoTv.setVisibility(View.INVISIBLE);
    }

    public void revealTheDeal(){
        newGame.deal();
        displayUserCardOne();
        displayUserCardTwo();
        displayDealerCardOne();

        new CountDownTimer(4000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                userTotalTv.setVisibility(View.VISIBLE);
                stickBtn.setVisibility(View.VISIBLE);
                twistBtn.setVisibility(View.VISIBLE);
                handValueTv.setVisibility(View.VISIBLE);
                handValueTv.setText(newGame.showUserHandValue().toString());
            }

        }.start();
    }

    public void resetTable(){
        dealerHandValueTv.setText("");
        showWinnerTv.setText("");
        checkBustTv.setText("");
        userCard1.setImageResource(R.drawable.back);
        userCard2.setImageResource(R.drawable.back);
        dealerCard1.setImageResource(R.drawable.back);
        dealerCard2.setImageResource(R.drawable.back);
        cardOneTv.setText("");
        cardTwoTv.setText("");
        dealerCardOneTv.setText("");
        dealerCardTwoTv.setText("");
        userCard1Suit2.setVisibility(View.INVISIBLE);
        userCard2Suit2.setVisibility(View.INVISIBLE);
        dealerCard1Suit2.setVisibility(View.INVISIBLE);
        showWinnerTv.setVisibility(View.INVISIBLE);
        handValueTv.setVisibility(View.INVISIBLE);
        dealerHandValueTv.setVisibility(View.INVISIBLE);

        userTotalTv.setVisibility(View.INVISIBLE);
        dealerTotalTv.setVisibility(View.INVISIBLE);

        betBar.setVisibility(View.INVISIBLE);
        placeBetBtn.setVisibility(View.INVISIBLE);
        selectedBetTv.setVisibility(View.INVISIBLE);
        handValueTv.setText("");

        communalCard.setVisibility(View.INVISIBLE);
        communalCardSuit.setVisibility(View.INVISIBLE);
        communalDrawTextTv.setVisibility(View.INVISIBLE);
        communalCardTv.setVisibility(View.INVISIBLE);
    }

    //ReBuy Button
    public void reBuyPage(View view) {
        Intent i = new Intent(this, ReBuyActivity.class);
        startActivity(i);
    }

}
