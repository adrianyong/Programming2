/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public class IntermediatePlayer extends BasicPlayer implements Player {
    
    BlackjackDealer dealer = new BlackjackDealer();         //gets the dealer
    
    @Override
    public boolean hit() {
        
        boolean hit = false;    //hit set to false
        int result = playerHand.optimalValue();   //result is the optimal value for array
        int firstCard = dealer.getHandTotal();   //result is the optimal value for array
        if(firstCard >= 7 && result < 17)        //if the dealer's card is 7 or greater
        {
            hit = true; //hit set to true
        }
        else if(firstCard <= 6 && result < 12)        //if the dealer's card is 6 or less
        {
            hit = true; //hit set to true
        }
        System.out.println("Will hit: " + hit);
        return hit;
        
    }
    
    
//    public static void main(String[] args)
//    {
//        
//        IntermediatePlayer p1 = new IntermediatePlayer();
//        BlackjackDealer dealer = new BlackjackDealer();
//        
//        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
//        players.add(p1);    
//        dealer.assignPlayers(players); 
//        
//        
//        dealer.dealFirstCards();
//        
//       
//        System.out.println("Dealer Card: " + dealer.dealerHand.toString()); //print out dealer hand
//        
//        System.out.println("----Player's hand----");
//        System.out.println(p1.viewCards());  //print out player hand
//        
//        System.out.println("Checking hit: " + p1.hit());        //checking the hit algorithm
//        while(p1.hit() && !p1.isBust())          //while hit is true
//        {
//            dealer.play(p1);                    //deals player 1 a card
//            System.out.println(p1.viewCards()); //prints out the cards
//            dealer.deckCheck();
//            System.out.println("Is player bust: " + p1.isBust());
//        }       
//
//        
        

    
//    }
}
