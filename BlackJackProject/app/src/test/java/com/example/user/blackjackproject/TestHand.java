package com.example.user.blackjackproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 10/11/2017.
 */

public class TestHand {

    Hand hand;
    Card card;
    Card card2;
    Card card3;


    @Before
    public void setup(){
        hand = new Hand();
        card = new Card(Suit.CLUBS, Rank.ACE, 3);
        card2 = new Card(Suit.DIAMONDS, Rank.ACE, 2);
        card3 = new Card(Suit.DIAMONDS, Rank.KING, 2);
    }

    @Test
    public void valueIs0Initially(){
        assertEquals(0, hand.getHandValue());
    }

    @Test
    public void cardIsAdded(){
        hand.addCard(card);
        hand.addCard(card2);
        assertEquals(2, hand.getCards().size());
    }

    @Test
    public void handHasValue(){
        hand.addCard(card);
        assertEquals(11, hand.getHandValue());
    }

    @Test
    public void checkLowAces() throws Exception {
        hand.addCard(card);
        hand.addCard(card2);
        hand.addCard(card2);
        assertEquals(13, hand.getHandValue());
    }

    @Test
    public void numberOfAcesInHand() throws Exception {
        hand.addCard(card);
        hand.addCard(card);
        hand.addCard(card);
        assertEquals(3, hand.numberOfAcesInHand());
    }

    @Test
    public void stillBustIfAllAcesInHand() throws Exception {
        hand.addCard(card);
        hand.addCard(card);
        hand.addCard(card);
        hand.addCard(card);
        hand.addCard(card3);
        hand.addCard(card3);
        assertEquals(24, hand.getHandValue());
    }

    @Test
    public void testAcesOnlyMadeLowOnce() throws Exception {
        hand.addCard(card);
        hand.addCard(card3);
        hand.addCard(card3);
        assertEquals(21, hand.getHandValue());
        hand.addCard(card3);
        assertEquals(31, hand.getHandValue());
    }
}
