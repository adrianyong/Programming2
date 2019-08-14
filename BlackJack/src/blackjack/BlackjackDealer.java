/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyu15mtu
 */
public class BlackjackDealer implements Dealer, Serializable {

    static final long serialVersionID = 142;
    
    Hand dealerHand = new Hand();
    private Deck deck = new Deck();
    List<Player> listOfPlayers;
        
    
       
    @Override
    public void assignPlayers(List<Player> p) 
    {
        deck.shuffle();
        listOfPlayers = p;              //assigns the list inputted above to list of players defined in this class
        /*debug for showing deck*/
//        System.out.println("----Deck----");
//        System.out.println(deck);
//        System.out.println("----End of Deck----");
    }

    @Override
    public void takeBets() {
        for(Player p: listOfPlayers)     //for every player in the list of players
        {
            p.makeBet();                //player makes the bet
            p.getBet();                 //gets the player's bet
        }
    }

    public boolean deckCheck()
    {
        boolean deckCheck = false;      //sets the deck check to false
        System.out.println("Size of current deck: " + deck.deckSize());
        
        if (deck.deckSize() < 20)       //if the deck size is less than 20
        {
            deckCheck = true;           //set the deck check to true
            deck.newDeck();             //makes a new deck
            deck.shuffle();             //shuffle the deck
            for(Player p: listOfPlayers)
            {
                p.newDeck();
            }
        }
       System.out.println("New deck in play: " + deckCheck); 
       return deckCheck;                //returns the value of deck check
    }
    
    public Hand newHand() {
       Hand oldHand = dealerHand; //stores the previous hand played in oldHand
       
       dealerHand = new Hand();//makes new hand
       return oldHand;//returns oldHand
    }
    
    @Override
    public void dealFirstCards()
    {     
       newHand();
       Card dealerFirst =  deck.deal();
       dealerHand.addSingleCard(dealerFirst);   //deals the dealer 1 card
       
       for(Player p: listOfPlayers)     //for every player in the list of players
       {
           Card c1 =  deck.deal();
           p.takeCard(c1);         //deals 2 cards from the top of the deck
           
           
           Card c2 =  deck.deal();
           p.takeCard(c2);
           
           
       }
       
    }

    @Override
    public int play(Player p) 
    {
        
       p.hit();                             //calls the hit method in player
       Card nextCard = deck.deal();
       p.takeCard(nextCard); //calls the player to take the top card
      
       
       int handTotal = p.getHandTotal();    //gets the hand total
       return handTotal;                    //returns the total
    }

    
    public int getHandTotal() {
        int[] handValueList = dealerHand.handTotalValue(); //uses handTotalValue method to return hand total.
        int handTotal = handValueList[0]; //returns latest index in list
        return handTotal; //returns hand total value
    }
    
    
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
    public int playDealer() {
        
        int result = dealerHand.optimalValue();           //result is the optimal value for array
        while(result < 17 && !isBust())                                   //since dealer, only limits at 17 so if it is less than 17
        {
          deckCheck();                        
          dealerHand.addSingleCard(deck.deal());        //adds card c to the dealer hand
          dealerHand.handTotalValue();                    //updates the total value
          result = dealerHand.optimalValue();             //updates the optimal value
        }
        
        return result;                                    //returns the result
    }

    @Override
    public int scoreHand(Hand h) {
        int handScore = h.optimalValue(); //sets the return value to the value from the method optimalValue
        return handScore;                 //returns optimal Value
    }

    @Override
    public boolean blackjack() {
        return Card.isBlackjack(dealerHand.hand.get(0),dealerHand.hand.get(1)); //returns the isBlackjack method from the card
    }
    
    
    
    @Override
    public void settleBets() {
        ArrayList<Card> cardsPlayed = new ArrayList();  
        int playerScore = 0;
        int dealerScore = scoreHand(dealerHand);
        
        for(Player p: listOfPlayers)
        {
            playerScore = p.getHandTotal();
            if (p.isBust())
            {
                System.out.println("Player is bust");
                p.settleBet(-1);
            }
            else if(dealerHand.isOver(dealerHand)){System.out.println("Dealer is bust"); p.settleBet(1);} //if dealer is bust then player wins
            else if(playerScore > dealerScore){System.out.println("Player wins"); p.settleBet(1);} //if player is higher than dealer, player wins
            else if(playerScore < dealerScore){System.out.println("Dealer wins"); p.settleBet(-1);}//if dealer is higher than player, dealer wins
            else if(playerScore == dealerScore){System.out.println("Draw"); p.settleBet(0);}//if dealer is equal than player, draw
            else if(playerScore == dealerScore && p.blackjack()){System.out.println("Player wins with BlackJack"); p.settleBet(1);}//if dealer is equal than player but player has Blackjack. Player wins
            else if(playerScore == dealerScore && blackjack()){System.out.println("Dealer wins with BlackJack"); p.settleBet(-1);}//if dealer is equal than player but dealer has Blackjack. Dealer wins
            else if(playerScore > dealerScore && p.blackjack()){System.out.println("Player wins with BlackJack"); p.settleBet(1);}//if dealer is equal than player but player has Blackjack. Player wins
            else if(playerScore < dealerScore && blackjack()){System.out.println("Dealer wins with BlackJack"); p.settleBet(-1);}//if dealer is equal than player but dealer has Blackjack. Dealer wins
            
            
            cardsPlayed.addAll(p.newHand().hand);
            
        }      
        cardsPlayed.addAll(newHand().hand);
        for(Player p: listOfPlayers)
        {
            p.viewCards(cardsPlayed);
        }
    }
    
}
