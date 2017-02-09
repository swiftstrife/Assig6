import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 
 */

/**
 * @author Huy, Keith, and Wis
 *
 */
class GUICard
{
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K +
                                                             // joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;

   GUICard()
   {
      if (iconsLoaded == false)
      {
         loadCardIcons();
      }
   }

   /**
    * loads card Icons
    */
   static void loadCardIcons()
   {
      for (int i = 0; i < Card.cardNumber.length; i++)
      {
         for (int j = 0; j < Card.Suit.values().length; j++)
         {
            iconCards[i][j] = new ImageIcon(turnIntIntoCardValue(i)
               + turnIntIntoCardSuit(j));

            System.out.println(iconCards[i][j]);

         }
      }

      iconBack = new ImageIcon("images/BK.gif");

      iconsLoaded = true;
   }

   /**
    * Turn int into a card value
    * @param i
    * @return
    */
   static String turnIntIntoCardValue(int i)
   {
      return "images/" + Card.cardNumber[i];
   }

   /**
    * turns an int into a card suit
    * @param j
    * @return
    */
   static String turnIntIntoCardSuit(int j)
   {
      return Card.Suit.values()[j] + ".gif";
   }

   /**
    * returns the back image of the card.
    * @return
    */
   static public Icon getBackCardIcon()
   {
      return (Icon) iconBack;
   }

   /**
    * Returns the icon of the card.
    * @param card
    * @return
    */
   static public Icon getIcon(Card card)
   {
      return iconCards[valueAsInt(card)][suitAsInt(card)];
   }

   /**
    * returns the cards value as an integer.
    * @param card
    * @return
    */
   private static int valueAsInt(Card card)
   {
      return new String(Card.cardNumber).indexOf(card.getValue());
   }

   /**
    * returns the cards suit as an integer.
    * @param card
    * @return
    */
   private static int suitAsInt(Card card)
   {
      return card.getSuit().ordinal();
   }

}
