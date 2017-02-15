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
   boolean compCantPlay= true;
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

   public int getCannotPlays()
   {
      return cannotPlays;
   }

   public void setCantPlay(boolean cantPlay)
   {
      this.cantPlay = cantPlay;
      cannotPlays++;
   }
   public void setCompCantPlay(boolean compCantPlay)
   {
      this.compCantPlay = compCantPlay;
   }
   public void setTopCard(CardLabel destinationCard, CardLabel sourceCard)
   {
      for (int i = 0; i < topCards.length; i++)
      {
         if (destinationCard.getCard().equals(topCards[i]))
         {
            topCards[i] = sourceCard.getCard();
            break;
         }
      }
      System.out.println("Top Cards " + Arrays.toString(topCards));
   }

   public CardLabel[] planNextMove(Component[] computerHand)
   {
    
      Component[] cardLabels = computerHand;
      for (Component lab : cardLabels)
      {
         if (lab instanceof CardLabel)
         {
            Card card = ((CardLabel) lab).getCard();
            for (int i = 0; i < topCards.length; i++)
            {
               if(compCantPlay){
                  Card temp = topCards[i];
                  topCards[i]=card;
                  setCompCantPlay(false);
                  return new CardLabel[]{(CardLabel) lab,new CardLabel(temp)};
               }
               if(isPlayable(card, topCards[i])){
                  topCards[i]=card;
                  return new CardLabel[]{(CardLabel) lab,new CardLabel(topCards[i])};
               }
            }
        
         }
      }
      System.out.println("is this the prob?");
      return null;
   }
      

}