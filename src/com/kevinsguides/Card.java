package com.kevinsguides;

/**
 * The Card class allows us to create cards with a Suit and a Rank
 * It contains the methods needed to get the value of the card,
 * and it contains a method to print the card to the console
 */
public class Card implements Comparable<Card>{

     private Suit suit;
     private Rank rank;

    /**
     *
     * @param suit  The Suit of the card to be created
     * @param rank  The Rank of the card to be created
     */
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Copy constructor
     * @param card the card being copied
     */
    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    /**
     *
     * @return  The numerical value of the Card
     */
    public int getValue(){
        return rank.rankValue;
    }

    /**
     *
     * @return The suit of the Card.
     */
    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    /**
     *
     * @return The Card as a readable string
     */
    public String toString(){
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");

    }

    /**
     * Compare a card to another card, returning 1 if this is higher, -1 if this is lower, in case we want to sort cards
     * I didn't talk about this in the tutorial, but left it here, so you can see how it works
     * @param c the card being compared
     * @return 1 if greater, -1 if less, 0 if equal
     */
    @Override
    public int compareTo(Card c) {
        //if this card is greater than the other card
        if(this.getValue() > c.getValue()){
            return 1;
        }
        else if(this.getValue() < c.getValue()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
