public class Phase1 {
	public final static int NUMBER_OF_CARDS = 52;
	public final static int NUMBER_OF_PLAYERS = 2;

	public static void main(String[] args) {

		GameView view = new GameView("Card Game", NUMBER_OF_CARDS,
				NUMBER_OF_PLAYERS);
		GameModel model = new GameModel();
		
		GameController controller = new GameController(model, view);
		
		view.setVisible(true);

	}

}
