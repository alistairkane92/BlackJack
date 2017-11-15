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
        player.returnCards();

        dealer.startDealing(this.players, this.deck);
    }


    public Integer showUserHandValue(){
        return player.getHandValue();
    }

    public int showUserFunds(){ return player.getFunds(); }

    public void setUserFunds(int newAmount){player.setFunds(newAmount);}

    public Integer showDealerHandValue(){
        return dealer.getHandValue();
    }

    public void twist(){
        //deals player another card and display new result
        dealer.dealCard(players.get(0), deck);
    }

    public String displayBust(){
        if (players.get(0).getHand().checkBust()){
            return "You're BUST!";
        } else {
            return " ";
        }
    }

//      Dealer logic

    public void dealerMove() {
        while (dealer.checkShouldTwist(dealer.getHandValue())) {
            dealer.dealCard(dealer, deck);
        }
    }

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

    public void payOut(Integer betPlaced, Participant player){
        if (checkWinner(players).equals(player.getName())){
            player.setFunds(player.getFunds() + betPlaced * 2);
        }
    }

    public void setUserName(String userName) {
        this.player.setName(userName);
    }

    public boolean checkIfBlackJack() {
        if (player.getHandValue() == 21){
            return true;
        } else return false;
    }
}

