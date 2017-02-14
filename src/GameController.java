import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

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

	class GameListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			// e.getComponent().setVisible(false);
			System.out.println("A CARD WAS CLICKED ON!");
			System.out
					.println(((CardLabel) e.getSource()).getCard().toString());
			CardLabel card = ((CardLabel) e.getSource());
			if (card.faceUp == false) {
				card.flip();
			}
			if (card.isPlayed() == false) {
				view.changeCursorImage(card);
				model.setNextCard(card);
				model.isPlayable();
				// view.showWinner();
				view.showWinnings();

				view.refresh();
			} else{
				Component p = e.getComponent().getParent();
				
				e.getComponent().getParent().add(model.nextCard);
			}
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
