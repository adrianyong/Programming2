
package blackjack;
import blackjack.Card.Rank;
import blackjack.Card.Suit;
import java.io.Serializable;
import java.util.Comparator;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Deck implements Iterable, Serializable {
    static final long serialVersionID = 112;
    ArrayList<Card> deck = new ArrayList<Card>();
    
    public Deck()                           //deck constructor
    {
       
        for(Rank r : Rank.values())         //for each rank in the enum
        {
            for(Suit s : Suit.values())     //for each suit in the enum   
            {
                deck.add(new Card(r,s));    //put a card into the deck
            }
        }
    }
    
    /* iterator */
    @Override
    public Iterator<Card> iterator()        //iterator method
    {
        return new SecondCardIterator();    //returns every other card
    }
    
    /* toString outputs each card in the deck */
    @Override
    public String toString()                
    {
        String deckOutput = "";             
        for(int i=0;i < deck.size(); i++)   //for each card in the deck
        {
           String cardOut = deck.get(i).toString(); //gets the card and uses toString method()
           deckOutput += cardOut += "\n";   //adds the card to the output string
        }
        return deckOutput; 
    }
    
    public Card deal()
    {
       Card topDeck = deck.get(0);
       deck.remove(0);
       return topDeck;
    }   
    
    public int deckSize()
    {
        int deckSize = deck.size();         //uses .size() method to return deckSize
        return deckSize;
    }
    
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
    
    public void newDeck()
    {
        deck.clear();                       //clears the deck
        for(Rank r : Rank.values())         //same method as the constructor
        {
            for(Suit s : Suit.values())
            {
                deck.add(new Card(r,s));
            }
        }
    }


    
    private class deckIterator implements Iterator<Card>
    {
        int pos=0;
        
        @Override
        public boolean hasNext() {
            if(pos >= deck.size())  //if the counter is bigger than the deck
            {
                pos=0;              //reset the counter back to zero
                return false;       //there will be no next card
            }
            else 
                return true;        
        }

        @Override
        public Card next() {
            return deck.get(pos++); 
        } 
    }
    
    
    
     private class SecondCardIterator implements Iterator<Card>
    {
        int pos=0;
        
        @Override
        public boolean hasNext() {
            if(pos >= deck.size())  //if the counter is bigger than the deck
            {
                pos=0;              //reset the counter back to zero
                return false;       //there will be no next card     
            }
            else
                return true;        //hasNext continues
        }

        @Override
        public Card next() 
        {
            if (hasNext())          //if the next card is present
            {
                Card c;             //instantiates new card
                c = deck.get(pos);  //card will be retrieved from position
                pos +=2;            //increment position by 2
                return c;           //return the card printed
            }
            else return null;
        }
            
        
    }
    
    
//    public static void main(String[] args) {
//        
// 
//        Deck d1 = new Deck();
//        Iterator<Card> it = d1.iterator();
//                
//        while(it.hasNext())
//        {
//            Card c = it.next();
//            System.out.println(c);
//        }
//              
//        //deck.shuffle();
//        
//    }
    
    
}
