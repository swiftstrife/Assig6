/**
 * 
 * @author Keith
 *
 */
class Deck
{

   private static final int NUMBER_OF_CARDS = 56;
   public static final int MAX_CARDS = 6 * NUMBER_OF_CARDS;
   private static Card[] masterPack = new Card[NUMBER_OF_CARDS];
   private static boolean emptyMasterPack = true;
   private Card[] cards;
   private int topCard;
   private int numPacks;

   /**
    * a constructor that populates the arrays and assigns initial values to
    * members.
    * 
    * @param numPacks
    */
   public Deck(int numPacks)
   {
      if (numPacks * NUMBER_OF_CARDS > MAX_CARDS)
      {
         this.numPacks = 1; // default pack is 1 if number of packs exceed
         // MAX_CARDS
      }
      if (emptyMasterPack)
      {
         allocateMasterPack();
      }
      init(numPacks); // make a certain amount of decks using MasterPack as
      // the
      // model
   }

   /**
    * initializes the Deck to 1 pack by default
    */
   public Deck()
   {
      this.numPacks = 1; // if no parameters passed, 1 pack is assumed

      if (emptyMasterPack)
      {
         allocateMasterPack();
      }
      init(numPacks);
   }

   /**
    * initializes the cards array.
    * 
    * @param numPacks
    */
   public void init(int numPacks)
   {

      cards = new Card[numPacks * NUMBER_OF_CARDS];
      for (int i = 0; i < cards.length; i++)
      {
         cards[i] = masterPack[i % NUMBER_OF_CARDS];
         if (topCard != cards.length)
         {
            topCard++;
         }
      }

   }

   private static void allocateMasterPack()
   {
      Card.Suit suit;
      int cardLength = Card.cardNumber.length;
      for (int i = 0; i < masterPack.length; i++)
      {
         suit = Card.Suit.values()[i / cardLength];

         // change suit after every 13th card
         masterPack[i] = new Card(Card.cardNumber[i % cardLength], suit);
      }
      emptyMasterPack = false;
   }

   public Card[] getPack()
   {
      return cards;
   }

   public void shuffle()
   {
      Card temp;
      for (int i = cards.length - 1; i > 0; i--)
      {
         int rand = (int) (Math.random() * i);
         temp = cards[i];
         cards[i] = cards[rand];
         cards[rand] = temp;
      }

   }

   /**
    * 
    * @return
    */
   public Card dealCard()
   {
      if (topCard == 0)
      {
         return null;
      }

      Card theCard = new Card(cards[topCard - 1].getValue(), cards[topCard - 1].getSuit());
      cards[topCard - 1] = null;
      topCard--;

      return theCard;
   }

   public int getTopCard()
   {
      return topCard;
   }

   /**
    * 
    * @return
    */
   public int getNumCards()
   {
      return topCard;
   }

   /**
    * You are looking to remove a specific card from the deck. Put the current
    * top card into its place. Be sure the card you need is actually still in
    * the deck, if not return false.
    * 
    * @param card
    * @return
    */
   public boolean removeCard(Card card)
   {
      for (int i = topCard - 1; i > 0; i--)
      {
         if (cards[i] != null && card != null)
         {
            while (cards[i].equals(card))
            {
               Card temp = dealCard();
               if (!(temp.equals(card)))
               {
                  cards[i] = temp;
               }
            }
         }

      }
      return isNotInDeck(card);
   }

   /**
    * check that a card is no longer in the deck.
    * 
    * @param card
    * @return false if the card exist, otherwise true
    */
   private boolean isNotInDeck(Card card)
   {
      boolean exist = true;
      for (int j = 0; j < topCard; j++)
      {
         if (this.getPack()[j].equals(card))
         {
            exist = false;
         }
      }
      return exist;
   }

   public boolean addCard(Card card)
   {
      // make sure there aren't too many instances of the card.
      if (countOccurences(card) <= numPacks * 4 && topCard < cards.length)
      {
         cards[topCard] = card;
         topCard++;
         return true;
      } else
      {
         return false;

      }
   }

   private int countOccurences(Card card)
   {
      int count = 0;
      for (int j = 0; j < topCard; j++)
      {
         if (this.getPack()[j].equals(card))
         {
            count++;
         }
      }
      return count;
   }

   void sort()
   {
      Card.arraySort(cards, topCard);

   }
}
