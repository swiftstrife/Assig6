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
	int[] stackCount = {0,0};
	private MouseListener gameListener;
	JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
	JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
	CardLabel[] playedCardLabels = new CardLabel[NUM_PLAYERS];
	JLabel[] playLabelText = new JLabel[NUM_PLAYERS];
	JLabel[] results = new JLabel[NUM_PLAYERS];
	JLayeredPane[] playStack = new JLayeredPane[NUM_PLAYERS];
	public JPanel pnlComputerHand = new JPanel();
	public JPanel pnlHumanHand = new JPanel();
	public JPanel pnlPlayArea = new JPanel();
	public JPanel pnlTimer = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel timerPanel = new JPanel();

	// public JLabel lblConsole;

	GameView(String title, int numCardsPerHand, int numPlayers) {
		super(title);
		mainPanel.setLayout(new GridLayout(3, 1));
		timerPanel.setLayout(new GridLayout(1, 1));

		mainPanel.add(pnlComputerHand);
		mainPanel.add(pnlPlayArea);
		mainPanel.add(pnlHumanHand);
		timerPanel.add(pnlTimer);
		setSize(475, 500);
		pnlComputerHand.setBorder(BorderFactory
				.createTitledBorder("Computer Hand"));
		pnlHumanHand.setBorder(BorderFactory.createTitledBorder("Your Hand"));
		pnlHumanHand.setLayout(new BorderLayout());
		pnlPlayArea.setBorder(BorderFactory.createTitledBorder("Playing Area"));
		pnlTimer.setBorder(BorderFactory.createTitledBorder("Clock"));
		pnlPlayArea.setLayout(new GridLayout(1, 2));
		pnlTimer.setLayout(new GridLayout(3, 1));
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(timerPanel, BorderLayout.EAST);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUICard.loadCardIcons();

		// Add timer
		Clock insertClock = new Clock();
		pnlTimer.add(insertClock.timeText);
		pnlTimer.add(insertClock.startStopButton);

		insertClock.timeText.setFont(new Font("Calibri", Font.CENTER_BASELINE,
				40));
	}

	public void addGameListener(MouseListener gameListener) {
		this.gameListener = gameListener;

	}

	public void showWinner() {
		// TODO at the end of the game show the winner

	}

	public void showWinnings() {
		// TODO Show winning stacks?

	}

	public void refresh() {
		pack();
	}

	/**
	 * Show the hands of the player (currently setup to just add a computer
	 * player and human)
	 * 
	 * @param hand
	 */
	public void showHands(Hand hand) {
		// TODO enable multiple hands????
		JLayeredPane player1 = new JLayeredPane();

		for (int i = 0; i < NUM_CARDS_PER_HAND; i++) {
			System.out.println(i);
			computerLabels[i] = new JLabel(GUICard.getBackCardIcon());
			humanLabels[i] = new CardLabel(hand.inspectCard(i));
			humanLabels[i].addMouseListener(this.gameListener);
			humanLabels[i].setBounds(((getWidth() - i
					* (getWidth() / NUM_CARDS_PER_HAND))), 0, GUICard
					.getBackCardIcon().getIconWidth(), GUICard
					.getBackCardIcon().getIconHeight());
			humanLabels[i].setBackground(Color.white);
			player1.add(humanLabels[i], -i);
			pnlComputerHand.add(computerLabels[i]);
		}

		// cursor test

		// setCursor(new Cursor(ICONIFIED));
		pnlHumanHand.add(player1);
		this.pack();
	}

	/**
	 * shows play area. (The setup is currently based on the assignment 5 phase
	 * 3.)
	 */
	public void showPlayArea() {
		// TODO update for multiple players.

		for (int j = 0; j < NUM_PLAYERS; j++) {
			playedCardLabels[j] = new CardLabel();
			playedCardLabels[j].setPlayed(true);
			playedCardLabels[j].addMouseListener(gameListener);
			playStack[j] = new JLayeredPane();
			playedCardLabels[j].setBounds(10, 0, GUICard.getBackCardIcon()
					.getIconWidth(), GUICard.getBackCardIcon()
					.getIconHeight());
			playStack[j].add(playedCardLabels[j], 0);
			pnlPlayArea.add(playStack[j]);
		}
	}

	public void addCardToPlayArea(CardLabel sourceCard,
			CardLabel destinationCard) {
		for (int i = 0; i < playStack.length; i++) {
			JLayeredPane jlp = playStack[i];
			if (destinationCard.getParent() == jlp) {
				stackCount[i]++;
				jlp.add(sourceCard, new Integer(i),0-stackCount[i]);
				sourceCard.setBounds(stackCount[i], 0, GUICard.getBackCardIcon()
						.getIconWidth() * 3, GUICard.getBackCardIcon()
						.getIconHeight());

			}
		}
	}

	public void changeCursorImage(CardLabel card) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = ((ImageIcon) card.getIcon()).getImage();
		Cursor c = toolkit.createCustomCursor(image, new Point(getX(), getY()),
				"img");
		setCursor(c);

	}

}
