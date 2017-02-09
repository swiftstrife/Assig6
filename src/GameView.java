import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener;

/**
 * @author BigMac
 *
 */
public class GameView extends JFrame {
	int NUM_CARDS_PER_HAND = 7;
	int NUM_PLAYERS = 2;
	JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
	JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
	JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
	JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
	JLabel[] results = new JLabel[NUM_PLAYERS];

	Card[] computerWinning = new Card[NUM_CARDS_PER_HAND * NUM_PLAYERS];
	Card[] humanWinning = new Card[NUM_CARDS_PER_HAND * NUM_PLAYERS];
	JLayeredPane[] winningStack = new JLayeredPane[NUM_PLAYERS];

	public JPanel pnlComputerHand = new JPanel();
	public JPanel pnlHumanHand = new JPanel();
	public JPanel pnlPlayArea = new JPanel();
	private MouseListener gameListener;
	JPanel mainPanel = new JPanel();

	// public JLabel lblConsole;

	GameView(String title, int numCardsPerHand, int numPlayers) {
		super(title);
		mainPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(pnlComputerHand);
		mainPanel.add(pnlPlayArea);
		mainPanel.add(pnlHumanHand);

		pnlComputerHand.setBorder(BorderFactory
				.createTitledBorder("Computer Hand"));
		pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
		pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Area"));
		pnlPlayArea.setLayout(new GridLayout(2, 4));
		this.add(mainPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUICard.loadCardIcons();
		this.pack();
	}

	public void addGameListener(MouseListener gameListener) {
		this.gameListener = gameListener;

	}

	public void showWinner() {
		// TODO Auto-generated method stub

	}

	public void showWinnings() {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	// shows the human and computer hand.
	public void showHands(Hand hand) {

		for (int i = 0; i < NUM_CARDS_PER_HAND; i++) {
			System.out.println(i);
			computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
			humanLabels[i] = new JLabel(GUICard.getIcon(hand.inspectCard(i)));
			humanLabels[i].addMouseListener(this.gameListener);
			pnlHumanHand.add(humanLabels[i]);
			pnlComputerHand.add(computerLabels[i]);
		}

		this.pack();
	}

	public void showPlayArea() {
		playedCardLabels[0] = new JLabel(GUICard.getBackCardIcon());
		playedCardLabels[1] = new JLabel(GUICard.getBackCardIcon());

		for (int j = 0; j < NUM_PLAYERS; j++) {
			pnlPlayArea.add(playedCardLabels[j]);
			winningStack[j]  = new JLayeredPane();
			playLabelText[j] = new JLabel("Player" +(j+1), JLabel.CENTER);
			results[j] = new JLabel("", JLabel.RIGHT);
	      pnlPlayArea.add(winningStack[j]);
		}
	}
}
