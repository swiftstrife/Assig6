
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

	public void determineWinner() {
		// TODO Auto-generated method stub
		
	}

	public void nextTurn() {
		// TODO Auto-generated method stub
		
	}
	
	public Hand getHumanHand(){
		highCardGame.deal();
		return highCardGame.getHand(0);
	
	}
        

}