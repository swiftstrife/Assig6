import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
		CardLabel sourceCard;
		CardLabel destinationCard;
		
		@Override
		public void mousePressed(MouseEvent e) {
			// e.getComponent().setVisible(false);
			sourceCard = (CardLabel) e.getSource();
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
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(model.getNextCard()!= null && destinationCard.isPlayed()){
/*//				sourceCard.setBounds(20, 0, GUICard.getBackCardIcon()
//						.getIconWidth()*3, GUICard.getBackCardIcon().getIconHeight());
//				destinationCard.getParent().add(sourceCard, 2);
*/				//sourceCard.invalidate();	
				view.addCardToPlayArea(sourceCard, destinationCard);
			}
			view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			model.setNextCard(null);
			view.pack();
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
			destinationCard  = (CardLabel)e.getComponent();

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
