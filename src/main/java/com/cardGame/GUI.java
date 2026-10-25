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
       
       // Creates the topPile JPanel located on the top middle of the application
       this.setLayout(null);
       JPanel topPile = new JPanel();
       topPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));
       topPile.setBounds(10, 10, 860, 300);
       topPile.setOpaque(false);
       
       // Creates the cardPile JPanel located on the bottom left of the application
       JPanel cardPile = new JPanel();
       cardPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.yellow));
       cardPile.setBounds(10, 350, 300, 200);
       cardPile.setOpaque(false);
       
       // Creates the bottomPile JPanel located on the bottom right of the application
       JPanel bottomPile = new JPanel();
       bottomPile.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
       bottomPile.setBounds(330, 350, 525, 200);
       bottomPile.setOpaque(false);
       
        // Creating the stack for the cards
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
        test.push(new Card(9, Suit.Clubs));
        test.push(new Card(2, Suit.Hearts));
        test.push(new Card(5, Suit.Hearts));
        test.push(new Card(10, Suit.Hearts));
        test.push(new Card(9, Suit.Hearts));
        test.push(new Card(6, Suit.Diamonds));
        test.push(new Card(8, Suit.Diamonds));
        test.push(new Card(4, Suit.Diamonds));
        
        //The topPile JPanel adds the JLayeredPane containing all of the cards
        //appended from the stack that is now converted into an array
        topPile.add(drawPile(test));
        
        //GUI adds all of the JPanels previously mentioned
        this.add(topPile);
        this.add(cardPile);
        this.add(bottomPile);
        
        //Allows GUI to be seen when program is run 
        this.setVisible(true);
    }

    // Method declaration for drawPile
 
    public JLayeredPane drawPile(Stack<Card> stackIn) {
        Object[] cards = stackIn.toArray();

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

