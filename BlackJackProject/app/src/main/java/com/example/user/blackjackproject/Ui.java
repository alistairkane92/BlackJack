package com.example.user.blackjackproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by user on 10/11/2017.
 */
public class Ui {

    Scanner sc;
    private String answer;
    BufferedReader br;


    public Ui() {
        sc = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
    }


    public String askTwistOrStick(Participant participant){
        System.out.println(participant.getName() + " would you like to twist or stick?");
        try {
            answer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;



    }

    public void showDealtCards(Participant player) {
        System.out.println("\nYour hand is ");
        pause();
        System.out.println(player.describeHand());
        pause();
        System.out.println(player.getName() + " has " + player.getHandValue());
    }

    public void showWinner(Participant winner){
        System.out.println(winner.getName() + " wins all the moolah!");
        goodbye();
    }

    public void goodbye() {
        System.out.println("\nSee ya later alligator!");
        System.exit(1);

    }

    public void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
