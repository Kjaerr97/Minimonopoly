package dk.dtu.compute.se.pisd.monopoly.mini.controller;

import dk.dtu.compute.se.pisd.monopoly.mini.Database.Database;
import dk.dtu.compute.se.pisd.monopoly.mini.model.*;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.GameEndedException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
//import dk.dtu.compute.se.pisd.monopoly.mini.view.PlayerPanel;
import dk.dtu.compute.se.pisd.monopoly.mini.view.View;
import gui_main.GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The overall controller of a Monopoly game. It provides access
 * to all basic actions and activities for the game. All other
 * activities of the game, should be implemented by referring
 * to the basic actions and activities in this class.
 * 
 * Note that this controller is far from being finished and many
 * things could be done in a much nicer and cleaner way! But, it
 * shows the general idea of how the model, view (GUI), and the
 * controller could work with each other, and how different parts
 * of the game's activities can be separated from each other, so
 * that different parts can be added and extended independently
 * from each other.
 * 
 * For fully implementing the game, it will probably be necessary
 * to add more of these basic actions in this class.
 * 
 * The <code>doAction()</code> methods of the
 * {@link dk.dtu.compute.se.pisd.monopoly.mini.model.Space} and
 * the {@link dk.dtu.compute.se.pisd.monopoly.mini.model.Card}
 * can be implemented based on the basic actions and activities
 * of this game controller.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class  GameController {

	private Game game;

	private GUI gui;

	private View view;

	private Database database;

	private boolean disposed = false;

	// Andreas - sat fields her så terninger kan tilgås i utilityklassen

	/**
	 * Constructor for a controller of a game.
	 *
	 * @param game the game
	 */
	public GameController(Game game) {
		super();
		this.game = game;

		gui = new GUI();
	}

	/**
	 * This method will initialize the GUI. It should be called after
	 * the players of the game are created. As of now, the initialization
	 * assumes that the spaces of the game fit to the fields of the GUI;
	 * this could eventually be changed, by creating the GUI fields
	 * based on the underlying game's spaces (fields).
	 */
	public void initializeGUI() {
		this.view = new View(game, gui);
	}

	/**
	 * The main method to start the game. The game is started with the
	 * current player of the game; this makes it possible to resume a
	 * game at any point.
	 */
	public void play() throws GameEndedException {
		List<Player> players = game.getPlayers();
		Player c = game.getCurrentPlayer();
		game.getPlayers().get(1).setCurrentPosition(game.getSpaces().get(21));
		game.getPlayers().get(0).setCurrentPosition(game.getSpaces().get(21));




		int current = 0;
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (c.equals(p)) {
				current = i;
			}
		}

		boolean terminated = false;
		while (!terminated) {
			Player player = players.get(current);
			if (!player.isBroke()) {
				try {
					this.makeMove(player);
				} catch (PlayerBrokeException e) {
					// We could react to the player having gone broke
				}
			}

			// Check whether we have a winner
			Player winner = null;
			int countActive = 0;
			for (Player p : players) {
				if (!p.isBroke()) {
					countActive++;
					winner = p;
				}
			}

			if (countActive == 1) {
				gui.showMessage(
						"Player " + winner.getName() +
								" has won with " + winner.getBalance() + "$.");
				throw new GameEndedException();

			} else if (countActive < 1) {
				// This can actually happen in very rare conditions and only
				// if the last player makes a stupid mistake (like buying something
				// in an auction in the same round when the last but one player went
				// bankrupt)
				gui.showMessage(
						"All players are broke.");
				break;

			}

			// TODO offer all players the options to trade etc.
			// Andreas - jeg giver kun currentplayer mulighed for at handle
			// dels fordi reglerne er sådan og dels fordi det ville tage for lang tid
			// hvis hver spiller skulle tage stilling efter hver eneste tur.
			String selecter = gui.getUserSelection("What would you like to do?",
					"Finish turn", "Sell/buy houses",
					"Trade others", "Mortgage properties");
			if (selecter.equals("Finish turn")) {

			} else if (selecter.equals("Sell/buy houses")) {

				this.tradeHouses(player);

			} else if (selecter.equals("Trade others")) {

			} else if (selecter.equals("Mortgage properties")) {


			}


			current = (current + 1) % players.size();
			game.setCurrentPlayer(players.get(current));
			if (current == 0) {
				String selection = gui.getUserSelection(
						"A round is finished. Do you want to continue the game?",
						"yes",
						"no");
				if (selection.equals("no")) {
					terminated = true;
				}else if(selection.equals("yes")){
					play();
				}
			}
		}


		dispose();
	}

	/**
	 * This method implements a activity of a single move of the given player.
	 * It throws a {@link dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException}
	 * if the player goes broke in this move. Note that this is still a very
	 * basic implementation of the move of a player; many aspects are still
	 * missing.
	 *
	 * @param player the player making the move
	 * @throws PlayerBrokeException if the player goes broke during the move
	 */
	// Andreas - ændret så terningerne kan tilgås i Soda-klassen og muligt at købe sig ud af fængsel
	public void makeMove(Player player) throws PlayerBrokeException {

		boolean castDouble;
		int doublesCount = 0;
		do {
			// her skal if(isprison evt først??)
			// og her skal die1 og die2 gemmes? hvorfor gør de ikke det. lige nu er de 0 så rent bliver 0 i Soda
			// det er fordi de kun ændres i denne metode.
			int die1 = (int) (1 + 6.0 * Math.random());
			int die2 = (int) (1 + 6.0 * Math.random());


			castDouble = (die1 == die2);
			gui.setDice(die1, die2);
			if (player.isInPrison()) {
				String selection = gui.getUserSelection("Would you like to pay 1000$ to get out of prison?",
						"yes", "no");
				if (selection.equals("yes")) {
					player.payMoney(1000);
					player.setInPrison(false);
				}


			}


			if (player.isInPrison() && castDouble) {
				player.setInPrison(false);
				gui.showMessage("Player " + player.getName() + " leaves prison now since he cast a double!");
			} else if (player.isInPrison()) {
				gui.showMessage("Player " + player.getName() + " stays in prison since he did not cast a double!");
			}
			if (castDouble) {
				doublesCount++;
				if (doublesCount > 2) {
					gui.showMessage("Player " + player.getName() + " has cast the third double and goes to jail!");
					gotoJail(player);
					return;
				}
			}
			if (!player.isInPrison()) {
				// make the actual move by computing the new position and then
				// executing the action moving the player to that space
				int pos = player.getCurrentPosition().getIndex();
				List<Space> spaces = game.getSpaces();
				int newPos = (pos + die1 + die2) % spaces.size();
				Space space = spaces.get(newPos);
				moveToSpace(player, space);
				if (castDouble) {
					gui.showMessage("Player " + player.getName() + " cast a double and makes another move.");
				}
			}
		} while (castDouble);
		//Virker sgu nok, men der skal lige styr på vores database først.
		
		//database.saveGame(game);
	}
	

	/**
	 * This method implements the activity of moving the player to the new position,
	 * including all actions associated with moving the player to the new position.
	 *
	 * @param player the moved player
	 * @param space  the space to which the player moves
	 * @throws PlayerBrokeException when the player goes broke doing the action on that space
	 */
	public void moveToSpace(Player player, Space space) throws PlayerBrokeException {
		int posOld = player.getCurrentPosition().getIndex();
		player.setCurrentPosition(space);

		if (posOld > player.getCurrentPosition().getIndex()) {

			gui.showMessage("Player " + player.getName() + " receives 4000$ for passing Go!");
			this.paymentFromBank(player, 4000);
		}
		gui.showMessage("Player " + player.getName() + " arrives at " + space.getIndex() + ": " + space.getName() + ".");

		// Execute the action associated with the respective space. Note
		// that this is delegated to the field, which implements this action
		space.doAction(this, player);
		// vi kan evt skrive hvad og hvem han betaler til hvis tid.
		if (space instanceof Property && ((Property) space).getOwner() != game.getCurrentPlayer() &&
				!((Property) space).isMortgaged()) {
			gui.showMessage("player " + game.getCurrentPlayer().getName() + " pays "
					+ ((Property) space).computeRent(this) + "$ in rent to " + ((Property) space).getOwner().getName());
		}
	}

	/**
	 * The method implements the action of a player going directly to jail.
	 *
	 * @param player the player going to jail
	 */
	public void gotoJail(Player player) {

		player.setCurrentPosition(game.getSpaces().get(10));
		player.setInPrison(true);
	}

	/**
	 * The method implementing the activity of taking a chance card.
	 *
	 * @param player the player taking a chance card
	 * @throws PlayerBrokeException if the player goes broke by this activity
	 */
	public void takeChanceCard(Player player) throws PlayerBrokeException {
		Card card = game.drawCardFromDeck();
		gui.displayChanceCard(card.getText());
		gui.showMessage("Player " + player.getName() + " draws a chance card.");

		try {
			card.doAction(this, player);
		} finally {
			gui.displayChanceCard("done");
		}
	}

	/**
	 * This method implements the action returning a drawn card or a card keep with
	 * the player for some time back to the bottom of the card deck.
	 *
	 * @param card returned card
	 */
	public void returnChanceCardToDeck(Card card) {
		game.returnCardToDeck(card);
	}

	/**
	 * This method implements the activity where a player can obtain
	 * cash by selling houses back to the bank, by mortgaging own properties,
	 * or by selling properties to other players. This method is called, whenever
	 * the player does not have enough cash available for an action. If
	 * the player does not manage to free at least the given amount of money,
	 * the player will be broke; this is to help the player make the right
	 * choices for freeing enough money.
	 *
	 * @param player the player
	 * @param amount the amount the player should have available after the act
	 */

	public void obtainCash(Player player, int amount) {
		// TODO implement
		while (player.getBalance() < amount) {
			String selection = gui.getUserSelection("You must free at least " + amount +
							"$ to move on with your move. Would you like to " +
							" sell houses, mortgage properties or sell properties?",
					" Sell houses",
					"Mortgage properties", "Sell properties", "None of above");

			if (selection.equals("Sell houses")) {
				this.tradeHouses(player);



			} else if (selection.equals("Mortgage properties")) {

				String propertySelection = gui.getUserString("Choose which property to mortgage");
				// dette er et string object og skal convertes til at propertyobject?
			//	for (int i = 0; i < 40; i++)
				//	if (game.getSpaces().get(i).getName().equals(propertySelection)) {
					//}
// giv ham grundene i en dropdown og lad ham vælge den rigtige grund derfra. se slide og brug en toString.
				// fordi de objekter vi giver krypteres og ikke kan læses. derfra gives det rigtige property-objekt
				// tilbage.



				if (player.getOwnedProperties().contains(propertySelection)) {// og den ikke er mortgaged
					gui.showMessage("You mortgage " + propertySelection + " and receive ?");
					player.getOwnedProperties();


					//.setMortgaged(true);
					// player.paymentFromBank(property.getprice/2
				}
			} else if (selection.equals("Sell properties")) {

			} else {
				return;

			}

		}
	}

	
	/**
	 * This method implements the activity of offering a player to buy
	 * a property. This is typically triggered by a player arriving on
	 * an property that is not sold yet. If the player chooses not to
	 * buy, the property will be set for auction.
	 * 
	 * @param property the property to be sold
	 * @param player the player the property is offered to
	 * @throws PlayerBrokeException when the player chooses to buy but could not afford it
	 */
	public void offerToBuy(Property property, Player player) throws PlayerBrokeException {
		String choice = gui.getUserSelection(
				"Player " + player.getName() +
						": Do you want to buy " + property.getName() +
						" for " + property.getCost() + "$?",
				"yes",
				"no");
// Andreas. added mulighed for at obtaine cash hvis man vælger ja men alligevel ikke har råd. i så
		// fald skal der jo ikke komme en playbrokeException hvis man kan sælge andre ting.
		if (choice.equals("yes") && property.getCost() > player.getBalance()) {
			this.obtainCash(player, property.getCost());
		}
		if (choice.equals("yes")) {
			try {
				paymentToBank(player, property.getCost());
			} catch (PlayerBrokeException e) {
				// if the payment fails due to the player being broke,
				// an auction (among the other players is started
				auction(property);
				// then the current move is aborted by casting the
				// PlayerBrokeException again
				throw e;
			}
			player.addOwnedProperty(property);
			property.setOwner(player);

		} else {

			// In case the player does not buy the property,
			// an auction is started
			auction(property);
		}
	}

	
	/**
	 * This method implements a payment activity to another player,
	 * which involves the player to obtain some cash on the way, in case he does
	 * not have enough cash available to pay right away. If he cannot free
	 * enough money in the process, the player will go bankrupt.
	 * 
	 * @param payer the player making the payment
	 * @param amount the payed amount
	 * @param receiver the beneficiary of the payment
	 * @throws PlayerBrokeException when the payer goes broke by this payment
	 */
	public void payment(Player payer, int amount, Player receiver) throws PlayerBrokeException, GameEndedException {
		if (payer.getBalance() < amount) {
			obtainCash(payer, amount);
			if (payer.getBalance() < amount) {
				playerBrokeTo(payer,receiver);
				throw new PlayerBrokeException(payer);
				}
				if (amount >= 1000000000){

					throw new GameEndedException();

				}

		}


		gui.showMessage("Player " + payer.getName() + " pays " +  amount + "$ to player " + receiver.getName() + ".");
		payer.payMoney(amount);
		receiver.receiveMoney(amount);
	}
	
	/**
	 * This method implements the action of a player receiving money from
	 * the bank.
	 * 
	 * @param player the player receiving the money
	 * @param amount the amount
	 */
	public void paymentFromBank(Player player, int amount) {
		player.receiveMoney(amount);
	}

	/**
	 * This method implements the activity of a player making a payment to
	 * the bank. Note that this might involve the player to obtain some
	 * cash; in case he cannot free enough cash, he will go bankrupt
	 * to the bank. 
	 * 
	 * @param player the player making the payment
	 * @param amount the amount
	 * @throws PlayerBrokeException when the player goes broke by the payment
	 */
	public void paymentToBank(Player player, int amount) throws PlayerBrokeException{
		if (amount > player.getBalance()) {
			obtainCash(player, amount);
			if (amount > player.getBalance()) {
				playerBrokeToBank(player);
				throw new PlayerBrokeException(player);
			}
			
		}
		gui.showMessage("Player " + player.getName() + " pays " +  amount + "$ to the bank.");
		player.payMoney(amount);
	}
	
	/**
	 * This method implements the activity of auctioning a property.
	 *
     * @param property the property which is for auction
     * @GruppeF made this method (class assignment 2)
     */
	public Player auction(Property property) {
		// Creates new ArrayList called bidders.

		List<Player> bidders = new ArrayList<Player>();
		gui.showMessage("Now, there will be an auction of " + property.getName() + ".");
		for (Player player : game.getPlayers()) {
			if (!player.isBroke() && player != game.getCurrentPlayer()) {
				String selection = gui.getUserSelection(
						"Would you " + player.getName() + " participate in the auction?",
						"yes",
						"no");
				if (selection.equals("yes")) {
					bidders.add(player);
				}
			}
		}

		Player bidder = null;
		int currentBid = 999;
		while (bidders.size() > 1) {
			int minBid = currentBid + 1;

			bidder = bidders.get(0);
			// de får lov at byde

			Player player = bidders.remove(0);
			int newBid = gui.getUserInteger("Place bid here. It must be " + minBid + " or above",
					1,
					player.getBalance() + 0);
			if (newBid >= minBid) {
				currentBid = newBid;

				bidders.add(player);

				gui.showMessage(player.getName() + " has the new highest bid of " + currentBid);
			} else {
				gui.showMessage("Your bid wasn't enough, you're removed from the auction.");
			}
		}
		try {
			if (bidders.size() == 1) {
				Player winner = bidders.get(0);
				paymentToBank(winner, currentBid);
				winner.addOwnedProperty(property);
				property.setOwner(winner);

			} else {
				gui.showMessage("The property isn't sold by auction");
			}

		} catch (PlayerBrokeException e) {
			gui.showMessage("PLayer is broke " + e.getMessage());
		}

		return bidder; //Tjek op på dette.
	}


	/**
	 * Action handling the situation when one player is broke to another
	 * player. All money and properties are transferred to the other player.
	 *  
	 * @param brokePlayer the broke player
	 * @param benificiary the player who receives the money and assets
	 */
	public void playerBrokeTo(Player brokePlayer, Player benificiary) {
		int amount = brokePlayer.getBalance();
		benificiary.receiveMoney(amount);
		brokePlayer.setBalance(0);
		brokePlayer.setBroke(true);

		// TODO We assume here, that the broke player has already sold all his houses! But, if
		// not, we could make sure at this point that all houses are removed from
		// properties (properties with houses on are not supposed to be transferred, neither
		// in a trade between players, nor when  player goes broke to another player)
		for (Property property: brokePlayer.getOwnedProperties()) {
			property.setOwner(benificiary);
			benificiary.addOwnedProperty(property);
		}	
		brokePlayer.removeAllProperties();
		
		while (!brokePlayer.getOwnedCards().isEmpty()) {
			game.returnCardToDeck(brokePlayer.getOwnedCards().get(0));
		}
		
		gui.showMessage("Player " + brokePlayer.getName() + "went broke and transfered all"
				+ "assets to " + benificiary.getName());
	}
	
	/**
	 * Action handling the situation when a player is broke to the bank.
	 * 
	 * @param player the broke player
	 */
	public void playerBrokeToBank(Player player) {

		player.setBalance(0);
		player.setBroke(true);
		
		// TODO we also need to remove the houses and the mortgage from the properties 
// skulle gerne være gjort her. Andreas
		for (Property property: player.getOwnedProperties()) {
			property.setOwner(null);
			if(property.isMortgaged()) {
				property.setMortgaged(false);
			}
			if(property instanceof RealEstate){
				((RealEstate) property).setHouses(0);
			}
		}
		player.removeAllProperties();
		
		gui.showMessage("Player " + player.getName() + " went broke");
		
		while (!player.getOwnedCards().isEmpty()) {
			game.returnCardToDeck(player.getOwnedCards().get(0));
		}
	}
	
	/**
	 * Method for disposing of this controller and cleaning up its resources.
	 */
	public void dispose() {
		if (!disposed && view != null) {
			disposed = true;
			if (view != null) {
				view.dispose();
				view = null;
			}
			// TODO we should also dispose of the GUI here. But this works only
			//      for my private version of the GUI and not for the GUI currently
			//      deployed via Maven (or other official versions);
		}
	}
	public void tradeHouses(Player player){
		// hvilke huse osv. kan det gøres med mere end et hus ad gangen
		// vi kan stadig bygge uneven antal huse. kom evt tilbage hertil
		ArrayList<RealEstate> options = new ArrayList<>();
		for(Property property : player.getOwnedProperties()){

			if(property instanceof RealEstate && property.getGroupOwned()){
				options.add((RealEstate)property);
			}
		}
		Object[] option = options.toArray();
		if(option.length == 0){
			gui.showMessage("You don't own any groups");
		} else {

			Object result = JOptionPane.showInputDialog(JOptionPane.showInputDialog(null,
					"Choose a property to buy/sell houses on",
					"sell/buy", JOptionPane.QUESTION_MESSAGE, null, option, 2));

			String select = gui.getUserSelection("Would you like to buy or sell a house?",
					"Buy", "Sell");
			if(select.equals("Buy")){
				for(Property property : player.getOwnedProperties())
					if(player.getOwnedProperties().equals(result)&& property.getGroupOwned()) {
						if(((RealEstate)property).getHouses() < 5){
							player.payMoney(((RealEstate) property).getHousePrice());
							((RealEstate) property).setHouses(((RealEstate) property).getHouses() +1);


						}
					}

			}
		}
	}
	public void mortgagePropeties(){

	}
	public void groupOwned(Property property, Player player) {

		int ownedColour = 0;
		for (Property property1 : player.getOwnedProperties()){
			if (property.getColorGroup() == property.getColorGroup()) {
				ownedColour++;
			}
		}
		if (ownedColour == property.getColorGroup().getGroupID()) {
			for (Property property1 : player.getOwnedProperties()) {
				if (property.getColorGroup() == property.getColorGroup()) {
					property.setGroupOwned(true);
				}
			}
		}
	}

	public Game getGame() {
		return game;
	}
}
