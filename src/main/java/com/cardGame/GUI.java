package com.cardGame;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import com.cardGame.Card.Suit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Stack;



public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
    boolean hasBet = false;

    
    JPanel playerPile;
    JPanel dealerPile;
    private BlackJack game;
    public static String IMG_PATH = System.getProperty("user.dir")+"/src/main/java/com/cardGame/resources/";
   public GUI(BlackJack game){
       this.game= game;
       
        //Create and set up the window.
       setTitle("Black Jack");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // Supplies Background
       try {
        Image blackImg = ImageIO.read(new File(IMG_PATH+"background.jpg"));
       
        setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
           e.printStackTrace();
           
       }
       game.setup();
       // Creates the topPile JPanel located on the top middle of the application
       this.setLayout(null);
       dealerPile = new JPanel();
       dealerPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
       dealerPile.setBounds(10, 10, 860, 150);
       dealerPile.setOpaque(false);
       
       // Creates the middlePile JPanel located in the middle of the application 
       playerPile = new JPanel();
       playerPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));
       playerPile.setBounds(10, 170, 860, 150);
       playerPile.setOpaque(false);
       
       // Creates the JButton located at the bottom left of the application
       JButton hitButton = new JButton("Hit");
       hitButton.setBounds(10, 450, 150, 100);
       hitButton.setFont(new Font("Arial", Font.ITALIC, 50));
       
       // Creates the JButton located adjacent to hitButton for the application
       JButton standButton = new JButton("Stand");
       standButton.setBounds(170, 450, 150, 100);
       standButton.setFont(new Font("Arial", Font.ITALIC, 35));

       // Creates the JButton located adjacent to standButton for the application 
       JButton betButton = new JButton("Bet");
       betButton.setBounds(340, 450, 150, 100);
       betButton.setFont(new Font("Arial", Font.ITALIC, 50));

       // Creates the JPanel that is supposed to represent a stack of cards 
       JPanel stackOfCards = new JPanel();
       stackOfCards.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));
       stackOfCards.setBounds(700, 345, 150, 200);
       stackOfCards.setOpaque(false);
       
       // Creates a Card that will be added into the stackOfCards JPanel
       Card imageCard = new Card(1, Suit.Clubs);
       imageCard.hide();
       imageCard.setPreferredSize(new Dimension(130 , 180));
       stackOfCards.add(imageCard);
       
        //The topPile JPanel adds the JLayeredPane containing all of the cards
        //appended from the stack that is now converted into an array
        dealerPile.add(drawDealerPile());
        playerPile.add(drawPlayerPile());
      
        //Action Events
   
        hitButton.addActionListener(e -> {
        	if (hasBet == true) {
        		
				game.calculatePlayerValue();

				if (game.hasLost == true) {

				hitButton.setVisible(false);
				standButton.setVisible(false);
				betButton.setVisible(false);
				}

        		update();
        	}
        	else if (hasBet == false) {
        		System.out.println("You need to bet first!");
        	}
        	
        });
        
        betButton.addActionListener(e -> {
            	hasBet = true;
            	
            	Scanner myObj = new Scanner(System.in);
            	System.out.println("Please input the amount you are willing to bet");
            	String betInput = myObj.nextLine();
            	System.out.println("Transaction accepted");
            	
            	betButton.setVisible(false);
            	this.repaint();
         
        });
        
        standButton.addActionListener(e -> {
        	if (hasBet == true) {
        		
        		game.dealerTurn();

				if (game.hasStand == true) {

					standButton.setVisible(false);
					hitButton.setVisible(false);

				}
				game.calculateTie();
				game.calculateWinner();
        		update();
        		
        	}
        	else if(hasBet == false) {
        		System.out.println("You need to bet first!");
        		
        		
        	}
        	
        });
        
        
        
        //End of Action Events
        
        //GUI adds all of the JPanels previously mentioned
        
        this.add(dealerPile);
        this.add(playerPile);
        this.add(hitButton);
        this.add(standButton);
        this.add(betButton);
        this.add(stackOfCards);
       
        //Allows GUI to be seen when program is run
        
        this.setVisible(true);
      
    }

   private void update()  {
   	
   	playerPile.removeAll();
   	dealerPile.removeAll();
   	playerPile.add(drawPlayerPile());
   	dealerPile.add(drawDealerPile());

   	System.out.println("Updating visuals");
   	this.revalidate();
   	this.repaint();
   }
      
    // Method declaration for drawDealerPile
 
    public JLayeredPane drawDealerPile() {
        Object[] cards = game.dealerHand.toArray();

        JLayeredPane jLPane = new JLayeredPane();
        jLPane.setOpaque(false);
        jLPane.setPreferredSize(new Dimension(850, 300));

        int x = 0;
        int y = 0;
        int offsetX = 42;
        int offsetY = 120;
        int cardWidth = 100;
        int cardHeight = 120;

        for (int i = 0; i < cards.length; i++) {
            Card c = (Card) cards[i];

        // Start a new row if the next card would exceed the width
            if (x + cardWidth > 850) {
                x = 0;
                y += offsetY;
            }

        // Position the card
            c.setBounds(x, y, cardWidth, cardHeight);

        // Add the card to the layered pane
            jLPane.add(c, Integer.valueOf(i));

        // Advance to the next column
            x += offsetX;
    }
    return jLPane;
}
    
    // Method declaration for drawPlayerPile
    
    public JLayeredPane drawPlayerPile() {
    	Object[] cards = game.playerHand.toArray();
    	
    	JLayeredPane jLPane = new JLayeredPane();
    	jLPane.setOpaque(false);
    	jLPane.setPreferredSize(new Dimension(850, 300));
    	
    	int x = 0;
    	int y = 0;
    	int offsetX = 42;
    	int offsetY = 120;
    	int cardWidth = 100;
    	int cardHeight = 120;
    	
    	for (int i = 0; i < cards.length; i++) {
    		Card c = (Card) cards[i];
    		
    	// Start a new row if the next card would exceed the width 
    		if (x + cardWidth > 850) {
    			x = 0;
    			y = offsetY;
    		}
    	// Position the card
    		c.setBounds(x, y, cardWidth, cardHeight);
    	
    	// Add the card to the layered pane 
    		jLPane.add(c, Integer.valueOf(i));
    		
    	// Advance to the next column
    		x += offsetX;
    		
    	}
    
    	return jLPane;
    }
    
    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    	
    	
    	
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    	
    	
    }


    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    }

    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
       
    }
}