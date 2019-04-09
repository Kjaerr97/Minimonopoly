package dk.dtu.compute.se.pisd.monopoly.mini.view;

import dk.dtu.compute.se.pisd.designpatterns.Observer;
import dk.dtu.compute.se.pisd.designpatterns.Subject;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import gui_fields.*;
import gui_fields.GUI_Car.Pattern;
import gui_fields.GUI_Car.Type;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a view on the Monopoly game based
 * on the original Matador GUI; it serves as a kind of
 * adapter to the Matador GUI. This class realizes the
 * MVC-principle on top of the Matador GUI. In particular,
 * the view implements an observer of the model in the
 * sense of the MVC-principle, which updates the GUI when
 * the state of the game (model) changes.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class View implements Observer {

	private Game game;
	private GUI gui;
	
	private Map<Player,GUI_Player> player2GuiPlayer = new HashMap<Player,GUI_Player>();
	private Map<Player,Integer> player2position = new HashMap<Player,Integer>();
	private Map<Space,GUI_Field> space2GuiField = new HashMap<Space,GUI_Field>();

	//private Map<Player,PlayerPanel> player2PlayerPanel = new HashMap<Player,PlayerPanel>();

	private boolean disposed = false;
	
	/**
	 * Constructor for the view of a game based on a game and an already
	 * running Matador GUI.
	 * 
	 * @param game the game
	 * @param gui the GUI
	 */
	public View(Game game, GUI gui/*PlayerPanel playerpanel*/) {
		this.game = game;
		this.gui = gui;
//		this.playerpanel = playerpanel;

/*		for (Player player : game.getPlayers()){
			PlayerPanel playerPanel = new PlayerPanel(game, player);
			player2PlayerPanel.put(player, playerPanel);
		}
*/
		GUI_Field[] guiFields = gui.getFields();
		
		int i = 0;
		for (Space space: game.getSpaces()) {
			// TODO, here we assume that the games fields fit to the GUI's fields;
			// the GUI fields should actually be created according to the game's
			// fields
			space2GuiField.put(space, guiFields[i++]);
			if (space instanceof Property){
				space.attach(this);

				updateProperty((Property) space);
			}
			// TODO we should also register with the properties as observer; but
			// the current version does not update anything for the spaces, so we do not
			// register the view as an observer for now
		}

		// create the players in the GUI
		for (Player player: game.getPlayers()) {
			GUI_Car car = new GUI_Car(player.getColor(), Color.black, Type.CAR, Pattern.FILL);
			GUI_Player guiPlayer = new GUI_Player(player.getName(), player.getBalance(), car);
			player2GuiPlayer.put(player, guiPlayer);
			gui.addPlayer(guiPlayer);
			// player2position.put(player, 0);

//			PlayerPanel playerPanel = new PlayerPanel(game, player);

			// register this view with the player as an observer, in order to update the
			// player's state in the GUI
			player.attach(this);

			updatePlayer(player);
		}
	}
	
	@Override
	public void update(Subject subject) {
		if (!disposed) {
			if (subject instanceof Player) {
				updatePlayer((Player) subject);
			}
			else if (subject instanceof Property){
				updateProperty((Property) subject);
			}

			
			// TODO update other subjects in the GUI
			//      in particular properties (sold, houses, ...)
			
		}
	}
	private void updateProperty(Property property){

		GUI_Field guiProperty = this.space2GuiField.get(property);
		//Er GUI_Ownable det samme som owner? Eller hvad er det. ER det bare et felt som man kan eje?
		if (guiProperty instanceof GUI_Ownable){
			Player owner = property.getOwner();
			if (owner != null){
				((GUI_Ownable) guiProperty).setBorder(owner.getColor());
			} else {
				((GUI_Ownable) guiProperty).setBorder(null);
			}

		if (guiProperty instanceof GUI_Street&& property instanceof RealEstate){
			((GUI_Street) guiProperty).setHouses(((RealEstate) property).getHouses());
		}

		}

	}
	/**
	 * This method updates a player's state in the GUI. Right now, this
	 * concerns the players position and balance only. But, this should
	 * also include other information (being i prison, available cards,
	 * ...)
	 * 
	 * @param player the player who's state is to be updated
	 */
	private void updatePlayer(Player player) {
		GUI_Player guiPlayer = this.player2GuiPlayer.get(player);
		if (guiPlayer != null) {
			guiPlayer.setBalance(player.getBalance());

			GUI_Field[] guiFields = gui.getFields();
			Integer oldPosition = player2position.get(player);
			if (oldPosition != null && oldPosition < guiFields.length) {
				guiFields[oldPosition].setCar(guiPlayer, false);
			}
			int pos = player.getCurrentPosition().getIndex();
			if (pos < guiFields.length) {
				player2position.put(player,pos);
				guiFields[pos].setCar(guiPlayer, true);
			}

			String name = player.getName();
			if (player.isBroke()) {
			} else if (player.isInPrison()) {
				guiPlayer.setName(player.getName() + " (in prison)");
			}
			if (!name.equals(guiPlayer.getName())) {
				guiPlayer.setName(name);
			}
		}
	}
	
	public void dispose() {
		if (!disposed) {
			disposed = true;
			for (Player player: game.getPlayers()) {
				// unregister from the player as observer
				player.detach(this);
			}
		}
	}

}
