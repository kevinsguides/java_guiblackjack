package com.kevinsguides;

import javax.swing.*;
import java.awt.*;

/**
 * Used for shared logic between the dealer and player
 */
public abstract class Person {

    private Hand hand;
    private String name;

    /**
     * Create a new Person
     */
    public Person(){
        this.hand = new Hand();
        this.name = "";
    }


    //Setters and Getters
    public Hand getHand(){
        return this.hand;
    }
    public void setHand(Hand hand){
        this.hand = hand;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    /**
     * Update the image icons for the player's hand
     */
    public void printHand(JLabel[] cardPics){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());


        //iterate through each card, update pic, hide remaining
        for(int i = 0; i < 11; i++){
            cardPics[i].setVisible(false);
        }
        for(int i = 0; i < this.hand.getHandSize(); i++){
            String rank = this.hand.getCard(i).getRank().toString();
            String suit = this.hand.getCard(i).getSuit().toString();
            String filename = rank + suit + ".png";
            cardPics[i].setIcon(new ImageIcon(new ImageIcon(Game.IMAGE_DIR+filename).getImage().getScaledInstance(Game.CARD_WIDTH, Game.CARD_HEIGHT, Image.SCALE_SMOOTH)));
            cardPics[i].setVisible(true);
        }

    }

    /**
     * Player takes a card from the deck
     * @param deck
     */
    public void hit(Deck deck, Deck discard){

        //If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
    
    }


    /**
     * Check if the player has blackjack
     * @return boolean true if blackjack, false if not
     */
    public boolean hasBlackjack(){
        if(this.getHand().calculatedValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }



}
