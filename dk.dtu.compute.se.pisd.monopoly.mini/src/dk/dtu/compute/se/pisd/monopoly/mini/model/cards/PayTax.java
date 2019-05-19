package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;

/**
 * A card that directs the player to pay tax to the bank. The tax amounts
 * 10% of the balance of the player's account.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class PayTax extends Card {

    /**
     * @author Sascha s171281
     *  Made sure that tax concerns all assets
     * @param controller the controller that is in charge of the game
     * @param player the involved player
     * @throws PlayerBrokeException
     */

	@Override
	public void doAction(GameController controller, Player player) throws PlayerBrokeException {

		try {
			controller.paymentToBank(player, player.playerAssets() / 10);
		} finally {

			super.doAction(controller, player);
		}
	}
}
