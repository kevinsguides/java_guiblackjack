package com.kevinsguides;

import javax.swing.JFrame;

/**
 * The Main Class
 * BlackJack with GUI
 * Used to start Games of Blackjack
 * @author Kevin Olson - KevinsGuides.com
 * @License MIT
 * @Copyright Copyright 2023 Kevin Olson
 * @Version 1.0 
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
public class Main {

    public static void main(String[] args) {


        //Create game window
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set its size
        frame.setSize(800, 600);

        //Make a new blackjack Game object/JPanel
        Game blackjack = new Game();

        //Add it to the frame and make it visible
        frame.add(blackjack);
        frame.setVisible(true);
        



    }
}
