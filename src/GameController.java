import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController {
	private GameModel model;
	private GameView view;

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		this.view.addGameListener(new GameListener());
		view.showPlayArea();
		this.view.showHands(model.getHumanHand());
		
	}
	class GameListener implements MouseListener , Cloneable{

		
		@Override
		public void mousePressed(MouseEvent e) {
			//e.getComponent().setVisible(false);
			System.out.println("A CARD WAS CLICKED ON!");
			System.out.println(((CardLabel)e.getSource()).getCard().toString());
			((CardLabel)e.getSource()).flip();
			model.determineWinner();
			model.nextTurn();

			view.showWinner();
			view.showWinnings();

			view.refresh();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/**
		 * Determines the winner of a match
		 */
		public void determineWinner() {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		

		
	}
	
}
