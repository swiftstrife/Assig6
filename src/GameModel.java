
/**
 * @author BigMac
 *
 */
public class GameModel 
{	
	static int NUM_CARDS_PER_HAND = 7;
	static int NUM_PLAYERS = 2;
	CardLabel nextCard;

	int numPacksPerDeck = 1;
    int numJokersPerPack = 0;
    int numUnusedCardsPerPack = 0;
    Card[] unusedCardsPerPack = null;
	
    CardGameFramework highCardGame = new CardGameFramework(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, 
    									unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);

    public GameModel()
    {

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


	
	public Hand getHumanHand(){
		highCardGame.deal();
		return highCardGame.getHand(0);
	
	}
	public void setNextCard(CardLabel nextCard) {
		this.nextCard = nextCard;
	}
	public CardLabel getNextCard() {
		return nextCard;
	}

	public boolean isPlayable(Card source,Card destination) {
		int comp = Math.abs(GUICard.valueAsInt(source)-GUICard.valueAsInt(destination));
		if(comp>1 ||comp == 0){
			return false;
		}else{
			return true;
		}
	}
        

}