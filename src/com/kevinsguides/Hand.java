package com.kevinsguides;


import java.util.ArrayList;


/**
 * A hand of cards to play with
 */
public class Hand {

    private ArrayList<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }

    /**
     * Take a single card from the top of this deck and add it to the hand, removing it from the previous deck
     * @param deck The deck of cards we're taking from
     */
    public void takeCardFromDeck(Deck deck){
        hand.add(deck.takeCard());
    }

    /**
     * Add a single card to this hand
     * @param c The card being added
     */
    /**
     *
     * @param discardDeck The deck we're discarding this hand to
     */
    public void discardHandToDeck(Deck discardDeck){

        //copy cards from hand to discardDeck
        discardDeck.addCards(hand);

        //clear the hand
        hand.clear();

    }

    /**
     *
     * @return The hand with all its cards in a single line String
     */
    public String toString(){
        //the String we're formatting for output
        String output = "";
        //for each card in the hand
        for(Card card: hand){
            //add the String version of the card to the output string
            output += card + " - ";
        }
        //return the formatted string
        return output;
    }


    /**
     *
     * @return The calculated numerical value of the hand as an integer
     */
    public int calculatedValue(){

        //variable to count number of aces, and current total value
        int value = 0;
        int aceCount = 0;

        //For each card in this hand
        for(Card card: hand){
            //Add the card value to the hand
            value += card.getValue();
            //Count how many aces have been added
            if (card.getValue() == 11){
                aceCount ++;
            }
        }
        //if we have a scenario where we have multiple aces, as may be the case of drawing 10, followed by two or more aces, (10+11+1 > 21)
        //go back and set each ace to 1 until get back under 21, if possible
        if (value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                aceCount --;
                value -= 10;
            }
        }
        return value;

    }


    /**
     *
     * @param idx the index of the card we're getting
     * @return the card we got
     */
    public Card getCard(int idx){
        return hand.get(idx);
    }

    public int getHandSize(){
        return hand.size();
    }



}
