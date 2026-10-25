package com.cardGame;
import javax.imageio.ImageIO;
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

	private Solitaire game;
	public static String IMG_PATH = System.getProperty("user.dir")+"/src/main/java/com/cardGame/resources/";
   public GUI(Solitaire game){
	   this.game= game;
        //Create and set up the window.
       setTitle("Solitaire");
       setSize(900,700);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // this supplies the background
       try {
		Image blackImg = ImageIO.read(new File(IMG_PATH+"background.jpg"));
		
		setContentPane(new ImagePanel(blackImg));

       }catch(IOException e) {
    	   e.printStackTrace();
       }
       
       this.setLayout(null);
       JPanel topPile = new JPanel();
       topPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));
       topPile.setBounds(10, 10, 860, 300);
       // top left is (0, 0 for coords)
       topPile.setOpaque(false);
       
       JPanel cardPile = new JPanel();
       cardPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.yellow));
       cardPile.setBounds(10, 350, 300, 200);
       cardPile.setOpaque(false);
       
       JPanel bottomPile = new JPanel();
       bottomPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
       bottomPile.setBounds(330, 350, 525, 200);
       bottomPile.setOpaque(false);
       
     

       
       // Card Layers Using LayeredPane 
     //here’s the function definition 

    

       
       /*******
        * This is just a test to make sure images are being read correctly on your machine. Please replace
        * once you have confirmed that the card shows up properly. The code below should allow you to play the solitaire
        * game once it's fully created.
        */
      
        //testing the drawPile
        Stack<Card> test = new  Stack<Card>();
        test.push(new Card(2, Suit.Diamonds));
        test.push(new Card(4, Suit.Diamonds));
        test.push(new Card(5, Suit.Diamonds));
        test.push(new Card(6, Suit.Diamonds));
        test.push(new Card(6, Suit.Spades));
        test.push(new Card(3, Suit.Spades));
        test.push(new Card(1, Suit.Spades));
        test.push(new Card(9, Suit.Spades));
        test.push(new Card(10, Suit.Clubs));
        test.push(new Card(8, Suit.Clubs));
        test.push(new Card(7, Suit.Clubs));
        test.push(new Card(9, Suit.Clubs));
        test.push(new Card(2, Suit.Hearts));
        test.push(new Card(5, Suit.Hearts));
        test.push(new Card(10, Suit.Hearts));
        test.push(new Card(9, Suit.Hearts));
        test.push(new Card(6, Suit.Diamonds));
        test.push(new Card(8, Suit.Diamonds));
        test.push(new Card(4, Suit.Diamonds));
        
        topPile.add(drawPile(test));
        
        this.add(topPile);
        this.add(cardPile);
        this.add(bottomPile);
        
        this.setVisible(true);
    }

   // Note have a separate class file for the game logic, possibly use a update function 
   
   public JLayeredPane drawPile(Stack<Card> stackIn) {
	    Object cards[];
	    cards = stackIn.toArray(); //please note we convert this stack to an array so that we can iterate 
	    //through it backwards while drawing. You’ll need to cast each element 
	    //inside cards to a <Card> in order to use the methods to adjust their position
	    
	   
//	   Card card = new Card(3, Card.Suit.Hearts);
//	   jLPane.add(card);
	   JLayeredPane jLPane = new JLayeredPane();
       jLPane.setOpaque(false);
	   jLPane.setPreferredSize(new Dimension(850, 300));
	   
	   for (int i = 0; i <= cards.length-1; i++) {
		   
		   Card c = (Card)cards[i];
		   c.setBounds(i* 40, 50, 100, 150);
           int layerNumber = i;
		   jLPane.add(c, Integer.valueOf(layerNumber));
		   
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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
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

