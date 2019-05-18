package dk.dtu.compute.se.pisd.monopoly.mini.model;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;


/**
 * A property which is a space that can be owned by a player.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Property extends Space {

	private int cost;
	private int rent;
	private Player owner;

	private boolean groupOwned = false;

	public boolean isGroupOwned() {
		return groupOwned;
	}

	private ColorGroup colorGroup;
// Andreas. tiføjet pantsat som kan tilgås ved setteren.

	private boolean isMortgaged = false;
// find brug
	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean mortgaged) {
		isMortgaged = mortgaged;
	}

	/**
	 * @Aurthor Sascha s171281
	 * Returns
	 */

	public boolean getGroupOwned(){
		return this.groupOwned;
	}

	/**
	 * @Aurthor Sascha s171281
	 * Sets
	 * @param bool the new state
	 */

	public void setGroupOwned(boolean bool){
		this.groupOwned = bool;
	}

	public ColorGroup getColorGroup(){
		return colorGroup;
	}

	public void setColorGroup (ColorGroup colorGroup){
		this.colorGroup = colorGroup;
	}


	/**
	 * Returns the cost of this property.
	 *
	 * @return the cost of this property
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost of this property.
	 *
	 * @param cost the new cost of this property
	 */
	public void setCost(int cost) {
		this.cost = cost;
		notifyChange();
	}

	/**
	 * Returns the rent to be payed for this property.
	 *
	 * @return the rent for this property
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * Sets the rent for this property.
	 *
	 * @param rent the new rent for this property
	 */
	public void setRent(int rent) {
		this.rent = rent;
		notifyChange();
	}

	/**
	 * Returns the owner of this property. The value is <code>null</code>,
	 * if the property currently does not have an owner.
	 *
	 * @return the owner of the property or <code>null</code>
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of this property  to the given owner (which can be
	 * <code>null</code>).
	 *
	 * @param player the new owner of the property
	 */
	public void setOwner(Player player) {
		this.owner = player;
		notifyChange();
	}

		public void doAction(GameController controller, Player player) throws PlayerBrokeException {
		if (owner == null) {
			controller.offerToBuy(this, player);
		} else if (!owner.equals(player)) {
			// TODO also check whether the property is mortgaged
			// Andreas
			if (this.isMortgaged()){
				// Skriv at den er mortgaged ogff
		} else {
			// polymorfisk kald: del utility op og kald alle metoderne det samme, som de overwriter herfra
			// kald det eks computeRent.
			// se op på om computeRent kaldes polymorfisk eller der skal tre if'er til for at se
			// hvilket objekt metoden kaldes på/ hvilken type felt spilleren er på.

			player.payMoney(this.computeRent(controller));
			owner.receiveMoney(this.computeRent(controller));

		}
	}
	//Andreas - metode vi overskriver i de tre child-klasser. atm er den overloaded så Soda kan
	// have controller som parameter, så sikr at den kaldes på det rigtige objekt i doAction.
// lige nu udskrives nok kun 0 fordi den kalder denne metode. sikr dig at den kalder underklassernes metode.
	// det kan du teste når vi kommer dertil.

	}

	public int computeRent(GameController controller) {
		return 0;
	}
}

