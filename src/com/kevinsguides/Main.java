package com.kevinsguides;

import javax.swing.*;

/**
 * GUI BLACKJACK - A GUI version of my earlier Blackjack project
 * The Main Class
 * Used to start Games of Blackjack
 * @author Kevin Olson - KevinsGuides.com
 * @License MIT
 * @Copyright Copyright 2022 Kevin Olson
 * @Version 1.0 - Seems stable but not extensively tested - updated with more docs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

/**
 * NOTICE: If you're coming from the CONSOLE version of my Blackjack game, this code is based off of it.
 * Here are the key differences:
 * The Main class opens a JFrame containing a JPanel, where we draw the cards, scores, and gui elements
 * The logic behind the game is largely unchanged
 * The Game class contains additional methods for creating the GUI
 * The makeDecision method was REMOVED. Now players make decisions (hit stand) by clicking buttons
 * Various areas of the Game class were modified to support buttons instead of calling the makeDecisions method
 * The printHand method in the Person class no longer prints a string of cards. Rather, it displays the cards in the player's hand on the screen in JLabels
 */
public class Main {

    /*
    IMAGE_DIR is the relative directory from the project root (user.dir) to the cards folder.
    It may be different depending on your IDE/settings.
    IF CARD IMAGES DON'T LOAD, YOU PROBABLY NEED TO CHANGE THIS
     */
    



    public static void main(String[] args) {

        //use this to find the user.dir for your project if images aren't loading
        System.out.println("Project directory to start finding images from is: " + System.getProperty("user.dir"));

        //Create the Window - give it title, set size, set close operation (exit on close)
        JFrame frame = new JFrame("BlackJack");
        frame.setSize(750,650);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //start a new  game of Blackjack
        Game blackjack = new Game();

        //Add game to window gui
        frame.add(blackjack);

        //make the game visible
        frame.setVisible(true);

    }
}
