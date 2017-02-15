import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameController
{
   private GameModel model;
   private GameView view;

   public GameController(GameModel model, GameView view)
   {
      this.model = model;
      this.view = view;
      this.view.addGameListener(new GameListener());
      this.view.addButtonListener(new ButtonListener());
      view.showPlayArea();
      this.view.showHands(model);
      this.view.showComputerHand(model);

   }

   class GameListener implements MouseListener, MouseMotionListener
   {
      CardLabel sourceCard;
      CardLabel destinationCard;

      @Override
      public void mousePressed(MouseEvent e)
      {
         sourceCard = (CardLabel) e.getSource();
         System.out.println(((CardLabel) e.getSource()).getCard().toString());
         CardLabel card = ((CardLabel) e.getSource());
         if (card.faceUp == false)
         {
            card.flip();
         }
         if (card.isPlayed() == false)
         {
            view.changeCursorImage(card);

            view.refresh();
         }
         
      }

      @Override
      public void mouseReleased(MouseEvent e)
      {
         if (sourceCard != null && destinationCard.isPlayed())
         {
            if (model.isPlayable(sourceCard.getCard(), destinationCard.getCard()))
            {
               view.addCardToPlayArea(sourceCard, destinationCard);
               view.addCardToHand(model.dealCardFromHumanHand());
               model.setTopCard(destinationCard, sourceCard);
               Card bestMove = model.planNextMove(view.getComputerHand());
               view.compMakeMove(bestMove);
              
              
            }
         }
         view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         view.pack();
      }

      @Override
      public void mouseClicked(MouseEvent e)
      {
     

      }

      @Override
      public void mouseEntered(MouseEvent e)
      {
         destinationCard = (CardLabel) e.getComponent();
         if (destinationCard.faceUp == false)
         {
            destinationCard.flip();
         }
      }

      @Override
      public void mouseExited(MouseEvent e)
      {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseDragged(MouseEvent e)
      {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseMoved(MouseEvent e)
      {
         CardLabel card = (CardLabel) e.getComponent();
         if (card.faceUp == false)
         {
            card.flip();
         }
         
      }

   }

   class ButtonListener implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent e)
      {
         model.setCantPlay(true);
         view.setScore(model.getCannotPlays());
         if (model.getCannotPlays() > 10)
         {
            view.showWinner();
         }
      }

   }

}
