/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class HumanPlayer extends BasicPlayer implements Player {
    
    Scanner scan = new Scanner(System.in);
    
    public int makeBet() {
        
        System.out.println("Please enter your bet: ");
        bet = scan.nextInt(); //bet will be the user input
        return bet;
    }
    
    
    @Override
    public boolean hit() 
    {
        
        boolean hit = false;  //hit set to false
        //int result = playerHand.optimalValue();   //result is the optimal value for array
        
        System.out.println("Would you like to hit (h) or stick (s)");
        char c = scan.next().charAt(0);
        if(c == 'h')
        {
            hit = true; //hit set to true
        }
        
        return hit;
        
    }
    
    @Override
    public void newDeck() {
        System.out.println("New deck in play.");
    }
    
    
}
