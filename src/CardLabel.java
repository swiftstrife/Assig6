import javax.swing.Icon;
import javax.swing.JLabel;

public class CardLabel extends JLabel
{

   Card card;
   Icon backIcon;
   Icon frontIcon;
   boolean faceUp;
   boolean played;

   public CardLabel()
   {
      this(new Card());
   }

   public CardLabel(Card card)
   {
      this.card = card;
      if (GUICard.iconsLoaded == false)
      {
         GUICard.loadCardIcons();
      }
      backIcon = GUICard.getBackCardIcon();
      frontIcon = GUICard.getIcon(card);
      setIcon(backIcon);
   }

   public boolean flip()
   {
      if (faceUp)
      {
         setIcon(backIcon);
         faceUp = false;
      } else
   if(!faceUp)
      {
         setIcon(frontIcon);
         faceUp = true;
      }
      return faceUp;
   }

   public Card getCard()
   {
      return card;
   }

   public boolean isPlayed()
   {
      return played;
   }

   public void setPlayed(boolean played)
   {
      this.played = played;
   }

   public boolean isPlayable(CardLabel destination)
   {
      int comp = Math.abs(GUICard.valueAsInt(this.getCard())
         - GUICard.valueAsInt(destination.getCard()));
      if ((comp > 1 || comp == 0))
      {
         return false;
      } else
      {
         return true;

      }
   }
}