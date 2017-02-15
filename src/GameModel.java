import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author BigMac
 *
 */
public class GameModel
{
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   int numPacksPerDeck = 1;
   int numJokersPerPack = 0;
   int numUnusedCardsPerPack = 0;
   int cannotPlays = 0;
   int compCannotPlays = 0;
   boolean compCantPlay = true;
   Card[] topCards = new Card[NUM_PLAYERS];

   boolean cantPlay = true;
   Card[] unusedCardsPerPack = null;

   CardGameFramework highCardGame = new CardGameFramework(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);

   public GameModel()
   {

      topCards[0] = new Card();
      topCards[1] = new Card();
      if (highCardGame.deal() == false)
      {
         System.out.print("Error: Cannot deal.");
         System.exit(1);
      }
   }

   public int getNumCardsPerHand()
   {
      return NUM_CARDS_PER_HAND;
   }

   public void setNumCardsPerHand(int numCardsPerHand)
   {
      NUM_CARDS_PER_HAND = numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return NUM_PLAYERS;
   }

   public void setNumPlayers(int numPlayers)
   {
      NUM_PLAYERS = numPlayers;
   }

   public CardGameFramework getHighCardGame()
   {
      return highCardGame;
   }

   public Hand getHumanHand()
   {

      return highCardGame.getHand(0);

   }

   public Hand getComputerHand()
   {
      return highCardGame.getHand(1);
   }

   /**
    * deals cards from human hands
    * @return
    */
   public Card dealCardFromHumanHand()
   {
      Card nextCard = getHumanHand().playCard();
      if (nextCard != null)
      {
         return nextCard;
      } else
      {
         if (highCardGame.deal() != false)
         {
            return dealCardFromHumanHand();
         } else
         {
            return null;
         }

      }
   }

   /**
    * deals card for human Hand
    * @return
    */
   public Card dealCardFromComputerHand()
   {
      Card nextCard = getComputerHand().playCard();
      if (nextCard != null)
      {
         return nextCard;
      } else
      {
         if (highCardGame.deal() != false)
         {
            return dealCardFromComputerHand();
         } else
         {
            return null;
         }

      }
   }

   public boolean isPlayable(Card source, Card destination)
   {
      int comp = Math.abs(GUICard.valueAsInt(source)
         - GUICard.valueAsInt(destination));
      if (cantPlay)
      {
         cantPlay = false;
         return true;
      } else if ((comp > 1 || comp == 0))
      {
         return false;
      } else
      {
         return true;
      }
   }

   public boolean isComputerPlayable(Card source, Card destination)
   {
      int comp = Math.abs(GUICard.valueAsInt(source)
         - GUICard.valueAsInt(destination));
      if (compCantPlay)
      {
         compCantPlay = false;
         return true;
      } else if ((comp > 1 || comp == 0))
      {
         return false;
      } else
      {
         return true;
      }
   }
   public int getCannotPlays()
   {
      return cannotPlays;
   }

   public int getCompCannotPlays()
   {
      return compCannotPlays;
   }

   public void setCantPlay(boolean cantPlay)
   {
      this.cantPlay = cantPlay;
      cannotPlays++;
   }

   public void setCompCantPlay(boolean compCantPlay)
   {
      this.compCantPlay = compCantPlay;
      compCannotPlays++;
   }

   public void setTopCard(CardLabel destinationCard, CardLabel sourceCard)
   {
      boolean match= false;
      System.out.println("destination"+destinationCard.getCard());
      for (int i = 0; i < topCards.length; i++)
      {
         if (destinationCard.getCard().equals(topCards[i]))
         {
            topCards[i] = sourceCard.getCard();
            match = true;
            break;
         }
         System.out.println("The new topCard is"+topCards[i]);
      }
      System.out.println("Top Cards " + Arrays.toString(topCards) + " match is "+match);
      
   }
 public void printTopCards(){
    System.out.println("Print Top Cards " + Arrays.toString(topCards));

  }
}