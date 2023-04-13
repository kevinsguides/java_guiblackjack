package com.kevinsguides;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Contains all Game logic and GUI stuff
 * This is where most of the changes over the console project are located
 */
public class Game extends JPanel {

    //Declare variables needed for Game class
    private Deck deck, discarded;

    private Dealer dealer;
    private Player player;
    private int wins, losses, pushes;

    //These constants define the width and height of the card images on the screen
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = 145;

    //These variables reference GUI elements
    JButton btnHit, btnStand, btnNext;
    JLabel lblDealerCards[], lblPlayerCards[], lblScore, lblPlayerHandVal, lblDealerHandVal, lblGameMessage;

    /**
     * Constructor for Game, creates our variables and starts the Game
     */
    public Game() {

        //Create a new deck with 52 cards
        deck = new Deck(true);
        //Create a new empty deck
        discarded = new Deck();

        //Create the People
        dealer = new Dealer();
        player = new Player();

        //Shuffle the deck and start the first round
        deck.shuffle();

        //Setup the GUI
        setupGUI();

        //Start first round
        startRound();


    }

    /**
     * Sets up the game window with GUI elements
     * (buttons, labels, places for the cards to show up, etc.)
     */
    private void setupGUI(){

        //Size of JPanel
        this.setSize(800, 500);

        //Make Buttons for "Hit" "Stand" and "Next Round" actions.
        //setBounds is used to define their locations and sizes
        btnHit = new JButton("Hit");
        btnHit.setBounds(10, 10, 50, 20);
        btnStand = new JButton("Stand");
        btnStand.setBounds(70, 10, 100, 20);
        btnNext = new JButton("Next Round");
        btnNext.setBounds(180, 10, 140, 20);

        //Listen for button clicks and do stuff with them

        //When someone clicks the Hit button
        btnHit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //make the player hit the deck
                player.hit(deck, discarded);
                //update screen with their new card, and their score
                updateScreen();
                //check if they busted
                checkBusts();

            }
        });

        //when someone clicks the "Stand" button
        btnStand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //that means it's the dealers turn now
                dealersTurn();
                //see who won after dealer drew card
                checkWins();
                //update screen with player's cards
                updateScreen();
                //also reveal all the dealer's cards, so we can see what they drew
                dealer.printHand(lblDealerCards);

                //make only the next round button visible, they cannot hit/stand at this point
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            }
        });

        //someone hits the next round button
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset buttons and start next round
                btnNext.setVisible(false);
                btnHit.setVisible(true);
                btnStand.setVisible(true);
                startRound();
            }
        });



        //The maximum amount of cards to get to 21, with all the lowest cards, is 11 cards
        //That's ace1 + ace1 + ace1 + ace1 + 2 + 2 + 2 + 2 + 3 + 3 + 3 = 21
        //So we need room to show up to 11 cards, though it's highly unlikely we'll get to this point
        //I'm most concerned with how the first 5 cards look. After that, it may look kind of odd...

        lblDealerCards = new JLabel[11];
        lblPlayerCards = new JLabel[11];

        //set the initial location of the card on the screen
        int initialCardX = 10, initialCardY = 150;

        //for each card that can possibly be displayed in the array
        for (int i = 0; i < lblDealerCards.length; i++) {

            //set them to new cards face down
            //done with JLabels and ImageIcons
            lblDealerCards[i] = new JLabel(new ImageIcon(new ImageIcon(Main.IMAGE_DIR+"CardDown.png").getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT)));
            lblPlayerCards[i] = new JLabel(new ImageIcon(new ImageIcon(Main.IMAGE_DIR+"CardDown.png").getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT)));

            //Use setBounds to set the width/height of each card, and their positions
            lblDealerCards[i].setBounds(initialCardX, initialCardY, CARD_WIDTH, CARD_HEIGHT);
            lblPlayerCards[i].setBounds(initialCardX, initialCardY+250, CARD_WIDTH, CARD_HEIGHT);

            //add the JLabel to the JPanel so we can see it later
            this.add(lblDealerCards[i]);
            this.add(lblPlayerCards[i]);

            //increment the x/y values of each card by some amount, this will make them appear "stacked" so users can see each one
            initialCardX +=  50;
            initialCardY -= 18;

        }

        //make scoreboard
        lblScore = new JLabel("[Wins: 0]   [Losses: 0]   [Pushes: 0]");
        lblScore.setBounds(450,10, 300, 50);
        this.add(lblScore);

        //message board
        lblGameMessage = new JLabel("Starting round! Hit or Stand?");
        lblGameMessage.setBounds(450, 200, 300, 40);
        lblGameMessage.setFont(new Font("Arial", 1, 20));
        this.add(lblGameMessage);

        //hand values on display
        lblDealerHandVal = new JLabel("Dealer's Hand Value:");
        lblPlayerHandVal = new JLabel("Player's Hand Value:");
        lblDealerHandVal.setBounds(20, 280, 300, 50);
        lblPlayerHandVal.setBounds(20, 530, 300, 50);
        this.add(lblDealerHandVal);
        this.add(lblPlayerHandVal);

        //show/add buttons
        this.add(btnHit);
        this.add(btnStand);
        this.add(btnNext);
        this.setLayout(null);

        //make all labels white
        lblGameMessage.setForeground(Color.WHITE);
        lblDealerHandVal.setForeground(Color.WHITE);
        lblPlayerHandVal.setForeground(Color.WHITE);
        lblScore.setForeground(Color.WHITE);

        //make next round button hidden to start
        btnNext.setVisible(false);

    }

    /**
     * Updates everything on the screen. Cards, Values, Scores, etc.
     * Updates everything EXCEPT the dealer's cards. The dealers cards are shown manually from elsewhere.
     */
    private void updateScreen(){

        lblPlayerHandVal.setText("Player's Hand Value: " + player.getHand().calculatedValue());
        lblDealerHandVal.setText("Dealer's Hand Value: " + dealer.getHand().calculatedValue());

        player.printHand(lblPlayerCards);
        //score
        lblScore.setText("[Wins: " + wins + "]   [Losses: " + losses + "]   [Pushes: "+pushes+"]");

    }

    /**
     * Start a new round, display score, give out cards, check for BlackJack
     */
    private void startRound() {
        //Update game message, tell user what to do
        lblGameMessage.setText("Your turn! Hit or Stand?");

        //If this isn't the first round, display the users score and put their cards back in the deck
        if (wins > 0 || losses > 0 || pushes > 0) {
            System.out.println();
            System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
            dealer.getHand().discardHandToDeck(discarded);
            player.getHand().discardHandToDeck(discarded);
        }

        //Check to make sure the deck has at least 4 cards left to start
        //Technically this could mean there's 1-3 cards left in the deck that are usable, but we'll just reshuffle them and not worry about it
        if (deck.cardsLeft() < 4) {
            //reload the deck from discard pile if we're out of cards
            deck.reloadDeckFromDiscard(discarded);
        }

        //Give the dealer two cards
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        //Give the player two cards
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //update the screen
        updateScreen();

        //print the dealer's hand
        dealer.printHand(lblDealerCards);
        //hide dealer's second card and card values, player should only see first card to start
        lblDealerCards[1].setIcon(new ImageIcon(new ImageIcon("src/com/kevinsguides/img/cards/CardDown.png").getImage()
                .getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT)));
        lblDealerHandVal.setText("Dealer's hand value: " + dealer.getHand().getCard(0).getValue() + " + ?");

        //Check if dealer has BlackJack to start
        if (dealer.hasBlackjack()) {
            //Show the dealer has BlackJack
            dealer.printHand(lblDealerCards);
            //Check if the player also has BlackJack
            if (player.hasBlackjack()) {
                //End the round with a push
                lblGameMessage.setText("Both 21 - Push");
                pushes++;
                //start a new round
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            } else {
                lblGameMessage.setText("Dealer has Blackjack!");
                dealer.printHand(lblDealerCards);
                losses++;
                //player lost, start a new round
                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnNext.setVisible(true);
            }
        }

        //Check if player has blackjack to start
        //If we got to this point, we already know the dealer didn't have blackjack
        if (player.hasBlackjack()) {
            //say player has blackjack
            lblGameMessage.setText("You have Blackjack!");
            //update score
            wins++;
            //make next round button only visible button
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
        }

    }

    private void checkBusts(){
        //Check if they busted
        if (player.getHand().calculatedValue() > 21) {
            //show message
            lblGameMessage.setText("You BUST - Over 21");
            //update score
            losses++;
            //make next round button only visible button
            btnHit.setVisible(false);
            btnStand.setVisible(false);
            btnNext.setVisible(true);
        }
    }

    private void checkWins(){

        //update the score/cards on display
        updateScreen();

        //Check who wins and count wins or losses
        if (dealer.getHand().calculatedValue() > 21) {
            lblGameMessage.setText("Dealer Busts! You win!");
            wins++;
        } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
            lblGameMessage.setText("Dealer wins - Higher hand");
            losses++;
        } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
            lblGameMessage.setText("You win - Higher hand");
            wins++;
        } else {
            lblGameMessage.setText("Equal Value Hands - Push");
            pushes++;
        }
    }

    private void dealersTurn(){

        //Now it's the dealer's turn
        //Dealer will continue drawing until hand is valued at 17 or higher
        while (dealer.getHand().calculatedValue() < 17) {
            //dealer hits deck
            dealer.hit(deck, discarded);
            updateScreen();
        }
    }


    //This just makes the background of the "table" dark green
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.decode("#18320e"));
        g.fillRect(0,0,1000,1000);
    }


}
