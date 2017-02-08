/**
 * The hand class is a class that represents the cards held by a single player.
 * 
 * @author Keith
 *
 */
class Hand
{
   public static final int MAX_CARDS = 50;
   // TODO: add some comments;
   private Card[] myCards;
   private int numCards;

   /**
    * The default constructor simply initializes the card array and numCards
    * variable.
    */
   public Hand()
   {
      resetHand();
   }

   /**
    * Removes all cards from the hand.
    */
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      this.numCards = 0;
   }

   /**
    * Adds a cloned card to the next available position in the myCards array.
    * 
    * @param card
    * @return a boolean to show that the card has been taken.
    */
   public boolean takeCard(Card card)
   {
      if (numCards < MAX_CARDS)
      {
         Card newCard = new Card(card.getValue(), card.getSuit());
         myCards[numCards] = newCard;
         numCards++;
         return true;
      } else
      {
         return false;
      }
   }

   /**
    * returns and removes the card in the top occupied position of the array.
    * 
    * @return playedCard
    */
   public Card playCard()
   {
      if (numCards > 0)
      {
         Card playedCard = myCards[numCards - 1];
         myCards[numCards - 1] = null; // set played card to null
         numCards--;
         return playedCard; // return the card on top of hand
      }

      return null;
   }

   /**
    * returns the entire hand as a String.
    */
   public String toString() // returns entire hand as a string
   {
      String currentHand = " | ";

      for (int i = 0; i < numCards; i++)
      {
         currentHand += myCards[i] + " | ";
         // organize cards by rows of 10
         if (i % 10 == 9 && i != numCards - 1)
         {
            currentHand += "\n | ";
         }
      }
      return currentHand;
   }

   /**
    * Returns the number of cards in the hand
    * 
    * @return numCards
    */
   public int getNumCards() // accessor for numCards
   {
      return numCards;
   }

   /**
    * Accessor for an individual card. Returns a bad card if index is not valid.
    * 
    * @param k
    * @return card
    */
   public Card inspectCard(int k)
   {
      if (k >= 0 && k < getNumCards())
      {
         return myCards[k];
      }
      return new Card('0', Card.Suit.S);
   }

   /**
    * Sorts the hand by calling the arraySort() method in the card class.
    */
   public void sort()
   {
      Card.arraySort(myCards, getNumCards());
   }

   public Card playCard(int cardIndex)
   {
      // TODO Auto-generated method stub
      return null;
   }

}
