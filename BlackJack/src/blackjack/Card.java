package blackjack;
import java.io.Serializable;
import java.util.Comparator;
//import java.lang.String;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;

public class Card implements Comparable<Card>, Serializable 
{

    static final long serialVersionID = 111;

       /* define the Suit enumerator, definined in the 4 suits of a deck with 
    Suit.CLUBS, etc/ */
    public enum Suit 
    {

        CLUBS(2), DIAMONDS(4), HEARTS(6), SPADES(8);
        private int suitValue;

        Suit(int suitValue)
        {
            this.suitValue = suitValue;
        }

        public int getSuitValue()
        {
            return suitValue;
        }

        public int getPrevSuitValue()
        {
            return suitValue;
        }    
    }

    /* define the Rank enumerator, definined by Rank.ACE*/
    public enum Rank 
    {

        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),JACK(10), QUEEN(10), KING(10), ACE(11);

        private int rankValue;  

        Rank(int rankValue)
        {
            this.rankValue= rankValue;  
        }

        public int getRankValue()
        {
            return rankValue;
        }

    }

    /* setup global variables */
    Rank rank;
    Suit suit;

    /*setup card*/
    public Card(Rank rank, Suit suit)
    {
        this.rank=rank; 
        this.suit=suit;
    }

    /*getter initialisation */

    public Rank getRank()
    {
        return rank;                        //returns rank
    }

    public Suit getSuit()
    {
        return suit;                        //returns suit
    }

    public Rank getPrevious()
    {
        int rankValue=rank.ordinal()-1;     //converts the value of the rank into int
        if (rankValue < 0)                  // if the rank is below a TWO (which is 0 in this case)
            rankValue=12;                   // set rank to ace
        return Rank.values()[rankValue];

    }

    public int getValue()
    {
        return rank.getRankValue();         //gets the rank value
    }

    public static int sum(Card card1, Card card2)
    {
        int rank1 = card1.getValue();       // gets card 1's rank value
        int rank2 = card2.getValue();       // gets card 2's rank value

        int sum = rank1 + rank2;            //sum of 2 cards value
        return sum;
    }


    public static boolean isBlackjack(Card card1, Card card2)
    {
        boolean isBlackJack = false;        //set blackjack to being false
        if (sum(card1,card2) == 21)         //if the sum of two cards is 21
            isBlackJack = true;             //BlackJack is set to true

        return isBlackJack;                 //return boolean of isBlackJack
    }
   
    @Override
    public String toString()
    {
            return getRank() + " of "+ getSuit(); 
    }


    /* Comparators */
    
    public static class compareAscending implements Comparator<Card>
    {

        @Override
        public int compare(Card card1, Card card2) 
        {
            return (card1.rank).compareTo(card2.rank);
        }
    }
    
    
    public static class compareSuit implements Comparator<Card>
    {

        @Override
        public int compare(Card card1, Card card2) 
        {
            return (card1.suit).compareTo(card2.suit);
        }
    }
    
    @Override
    public int compareTo(Card card) {
        int cardDifference =0;              //initialise cardDifference
        int cardRank1 = card.getValue();    //gets value of comparator card
        int cardRank2 = rank.getRankValue();//gets value of current card   
        cardDifference = cardRank2 - cardRank1;//difference betweent comparator and curremt
        return cardDifference;              //return the difference
    }
    
    
//    public static void main(String[] args) 
//    {
//        /*initial cards*/
//        Card.Rank rank = Card.Rank.TEN;
//        Card.Suit suit = Card.Suit.SPADES;
//        Card card1 = new Card(rank, suit);
//        
//        Card.Rank rank2 = Card.Rank.TEN;
//        Card.Suit suit2 = Card.Suit.DIAMONDS;
//        Card card2 = new Card(rank2, suit2);
//        
//        Card.Rank rank3 = Card.Rank.TWO;
//        Card.Suit suit3 = Card.Suit.CLUBS;
//        Card card3 = new Card(rank3, suit3);
//        
//        Card.Rank rank4 = Card.Rank.SIX;
//        Card.Suit suit4 = Card.Suit.HEARTS;
//        Card card4 = new Card(rank4, suit4);
//        
//        
//        /*test for blackJack */
//        System.out.println("*** Check for BlackJack ***");
//        System.out.println(Card.sum(card1, card2));
//        System.out.println(Card.isBlackjack(card1, card2));
//        
//        /*adds cards to the list */
//        ArrayList<Card> testD = new ArrayList<Card>();
//        testD.add(card1);
//        testD.add(card2);
//        testD.add(card3);
//        testD.add(card4);
//        
//        for(Card c: testD)
//        {
//            System.out.println(c.toString());
//        }
//        
//        System.out.println("*** Compare Ascending Rank ***");
//        
//        Collections.sort(testD, new compareSuit());
//        Collections.sort(testD, new compareAscending());
//        
//        
//        for(Card c: testD)
//        {
//            System.out.println(c.toString());
//        }
        
//        System.out.println("*** Compare Ascending Suit ***");
//        Collections.sort(testD, new compareSuit());
//        for(Card c: testD)
//        {
//            System.out.println(c.toString());
//        }
    }





