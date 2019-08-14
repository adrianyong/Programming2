/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cyu15mtu
 */
public class BlackjackTable implements Serializable {
    
    static final long serialVersionID = 152;
    
    /*initialise the game */
    public void initBasicGame()
    {
        BlackjackDealer dealer = new BlackjackDealer();         //make a new dealer
        BasicPlayer player1 = new BasicPlayer();                //make a new player
        BasicPlayer player2 = new BasicPlayer();
        BasicPlayer player3 = new BasicPlayer();
        BasicPlayer player4 = new BasicPlayer();
        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
        players.add(player1);                                   //add the players to the list
        players.add(player2);
        players.add(player3);
        players.add(player4); 
        dealer.assignPlayers(players); 
    }
    public void basicGame()
    {
        Scanner scan =  new Scanner(System.in);                   //scanner for input
        int scanInt = 0;

       
        BlackjackDealer dealer = new BlackjackDealer();         //make a new dealer

        /* dealer commands here */
                              //assign the players to the dealer.
        //System.out.println(numOfPlayers);
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
        boolean checker = true;
       
        while(checker || !.isEmpty())
        {
            //System.out.println(scanInt);
            for(int i=0; i < scanInt; i++)
            {
                    System.out.println("**********Round number " + (i+1)+"**********");
                    dealer.takeBets();                                   //take the bets of the players                  
                    System.out.println("Dealing Cards.");
                    dealer.dealFirstCards();  //deals the first cards
                    int j = 0;                  //used to count number of players
                    for (Player p: players)
                    {
                        System.out.println("----Player's " + (j+1) +" hand----");
                        System.out.println(p.getHand().toString());        
                        System.out.println("Player " + (j+1) +" balance: " + p.getBalance());
                        System.out.println("Player " + (j+1) +" bet: " + p.getBet());
                        
                        j++;

                    /*player 1 hit or stick */
                    while(p.hit() && !p.isBust())          //while hit is true
                    {
                        dealer.play(p);                    //deals player 1 a card
                        System.out.println(p.getHand().toString()); //prints out the cards
                        dealer.deckCheck();
                        System.out.println("Is player bust: " + p.isBust());
                    }

                    }

                    System.out.println("----Dealer's hand----");
                    dealer.playDealer();                                 //dealer plays
                    System.out.println(dealer.dealerHand.toString());    //outputs dealer hand

                    dealer.settleBets();                                 //settles hand
                    int k = 0;
                    for (Player p: players)
                    {
                        System.out.println("Player " + (k+1) +" balance: "  + p.getBalance());  //outputs balance
                        k++;
                    }
                    players.removeIf(player -> player.isBroke());
                    if (players.isEmpty()){break;}

            }
    if(scanInt == 0 || players.isEmpty())
    {
        checker=false;
        break;
    }
    else
    {
        System.out.println("Do you want the save the game? (y) yes, (n) no: ");
        char c = scan.next().charAt(0);
        if (c == 'y')
        {
            try 
            {
                FileOutputStream fileOut = new FileOutputStream("table.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in table.ser");
            } 
            catch (IOException i) 
            {
                i.printStackTrace(); 
            }
        }
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
        }
      }
    }  
        
    public void humanGame()
    {
        Scanner scan = new Scanner(System.in);                   //scanner for input
        int scanInt = 0;

        BlackjackTable table = new BlackjackTable();             //make a table
        BlackjackDealer dealer = new BlackjackDealer();         //make a new dealer


        HumanPlayer humanPlayer = new HumanPlayer();                //make a new player
        BasicPlayer player1 = new BasicPlayer();

        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
        players.add(humanPlayer);                                   //add the players to the list
        players.add(player1); 
        
        /* dealer commands here */
        dealer.assignPlayers(players);                       //assign the players to the dealer.
        
        
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
        boolean checker = true;
       
        while(checker || !players.isEmpty())
        {
            
            for(int i=0; i < scanInt; i++)
            {
                            System.out.println("**********Round number " + (i+1)+"**********");
                            dealer.takeBets();                                   //take the bets of the players
                                                  
                            
                            System.out.println("Dealing Cards.");
                            dealer.dealFirstCards();  //deals the first cards
                            int j = 0;                  //used to count number of players
                              
                            /* Human player */
                              while(!humanPlayer.isBust())
                              {
                                 
                                  if(humanPlayer.hit())
                                  {
                                    dealer.play(humanPlayer);          //deals player 1 a card
                                    System.out.println(humanPlayer.getHand().toString()); //prints out the cards
                                    dealer.deckCheck();
                                  }
                                  else
                                  {
                                    break;
                                  }
                              }
                              
                              
                              while(player1.hit() && !player1.isBust()) //while hit is true
                              {
                                dealer.play(player1);                    //deals player 1 a card
                                System.out.println(player1.getHand().toString()); //prints out the cards
                                dealer.deckCheck();

                              }
                              

                            System.out.println("----Dealer's hand----");
                            dealer.playDealer();                                 //dealer plays
                            System.out.println(dealer.dealerHand.toString());    //outputs dealer hand

                            dealer.settleBets();                                 //settles hand
                            int k = 0;
                            for (Player p: players)
                            {
                                System.out.println("Player " + (k+1) +" balance: "  + p.getBalance());  //outputs balance
                                k++;
                            }
                            players.removeIf(player -> player.isBroke());
                            if (players.isEmpty()){break;}

            }
            if(scanInt == 0 || players.isEmpty())
            {
                checker=false;
            }
            else
            {
                System.out.println("Do you want the save the game? (y) yes, (n) no: ");
                char c = scan.next().charAt(0);
                if (c == 'y')
                {
                    try 
                    {
                        FileOutputStream fileOut = new FileOutputStream("table.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(this);
                        out.close();
                        fileOut.close();
                        System.out.println("Serialized data is saved in table.ser");
                    } 
                    catch (IOException i) 
                    {
                        i.printStackTrace(); 
                    }
                }
                System.out.println("How many rounds do you want to do: ");
                scanInt = scan.nextInt();
            }
        }  
    }
    
    
    
    public void intermediateGame()
    {
        Scanner scan =  new Scanner(System.in);                   //scanner for input
        int scanInt = 0;

        BlackjackTable table = new BlackjackTable();             //make a table
        BlackjackDealer dealer = new BlackjackDealer();         //make a new dealer


        IntermediatePlayer player1 = new IntermediatePlayer();                //make a new player
        IntermediatePlayer player2 = new IntermediatePlayer();
        IntermediatePlayer player3 = new IntermediatePlayer();
        IntermediatePlayer player4 = new IntermediatePlayer();

        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
        players.add(player1);                                   //add the players to the list
        players.add(player2);
        players.add(player3);
        players.add(player4);
        /* dealer commands here */
       
        dealer.assignPlayers(players);                       //assign the players to the dealer.
        //System.out.println(numOfPlayers);
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
        boolean checker = true;
       
        while(checker || !players.isEmpty())
        {
            //System.out.println(scanInt);
            for(int i=0; i < scanInt; i++)
            {
                    System.out.println("**********Round number " + (i+1)+"**********");
                    dealer.takeBets();                                   //take the bets of the players
                                                              //runs the check deck method
                    //System.out.println("New deck in play: " + dealer.deckCheck());
                    System.out.println("Dealing Cards.");
                    dealer.dealFirstCards();  //deals the first cards
                    int j = 0;                  //used to count number of players
                    System.out.println("----Dealer's first card----");
                    System.out.println(dealer.dealerHand.toString());    //outputs dealer hand
                    
                    for (Player p: players)
                    {
                        System.out.println("----Player's " + (j+1) +" hand----");
                        System.out.println(p.getHand().toString());        
                        System.out.println("Player " + (j+1) +" balance: " + p.getBalance());
                        System.out.println("Player " + (j+1) +" bet: " + p.getBet());
                        
                        j++;

                    /*player 1 hit or stick */
                    while(p.hit() && !p.isBust())          //while hit is true
                    {
                        dealer.play(p);                    //deals player 1 a card
                        System.out.println(p.getHand().toString()); //prints out the cards
                        dealer.deckCheck();
                        System.out.println("Is player bust: " + p.isBust());
                    }

                    }
                
                    System.out.println("----Dealer's hand----");
                    dealer.playDealer();                                 //dealer plays
                    System.out.println(dealer.dealerHand.toString());    //outputs dealer hand

                    dealer.settleBets();                                 //settles hand
                    int k = 0;
                    for (Player p: players)
                    {
                        System.out.println("Player " + (k+1) +" balance: "  + p.getBalance());  //outputs balance
                        k++;
                    }
                    players.removeIf(player -> player.isBroke());
                    if (players.isEmpty()){break;}

    }
    if(scanInt == 0 || players.isEmpty())
    {
        checker=false;
        break;
    }
    else
    {
        System.out.println("Do you want the save the game? (y) yes, (n) no: ");
        char c = scan.next().charAt(0);
        if (c == 'y')
        {
            try 
            {
                FileOutputStream fileOut = new FileOutputStream("table.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in table.ser");
            } 
            catch (IOException i) 
            {
                i.printStackTrace(); 
            }
        }
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
    }
        }
    }  
    
    public void advancedGame()
    {
        Scanner scan =  new Scanner(System.in);     
        int scanInt = 0;
        
        
        BasicPlayer p1 = new BasicPlayer(); //add a basic player
        IntermediatePlayer p2 = new IntermediatePlayer();   //add an intermediate player
        AdvancedPlayer p3 = new AdvancedPlayer();   //add an advanced player
        BlackjackDealer dealer = new BlackjackDealer(); //add a dealer
        
        
        ArrayList<Player> players = new ArrayList<Player>();    //make a list of players of type Player
        
        /*adds players to the list*/
        players.add(p1);  
        players.add(p2);
        players.add(p3);
        dealer.assignPlayers(players); 
        
        
        dealer.dealFirstCards();    //deal the first cards
        
       
        System.out.println("Dealer Card: " + dealer.dealerHand.toString()); //print out dealer hand
        
        System.out.println("How many rounds do you want to do: ");
        scanInt = scan.nextInt();
        boolean checker = true;
       
        while(checker || !players.isEmpty())
        {
            for(int i=0; i < scanInt; i++)
            {
                System.out.println("**********Round number " + (i+1)+"**********");
                dealer.takeBets();                                   //take the bets of the players
                                                          //runs the check deck method
                //System.out.println("New deck in play: " + dealer.deckCheck());
                System.out.println("Dealing Cards.");
                dealer.dealFirstCards();  //deals the first cards
                int j = 0;                //used to count number of players
                for (Player p: players)
                {
                    System.out.println("----Player's " + (j+1) +" hand----");
                    System.out.println(p.getHand().toString());        
                    System.out.println("Player " + (j+1) +" balance: " + p.getBalance());
                    System.out.println("Player " + (j+1) +" bet: " + p.getBet());

                    j++;

                /*player 1 hit or stick */
                    while(p.hit() && !p.isBust())          //while hit is true
                    {
                        dealer.deckCheck();
                        dealer.play(p);                    //deals player 1 a card
                        System.out.println(dealer.dealerHand.toString()); //prints out the cards
                        
                        System.out.println("Is player bust: " + p.isBust());
                        
                    }        
                }

                System.out.println("----Dealer's hand----");
                dealer.playDealer();                                 //dealer plays
                System.out.println(dealer.dealerHand.toString());    //outputs dealer hand


                dealer.settleBets();                                 //settles hand
                int k = 0;
                for (Player p: players)
                {
                    System.out.println("Player " + (k+1) +" balance: "  + p.getBalance());  //outputs balance
                    k++;
                }

                players.removeIf(player -> player.isBroke());
                if (players.isEmpty()){break;}
            }   
                
        
        if(scanInt == 0 || players.isEmpty())
        {
            checker=false;
            break;
        }
        else
        {
            System.out.println("Do you want the save the game? (y) yes, (n) no: ");
            char c = scan.next().charAt(0);
            if (c == 'y')
            {
                try 
                {
                    FileOutputStream fileOut = new FileOutputStream("table.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(this);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in table.ser");
                } 
                catch (IOException i) 
                {
                    i.printStackTrace(); 
                }
            }
            System.out.println("How many rounds do you want to do: ");
            scanInt = scan.nextInt();
        }
    }
}
    

    
        
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        BlackjackTable table = new BlackjackTable(); //blackjack table
        
        System.out.println("Would you like to load your previous game? (y) yes, (n) no: ");
        char c = scan.next().charAt(0);
        if(c == 'y')
        {
            try 
            {
                FileInputStream fileIn = new FileInputStream("table.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                table = (BlackjackTable) in.readObject();
                in.close();
                fileIn.close();
            } 
            catch (IOException i) 
            {
                i.printStackTrace();
                return;
            } 
            catch (ClassNotFoundException cnf) 
            {
                System.out.println("Table class not found");
                cnf.printStackTrace();
                return;
            }
        System.out.println("Please choose which game you'd like to do: ");
        System.out.println("1. Basic Game \n2. Human Game\n3. Intermediate Game\n4. Advanced Game\n5. Quit");
        
        switch(scan.nextInt())
        {
            case 1:
                table.basicGame();
                break;
            case 2:
                table.humanGame();
                break;
            case 3:
                table.intermediateGame();
                break;
            case 4:
                table.advancedGame();
                break;
            case 5:
                break;
               
        }
            
        }
        System.out.println("Please choose which game you'd like to do: ");
        System.out.println("1. Basic Game \n2. Human Game\n3. Intermediate Game\n4. Advanced Game\n5. Quit");
        
        switch(scan.nextInt())
        {
            case 1:
                this.initBasicGame();
                table.basicGame();
                break;
            case 2:
                table.humanGame();
                break;
            case 3:
                table.intermediateGame();
                break;
            case 4:
                table.advancedGame();
                break;
            case 5:
                break;
               
        }

       
    }
    
}
