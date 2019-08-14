  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import blackjack.Card.Rank;
import blackjack.Card.Suit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author cyu15mtu
 */
public class Hand implements Iterable, Serializable {
        static final long serialVersionID = 102;
    
        ArrayList<Card> hand = new ArrayList<Card>();
        int[] rankCount;
        
        /* initial constructor */
        public Hand() //empty constructor for empty hand
        {
            hand = new ArrayList<Card>(); //makes new blank hand
        } 
        
        public Hand(Card[] card)
        {
            hand.addAll(Arrays.asList(card));
            handRankCount();
        }
        
        public Hand(Hand newHand)
        {
            hand.addAll(newHand.hand);
            handRankCount();
        }
        
        private void handRankCount()
        {
            int[] count = new int[13];      //initialise the array of len 13 for each rank
            for(Card c: hand)               //for each card in the hand
            {
                count[c.rank.ordinal()]++;  //add the integer value for the rank into the count array
            }
            rankCount = count;              //rankCount array becomes the previous count array
            
        }
        
        public int[] handTotalValue()
        {
            int[] sum;        //set sum size to the BlackJack official limit
            sum = new int[] {0,0,0,0,0}; 
            int numofAces=0; //number of aces 
            for (Card c: hand) //for each card in the hand
            {
                if(c.rank == Rank.ACE)  //if the card's rank is an ace
                    {
                        numofAces++;    //increment the ace counter
                    }
            }
            for(int i=0;i < hand.size(); i++)   //for each card in the hand
            {
                Card c = hand.get(i);   //get the card on the counter
                sum[0] = sum[0] + c.getValue(); //sum first index is the card value
                for(int j=0; j< numofAces; j++) //from 0 to the number of aces in the hand
                {
                    sum[j+1]=sum[j]-10; 
                }

            }
            return sum;
        }
        
        public int optimalValue()
        {
            int optimalValue =0;
            int[] totalValues = handTotalValue();
            for(int i=0;i < totalValues.length;i++)
            {
                if(totalValues[i] <= 21 && totalValues[i] > optimalValue)
                {
                    optimalValue = totalValues[i];
                }
                
            }
            
            return optimalValue;
        }
        
        @Override        
        public String toString()
        {
            String handOutput = "";
            
            for(int i=0;i < hand.size(); i++) //for every card in the hand
            {
               String cardOut = hand.get(i).toString(); //output card to screen
               handOutput += cardOut += ",";
            }
            return handOutput; 
        }
        
        /* adder methods */
        
        public void addSingleCard(Card card)
        {
            hand.add(card); //adds one card to the hand
            handRankCount();
        }
        
        public void addCollection(Collection cardCol)
        {
            hand.addAll(cardCol);   //adds collection to hand
            handRankCount();
        }
        
        public void addHand(Hand h)
        {
            hand.addAll(h.hand); //adds previous hand to new hand
            handRankCount();
        }
        
        
        /* remove methods*/ 
        
        public boolean removeCard(Card card)
        {
            boolean allRemoved = false; 
            hand.remove(card);      //removes a card from the hand
            if (hand.isEmpty())     //if the hand is empty
            {
               allRemoved = true;   //all the cards removed will be true
            }
           handRankCount();
           return allRemoved;
           
        }
        
        
        
        public boolean removeHand(Hand h)
        {
            boolean allRemoved = false;
            if(hand.containsAll(h.hand))
            {
                hand.removeAll(h.hand);
                allRemoved = true;
            }
            handRankCount();
            return allRemoved;
            
        }
        
        public Card removeCardPosition(int cardPosition)
        {
            Card removedCard;
            removedCard = hand.get(cardPosition);
            hand.remove(cardPosition);
            handRankCount();
            return removedCard;
            
        }
        
        public void sortAscending()
        {
            Collections.sort(hand, new Card.compareSuit());
            Collections.sort(hand, new Card.compareAscending());
        }
        
        public void sortDescending()
        {
            Collections.sort(hand);
        }

        public int countSuit(Suit suit)
        {
            int numofSuits=0;
            for(Card c: hand)               //for each card in the hand
            {
                if(c.getSuit() == suit)
                    numofSuits++;               //add the integer value for the rank into the count array
            }
            return numofSuits;
        }
        
        public int countRank(Rank rank)
        {
            int numofRanks=0;
            for(Card c: hand) //for each card in the hand
            {
                if(c.getRank() == rank)
                    numofRanks++;  //add the integer value for the rank into the count array
            } 
            return numofRanks;
        }
        
        public boolean isOver(Hand hand)
        {
            boolean isOver = false;         //sets isOver to false
            int [] sum;
            sum = hand.handTotalValue();
            if (sum[0] > 21)
                    isOver = true;            
            return isOver;
        }
            
        public Hand reverseHand()
        {
            Collections.reverse(hand);      //reverses the current hand
            Hand newHand = new Hand();      //make new hand
            newHand.addCollection(hand);    //add reversed hand to new hand
            return newHand;                 //return hand
        }
        
        
        @Override
        public Iterator iterator() 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
//        public static void main(String[] args) 
//        {
//            Hand h1 = new Hand();           
//                        
//            Card.Rank rank = Card.Rank.ACE;
//            Card.Suit suit = Card.Suit.SPADES;
//            Card card1 = new Card(rank, suit);
//        
//            Card.Rank rank2 = Card.Rank.ACE;
//            Card.Suit suit2 = Card.Suit.DIAMONDS;
//            Card card2 = new Card(rank2, suit2);
//            Card card3 = new Card(rank.ACE, suit.SPADES);
//            
//            h1.addSingleCard(card1);
//            h1.addSingleCard(card2);
//            
//            int [] sum = h1.handTotalValue();
//            
//            System.out.println("Hand 1");
//            System.out.println(h1.toString());
//            System.out.println("Is Over?: " + h1.isOver(h1));
//            System.out.println("Sum: " + Arrays.toString(sum));
//            System.out.println("Optimal Value: " + h1.optimalValue());
//            System.out.println("Adding card...");
//            
//            h1.addSingleCard(card3);
//            int [] sum2 = h1.handTotalValue();
//            
//            System.out.println("Hand 2");
//            System.out.println(h1.toString());
//            System.out.println("Is Over?: " + h1.isOver(h1));
//            System.out.println("Sum: " + Arrays.toString(sum2));
//            System.out.println("Optimal Value: " + h1.optimalValue());
//            System.out.println("Reversing hand...");
//            h1.reverseHand();
//            System.out.println(h1.toString());
//        }
}
