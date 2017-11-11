package com.example.user.blackjackproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 10/11/2017.
 */

public class TestDealer {

    Player player;
    Dealer spyDealer;
    Deck deck;
    Deck spyDeck;
    Card card1;
    Card card2;
    ArrayList<Participant> players;

    @Before
    public void setup(){
        deck = new Deck();
        player = new Player("Player1", 1000);
        spyDeck = Mockito.spy(deck);
        spyDealer = new Dealer("Dealer", 1000);
        card1 = new Card(Suit.HEARTS, Rank.ACE);
        card2 = new Card(Suit.CLUBS, Rank.QUEEN);
        players = new ArrayList<Participant>();
        players.add(player);
        players.add(spyDealer);

    }

    @Test
    public void dealerCanDeal(){
        Mockito.when(spyDeck.getCard()).thenReturn(new Card(Suit.CLUBS, Rank.ACE));
        spyDealer.dealCard(player, spyDeck);
        assertEquals(1, player.getHand().getNumberOfCards());
        assertEquals(Suit.CLUBS, player.getHand().getCards().get(0).getSuit());
        assertEquals(Rank.ACE, player.getHand().getCards().get(0).getRank());

    }

    @Test
    public void dealerDealsTwoCards() throws Exception {
        spyDealer.startDealing(players, deck);
        assertEquals(2, players.get(0).getHand().getNumberOfCards());
        assertEquals(2, players.get(1).getHand().getNumberOfCards());


    }

    @Test
    public void dealerDoesntTwistsWhenHandValueOver16() throws Exception {
        spyDealer.addCardToHand(card1);
        spyDealer.addCardToHand(card2);
        assertEquals(false, spyDealer.checkShouldTwist(spyDealer.getHandValue()));
    }
}

