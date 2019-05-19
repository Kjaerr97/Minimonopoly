package dk.dtu.compute.se.pisd.monopoly.mini.model;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;

/**
 * Represents a space, where the player has to pay tax.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Tax extends Space {

	/**
	 * @author Sascha
	 * Made sure that tax concerns all assets
	 * @param controller the controller in charge of the game
	 * @param player the involved player
	 * @throws PlayerBrokeException
	 */

	@Override
	public void doAction(GameController controller, Player player) throws PlayerBrokeException {
		controller.paymentToBank(player, player.playerAssets() / 10);
	}

}
