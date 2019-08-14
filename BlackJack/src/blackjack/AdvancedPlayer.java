/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class AdvancedPlayer extends BasicPlayer implements Player {
    
    BlackjackTable table = new BlackjackTable();
    BlackjackDealer dealer = new BlackjackDealer();         //gets the dealer
    int count;
    ArrayList<Card> cardsPlayed = new ArrayList();   
    
    @Override
    public int makeBet() {
        
       
       System.out.println("----Cards Played----"); 
       System.out.println(cardsPlayed);
       for (Card c: cardsPlayed)
        {
            
            if (c.getValue() <= 6)
                count+=1;
            else if (c.getValue() >=10)
                count-=1;
            
        }
        System.out.println("Count: " + count);
        
        if (count > 0)
        {
            bet = 10 * count; //multiplay by the count
        }
        else{bet=10;}
        System.out.println("Bet: " + bet);
        
        return bet;
    }
    
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
    
    @Override
    public void viewCards(ArrayList<Card> cards) {
        cardsPlayed.addAll(cards);
        System.out.println("Cards Played: " + cardsPlayed);
    }
    
    
    @Override
    public void newDeck() {
            cardsPlayed.clear();
    }
    
    
//    public static void main(String[] args)
//    {
//        
//        
//        BasicPlayer p1 = new BasicPlayer();
//        IntermediatePlayer p2 = new IntermediatePlayer();
//        AdvancedPlayer p3 = new AdvancedPlayer();
//        BlackjackDealer dealer = new BlackjackDealer();
//        
//        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
//        players.add(p1);  
//        players.add(p2);
//        players.add(p3);
//        dealer.assignPlayers(players); 
//        
//        
//        dealer.dealFirstCards();
//        
//       
//        System.out.println("Dealer Card: " + dealer.dealerHand.toString()); //print out dealer hand
//        
//            for(int i=0; i < 4; i++)
//            {
//                    System.out.println("**********Round number " + (i+1)+"**********");
//                    dealer.takeBets();                                   //take the bets of the players
//                                                              //runs the check deck method
//                    //System.out.println("New deck in play: " + dealer.deckCheck());
//                    System.out.println("Dealing Cards.");
//                    dealer.dealFirstCards();  //deals the first cards
//                    int j = 0;                //used to count number of players
//                    for (Player p: players)
//                    {
//                        System.out.println("----Player's " + (j+1) +" hand----");
//                        System.out.println(p.getHand().toString());        
//                        System.out.println("Player " + (j+1) +" balance: " + p.getBalance());
//                        System.out.println("Player " + (j+1) +" bet: " + p.getBet());
//                        
//                        j++;
//
//                    /*player 1 hit or stick */
//                    while(p.hit() && !p.isBust())          //while hit is true
//                    {
//                        dealer.play(p);                    //deals player 1 a card
//                        System.out.println(dealer.dealerHand.toString()); //prints out the cards
//                        dealer.deckCheck();
//                        System.out.println("Is player bust: " + p.isBust());
//                    }
//
//                    }
//
//                    System.out.println("----Dealer's hand----");
//                    dealer.playDealer();                                 //dealer plays
//                    System.out.println(dealer.dealerHand.toString());    //outputs dealer hand
//                    
//                    
//                    dealer.settleBets();                                 //settles hand
//                    int k = 0;
//                    for (Player p: players)
//                    {
//                        System.out.println("Player " + (k+1) +" balance: "  + p.getBalance());  //outputs balance
//                        k++;
//                    }
//                    
//                    players.removeIf(player -> player.isBroke());
//                    if (players.isEmpty()){break;}
//                    
//            }
//        
//        //System.out.println("Checking hit: " + p1.hit());        //checking the hit algorithm
//
//        
//
//    
//    }
    
    
    
}
