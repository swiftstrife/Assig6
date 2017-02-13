import javax.swing.Icon;
import javax.swing.JLabel;

public class CardLabel extends JLabel {

	Card card;
	Icon backIcon;
	Icon frontIcon;
	boolean faceUp;
	
	public CardLabel(Card card) {
		this.card = card;
		if (GUICard.iconsLoaded == false) {
			GUICard.loadCardIcons();
		}
		backIcon = GUICard.getBackCardIcon();
		frontIcon = GUICard.getIcon(card);
		setIcon(backIcon);
	}
	
	public boolean flip(){
		if(faceUp){
			setIcon(backIcon);
			faceUp = false;
		}else{
			setIcon(frontIcon);
			faceUp = true;
		}
		return faceUp;
	}

}
