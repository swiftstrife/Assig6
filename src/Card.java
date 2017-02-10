import java.util.Arrays;

import javax.swing.JFrame;

/**
 * The Card class is responsible for holding data and methods related to the
 * individual cards. It contains instance variables for the value and suit. It
 * also contains an error flag for invalid cards.
 * 
 * @author Keith
 *
 */
class Card implements Comparable<Card>
{
   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * The suit enum is used to track the possible suits of the standard deck of
    * cards
    * 
    */
   public static enum Suit
   {
      C, D, H, S;
   }

   public final static char[] cardNumber =
   { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };

   /**
    * The constructor calls the mutator to initialize the values. It takes two
    * parameters for the value and suit
    * 
    * @param value
    * @param suit
    */
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   /**
    * This is the default constructor meant to handle the creation of a card
    * without any parameters. The default card is an Ace of SPADES.
    */
   public Card()
   {
      set('A', Suit.S);
   }

   /**
    * A mutator that accepts the legal values for card using the isValid()
    * method to check. If a bad value is passed the errorFlag is set to true.
    */
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      } else
      {
         errorFlag = true;
         return false;
      }

   }

   /**
    * isValid checks if the cards value and suit are valid. Suit is unchecked at
    * this time.
    * 
    * @param value
    * @param suit
    * @return
    */
   private boolean isValid(char value, Suit suit)
   {
      for (int i = 0; i < cardNumber.length; i++)
      {
         if (cardNumber[i] == value)
         {
            return true;
         }
      }
      return false;
   }

   /**
    * Checks if a card is equal to this card
    * 
    * @param card
    * @return true of false.
    */
   boolean equals(Card card)
   {
      boolean same;
      if (this.suit == card.getSuit() && this.value == card.getValue())
      {
         same = true;
      } else
      {
         same = false;
      }
      return same;
   }

   /**
    * returns the String version of the card
    */
   public String toString()
   {
      if (errorFlag == false)
      {
         return value + " of " + suit;
      } else
      {
         return "ILLEGAL STATE";
      }
   }

   /**
    * Returns the value of the card
    * 
    * @return value
    */
   public char getValue()
   {
      return value;
   }

   /**
    * returns the suit of the cart
    * 
    * @return suit
    */
   public Suit getSuit()
   {
      return suit;
   }

   /**
    * Returns the error flag.
    * 
    * @return
    */
   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   /**
    * Sorts the incoming array of cards using a bubble sort routine.
    */
   public static void arraySort(Card[] c, int arraySize)
   {
      boolean sorted = false;
      while (sorted == false)
      {
         int swaps = 0;
         for (int i = 0; i < arraySize - 1; i++)
         {
            if (c[i + 1] != null && c[i] != null)
            {
               if (isNotInOrder(c[i], c[i + 1]))
               {
                  Card temp = c[i];
                  c[i] = c[i + 1];
                  c[i + 1] = temp;
                  swaps++;
               }
            }
         }
         if (swaps == 0)
         {
            sorted = true;
         }
      }
   }

   /**
    * Checks that the cards are in order (used in arraySort)
    * 
    * @param a
    * @param b
    * @return returns true if it isn't in order, false otherwise.
    */
   private static boolean isNotInOrder(Card a, Card b)
   {
      int valueA = getCardValueIndex(a.getValue());
      int valueB = getCardValueIndex(b.getValue());
      if (valueA > valueB)
      {
         return true;
      }
      return false;
   }

   /**
    * Returns the index of the card in the value rank array.
    * 
    * @param value
    * @return index
    */
   private static int getCardValueIndex(char value)
   {
      char[] ranks = valueRanks();
      for (int i = 0; i < ranks.length; i++)
      {
         if (ranks[i] == value)
         {
            return i;
         }
      }
      return -1;
   }

   /**
    * The official rank of each value.
    * 
    * @return
    */
   public static char[] valueRanks()
   {
      char[] ranks =
      { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', 'X' };
      return ranks;
   }

   @Override
   public int compareTo(Card card)
   {
      return Integer.compare(getCardValueIndex(this.value), getCardValueIndex(card.getValue()));
   }

}

