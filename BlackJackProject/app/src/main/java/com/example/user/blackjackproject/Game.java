package com.example.user.blackjackproject;

/**
 * Created by user on 10/11/2017.
 */

import java.util.ArrayList;


public class Game {

    private Deck deck;
    private Dealer dealer;
    private ArrayList<Participant> players;
    private Player player;

    public Game(Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer("DEALER", 0);
        this.players = new ArrayList<>();
        this.player = new Player("Player 1", 0);

        players.add(player);
        players.add(dealer);
    }

    public ArrayList<Participant> getPlayers() {
        return players;
    }

    public void deal(){
        dealer.setMaxDealerFunds(player.getFunds());
        dealer.returnCards();
        dealer.resetHandValue();
        player.resetHandValue();
        player.returnCards();

        dealer.startDealing(this.players, this.deck);
    }

    public String showUserHand(){
        return player.describeHand();
    }

    public Integer showUserHandValue(){
        return player.getHandValue();
    }

    public int showUserFunds(){ return player.getFunds(); }

    public void setUserFunds(int newAmount){player.setFunds(newAmount);}

    public String showDealerHand(){
        return dealer.describeHand();
    }

    public Integer showDealerHandValue(){
        return dealer.getHandValue();
    }

    public void makeAcesLow(Participant player){
        if (player.getHand().checkIfAce()){
            player.getHand().makeAcesLowIfBust();
        }
    }

    public void twist(){

        //deals player another card and display new result
        dealer.dealCard(players.get(0), deck);
        makeAcesLow(player);
    }

    public String displayBust(){
        if (players.get(0).getHand().checkBust()){
            return "You're BUST!";
        } else {
            return " ";
        }
    }

    public void stickOrTwistAction() {
        for (int i = 0; i < players.size() - 1; i++) {
            Participant player = players.get(i);
            if (player.getHand().checkIfAce()) {
                player.getHand().makeAcesLowIfBust();
            }
            while (!player.getHand().checkBust() && player.getStickOrTwist().equals("t")) {
                player.setStickOrTwist("t");

                if (player.getStickOrTwist().equals("t")) {
                    dealer.dealCard(player, deck);
                }
            }
        }
    }

//      Dealer logic

    public void dealerMove() {

        while (dealer.checkShouldTwist(dealer.getHandValue())) {
            dealer.dealCard(dealer, deck);
        }
        makeAcesLow(dealer);

    }

    public void dealerTakesCard(){
        if (dealer.checkShouldTwist(dealer.getHandValue())){
            dealer.dealCard(dealer, deck);
        } else dealer.setStickOrTwist("f");
    }

//     && dealer.getHandValue() <= 21

    public boolean checkIfDrawDealerWins(ArrayList<Participant> players){
        if (players.get(0).getHandValue() == players.get(1).getHandValue() && players.get(1).getHandValue() >= 16 && players.get(1).getHandValue() <= 21){
            return true;
        } else return false;
    }

//  Winner

    public String checkWinner(ArrayList<Participant> players) {
        Participant player1 = players.get(0);
        Participant player2 = players.get(1);

        if (checkIfDrawDealerWins(players)){
            return player2.getName();
        }

        if (!player1.getHand().checkBust() && !player2.getHand().checkBust()) {
            if (player1.getHandValue() == player2.getHandValue()) {
                return player1.getName();
            } else {
                return player1.getHandValue() > player2.getHandValue() ? player1.getName() : player2.getName();
            }

        } else {

            return player1.getHand().checkBust() ? player2.getName() : player1.getName();

        }
    }

    public String displayWinner(){
        return checkWinner(players);
    }


    // BET LOGIC

    public void placeBet(Integer betAmount){
        Integer newFunds = player.getFunds() - betAmount;
        player.setFunds(newFunds);
    }

    public void payOut(Integer betPlaced){
        Participant player1 = players.get(0);

        if (checkWinner(players).equals(player1.getName())){
            player1.setFunds(player1.getFunds() + betPlaced * 2);
        }
    }



    public void setUserName(String userName) {
        this.player.setName(userName);
    }
}

