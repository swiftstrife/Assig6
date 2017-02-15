import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author BigMac
 *
 */
public class GameView extends JFrame
{

   int NUM_CARDS_PER_HAND = 7;
   int NUM_PLAYERS = 2;
   int[] stackCount =
   { 52, 52 };
   private MouseListener gameListener;
   JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   CardLabel[] playedCardLabels = new CardLabel[NUM_PLAYERS];
   JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
   JLabel[] results = new JLabel[NUM_PLAYERS];
   JLayeredPane[] playStack = new JLayeredPane[NUM_PLAYERS];
   public JPanel pnlComputerHand = new JPanel();
   public JPanel pnlHumanHand = new JPanel();
   public JPanel pnlPlayArea = new JPanel();
   public JPanel pnlTimer = new JPanel();
   JLabel lblCantPlay = new JLabel("0", JLabel.CENTER);
   JLabel compCantPlay = new JLabel("0", JLabel.CENTER);
   JPanel playPanel = new JPanel();
   JPanel mainPanel = new JPanel();
   JPanel timerPanel = new JPanel();
   JButton cantPlayButton = new JButton("Can't Play");
   JPanel buttonPanel = new JPanel();
   JPanel comPanel = new JPanel();


   GameView(String title, int numCardsPerHand, int numPlayers)
   {
      super(title);
      mainPanel.setLayout(new GridLayout(3, 1));
      timerPanel.setLayout(new GridLayout(3, 1));
      playPanel.setLayout(new GridLayout(3, 1));
      playPanel.setBorder(BorderFactory.createTitledBorder("Player Resets"));
      comPanel.setBorder(BorderFactory.createTitledBorder("Comp Resets"));
      comPanel.add(compCantPlay);
      playPanel.add(lblCantPlay);
      buttonPanel.add(cantPlayButton);
      playPanel.add(buttonPanel);
      mainPanel.add(pnlComputerHand);
      mainPanel.add(pnlPlayArea);
      mainPanel.add(pnlHumanHand);
      timerPanel.add(comPanel);
      timerPanel.add(pnlTimer);
      timerPanel.add(playPanel);
      setSize(475, 500);
      pnlComputerHand.setBorder(BorderFactory.createTitledBorder("Computer Hand"));
      pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
      pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Area"));
      pnlTimer.setBorder(BorderFactory.createTitledBorder("Clock"));
      pnlPlayArea.setLayout(new GridLayout(1, 2));
      pnlTimer.setLayout(new GridLayout(3, 1));
      this.add(mainPanel, BorderLayout.CENTER);
      this.add(timerPanel, BorderLayout.EAST);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      GUICard.loadCardIcons();

      // Add timer
      Clock insertClock = new Clock();
      pnlTimer.add(insertClock.timeText);
      pnlTimer.add(insertClock.startStopButton);

      insertClock.timeText.setFont(new Font("Calibri", Font.CENTER_BASELINE, 40));
   }

   public void addGameListener(MouseListener gameListener)
   {
      this.gameListener = gameListener;

   }

   public void showWinner()
   {
      JOptionPane.showMessageDialog(null, "The game is won by");
   }

   // refreshes the screen.
   public void refresh()
   {
      pack();
   }

   /**
    * shows computer hand
    * 
    * @param hand
    */
   public void showComputerHand(GameModel model)
   {
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         computerLabels[i] = new CardLabel(model.dealCardFromComputerHand());
         ((CardLabel) computerLabels[i]).flip();
         
         pnlComputerHand.add(computerLabels[i]);
      }
      this.pack();
   }

   /**
    * Show the hands of the player (currently setup to just add a computer
    * player and human)
    * 
    * @param hand
    */
   public void showHands(GameModel model)
   {
      for (int i = 0; i < NUM_CARDS_PER_HAND; i++)
      {
         humanLabels[i] = new CardLabel(model.dealCardFromHumanHand());
         humanLabels[i].addMouseListener(this.gameListener);
         humanLabels[i].setBackground(Color.white);
         ((CardLabel) humanLabels[i]).flip();
         pnlHumanHand.add(humanLabels[i]);
      }

   }

   /**
    * shows play area. (The setup is currently based on the assignment 5 phase
    * 3.)
    */
   public void showPlayArea()
   {
      // TODO update for multiple players.

      for (int j = 0; j < NUM_PLAYERS; j++)
      {
         playedCardLabels[j] = new CardLabel();
         playedCardLabels[j].setPlayed(true);
         playedCardLabels[j].addMouseListener(gameListener);
         playStack[j] = new JLayeredPane();
         playedCardLabels[j].setBounds(stackCount[j] * 2, 0, GUICard.getBackCardIcon().getIconWidth(), GUICard.getBackCardIcon().getIconHeight());
         playStack[j].add(playedCardLabels[j], stackCount[j] * 2);
         pnlPlayArea.add(playStack[j]);
      }
   }

   public void addCardToPlayArea(CardLabel sourceCard, CardLabel destinationCard)
   {
      for (int i = 0; i < playStack.length; i++)
      {
         JLayeredPane jlp = playStack[i];
         if (destinationCard.getParent() == jlp)
         {
            stackCount[i]--;
            jlp.add(sourceCard, new Integer(0), 0);
            repaint();
            sourceCard.setBounds(stackCount[i] * 2, 0, GUICard.getBackCardIcon().getIconWidth() * 3, GUICard.getBackCardIcon().getIconHeight());
            sourceCard.setPlayed(true);
         }
      }
      for (int j = 0; j < playedCardLabels.length; j++)
      {
         System.out.println("Played Card" + j + playedCardLabels[j].getCard());
         if (playedCardLabels[j] == destinationCard)
         {
            playedCardLabels[j] = sourceCard;
         }
      }
      
      repaint();
   }

   public void changeCursorImage(CardLabel card)
   {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Image image = ((ImageIcon) card.getIcon()).getImage();
      Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(), getY()), "img");
      setCursor(c);

   }

   void setScore(int score)
   {
      lblCantPlay.setText("" + score);
      refresh();
   }
   void setComputerScore(int score)
   {
      compCantPlay.setText(""+score);
      refresh();
   }


   public void addButtonListener(ActionListener buttonListener)
   {
      cantPlayButton.addActionListener(buttonListener);

   }

   public void addCardToHand(Card cardFromHumanHand)
   {
      CardLabel card = new CardLabel(cardFromHumanHand);
      card.addMouseListener(this.gameListener);
      // card.addMouseMotionListener((MouseMotionListener) this.gameListener);
      this.pnlHumanHand.add(card);
      // player1.add(humanLabels[i], -i);

   }
   public void addCardToComputerHand(Card cardFromComputerHand)
   {
      CardLabel card = new CardLabel(cardFromComputerHand);
      this.pnlComputerHand.add(card);

   }
   public CardLabel getCompSource()
   {
      Random ran = new Random();
      boolean needCard = true;
      Component check = null;
      int i = 0;
      while(needCard){
      check = pnlComputerHand.getComponentAt(ran.nextInt(pnlComputerHand.getWidth()), ran.nextInt(pnlComputerHand.getHeight()));
      if(check instanceof CardLabel){
         needCard = false;
      }
      i++;
      }
      return (CardLabel)check;
   }

   public CardLabel[] getDestination()
   {
      return playedCardLabels;
      
   }





}
