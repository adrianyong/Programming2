/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author cyu15mtu
 */
public class BasicPlayer implements Player, Serializable {

    static final long serialVersionID = 132;
    /* global variables */
    
    Hand playerHand = new Hand();
    int balance = 200;
    protected int bet = 0; 
    
    
    @Override
    public Hand newHand() {
       Hand oldHand = playerHand; //stores the previous hand played in oldHand
       playerHand = new Hand();//makes new hand
       bet = 0; //resets the bet
       return oldHand;//returns oldHand
    }

    @Override
    public int makeBet() {
        bet = 10;
        return bet;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean hit() {
        
        boolean hit = false;    //hit set to false
        //int handValues [] = playerHand.handTotalValue();  //have the array of the total value
        int result = playerHand.optimalValue();   //result is the optimal value for array
        if(result <= 17)        //since basic, only limits at 17 so if it is less than 17
        {
            hit = true; //hit set to true
        }
        System.out.println("Will hit: " + hit);
        return hit;
        
    }

    @Override
    public void takeCard(Card c) 
    {
        //System.out.println("Hit: " + hit());
        if(hit())
        {
            playerHand.addSingleCard(c);  //add card to the hand
            playerHand.handTotalValue();  //updates the total value
            playerHand.optimalValue();    //calls for the optimal value
            //System.out.println("Card Value: " + optimal);
        }
    }

    @Override
    public boolean settleBet(int p) 
    {
       boolean settle = false;
       if (p < 0)
       {
           balance = balance - getBet();
       }
       else if (p > 0)
       {
           balance = balance + getBet();
          
           if (blackjack())
           {
               balance = balance + 2*getBet();
           }
       }
       
       if(balance < 0)
       {
           settle = true;
       }
       return settle;
    }

    @Override
    public int getHandTotal() {
        int[] handValueList = playerHand.handTotalValue(); //uses handTotalValue method to return hand total.
        int handTotal = handValueList[0]; //returns latest index in list
        
        return handTotal; //returns hand total value
    }

    @Override
    public boolean blackjack() {
        return Card.isBlackjack(playerHand.hand.get(0),playerHand.hand.get(1)); //returns the isBlackjack method from the card
    }

    @Override
    public boolean isBust() {
        boolean bust = false;   
        int handTotal = getHandTotal();
        if(handTotal > 21)  //if the hand total is greater than 21
        {
            bust = true;    //bust is set to true
        }
        return bust;
    }

    @Override
    public Hand getHand() {
        return playerHand;
        
    }
    
    public boolean isBroke()
    {
        boolean isBroke = false;
        if(balance <= 0)
            isBroke = true;
        return isBroke;
    }
    
    @Override
    public void viewDealerCard(Card c) {
        System.out.println("Dealer's card: " + c.toString()); //prints out the dealers card
    }

    @Override
    public void viewCards(ArrayList<Card> cards) {
    }

    @Override
    public void newDeck() {
    }
    
//    public static void main(String[] args)
//    {
//        Deck deck = new Deck();
//        BasicPlayer p1 = new BasicPlayer();
//        deck.shuffle();
//        
//        for(int i=0; i < 1; i++)
//        {
//        Card c1 = deck.deal();
//        System.out.println("Card drawn: " + c1);
//        p1.takeCard(c1);
//        
//        c1 = deck.deal();
//        System.out.println("Card drawn: " + c1);
//        p1.takeCard(c1);
//        
//        c1 = deck.deal();
//        System.out.println("Card drawn: " + c1);
//        p1.takeCard(c1);
//        
//        
//        System.out.println(p1.viewCards());
//      }
    
    
}
