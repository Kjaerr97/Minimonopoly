package dk.dtu.compute.se.pisd.monopoly.mini;

import dk.dtu.compute.se.pisd.monopoly.mini.Database.Database;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.*;
import dk.dtu.compute.se.pisd.monopoly.mini.model.cards.*;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.GameEndedException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.Ferry;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.Soda;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for setting up and running a (Mini-)Monoploy game.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class MiniMonopoly {
	
	/**
	 * Creates the initial static situation of a Monopoly game. Note
	 * that the players are not created here, and the chance cards
	 * are not shuffled here.
	 *
	 * @return the initial game board and (not shuffled) deck of chance cards 
	 */
	public static Game createGame() {

		// Create the initial Game set up (note that, in this simple
		// setup, we use only 11 spaces). Note also that this setup
		// could actually be loaded from a file or database instead
		// of creating it programmatically. This will be discussed
		// later in this course.
		Game game = new Game();
		
		Space go = new Space();
		go.setName("Go");
		game.addSpace(go);
		
		RealEstate p = new RealEstate();
		p.setName("Rødovrevej");
		p.setCost(1200);
		p.setRent(50);
		p.setColorGroup(ColorGroup.lightblue);
		p.setHousePrice(1000);
		game.addSpace(p);

		
		Chance chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);
		
		p = new RealEstate();
		p.setName("Hvidovrevej");
		p.setCost(1200);
		p.setRent(50);
		p.setColorGroup(ColorGroup.lightblue);
		p.setHousePrice(1000);
		game.addSpace(p);
		
		Tax t = new Tax();
		t.setName("Pay tax (10% of all assets)");
		game.addSpace(t);

		Ferry f = new Ferry();
		f.setName("Øresund");
		f.setCost(4000);
		f.setRent(500);
//		f.setColorGroup(ColorGroup.pink);
		game.addSpace(f);

		p = new RealEstate();
		p.setName("Roskildevej");
		p.setCost(2000);
		p.setRent(100);
		p.setColorGroup(ColorGroup.salmon);
		p.setHousePrice(1000);
		game.addSpace(p);
		
		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);
		
		p = new RealEstate();
		p.setName("Valby Langgade");
		p.setCost(2000);
		p.setRent(100);
		p.setColorGroup(ColorGroup.salmon);
		p.setHousePrice(1000);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Allégade");
		p.setCost(2400);
		p.setRent(150);
		p.setColorGroup(ColorGroup.salmon);
		p.setHousePrice(1000);
		game.addSpace(p);
		
		Space prison = new Space();
		prison.setName("Prison");
		game.addSpace(prison);
		
		p = new RealEstate();
		p.setName("Frederiksberg Allé");
		p.setCost(2800);
		p.setRent(200);
		p.setColorGroup(ColorGroup.green);
		p.setHousePrice(2000);
		game.addSpace(p);

		Soda s = new Soda();
		s.setName("Tuborg");
		s.setCost(3000);
		s.setColorGroup(ColorGroup.turquoise);
		game.addSpace(s);
		
		p = new RealEstate();
		p.setName("Bülowsvej");
		p.setCost(2800);
		p.setRent(200);
		p.setColorGroup(ColorGroup.green);
		p.setHousePrice(2000);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Gl. Kongevej");
		p.setCost(3200);
		p.setRent(250);
		p.setColorGroup(ColorGroup.green);
		p.setHousePrice(2000);
		game.addSpace(p);

		f = new Ferry();
		f.setName("D.F.D.S.");
		f.setCost(4000);
		f.setRent(200);
		f.setColorGroup(ColorGroup.pink);
		game.addSpace(f);

		p = new RealEstate();
		p.setName("Bernstorffsvej");
		p.setCost(3600);
		p.setRent(300);
		p.setColorGroup(ColorGroup.grey);
		p.setHousePrice(2000);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Hellerupvej");
		p.setCost(3600);
		p.setRent(300);
		p.setColorGroup(ColorGroup.grey);
		p.setHousePrice(2000);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Strandvejen");
		p.setCost(4000);
		p.setRent(350);
		p.setColorGroup(ColorGroup.grey);
		p.setHousePrice(2000);
		game.addSpace(p);

		Space parkering = new Space();
		parkering.setName("Parkering");
		game.addSpace(parkering);

		p = new RealEstate();
		p.setName("Trianglen");
		p.setCost(4400);
		p.setRent(400);
		p.setColorGroup(ColorGroup.red);
		p.setHousePrice(3000);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Østerbrogade");
		p.setCost(4400);
		p.setCost(400);
		p.setColorGroup(ColorGroup.red);
		p.setHousePrice(3000);
		game.addSpace(p);


		p = new RealEstate();
		p.setName("Grønningen");
		p.setCost(4800);
		p.setRent(450);
		p.setColorGroup(ColorGroup.red);
		p.setHousePrice(3000);
		game.addSpace(p);

		f = new Ferry();
		f.setName("Ø.S.");
		f.setCost(4000);
		f.setRent(500);
		f.setColorGroup(ColorGroup.pink);
		game.addSpace(f);

		p = new RealEstate();
		p.setName("Bredgade");
		p.setCost(5200);
		p.setRent(500);
		p.setColorGroup(ColorGroup.white);
		p.setHousePrice(3000);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Kgs. Nytorv");
		p.setCost(5200);
		p.setRent(500);
		p.setColorGroup(ColorGroup.white);
		p.setHousePrice(3000);
		game.addSpace(p);

		s = new Soda();
		s.setName("Carlsberg");
		s.setCost(3000);
		s.setColorGroup(ColorGroup.turquoise);

		game.addSpace(s);

		p = new RealEstate();
		p.setName("Østergade");
		p.setCost(5600);
		p.setRent(550);
		p.setColorGroup(ColorGroup.white);


		p.setHousePrice(3000);
		game.addSpace(p);

		SpaceToJail toJail = new SpaceToJail();
		toJail.setName("Gå i fængsel");
		game.addSpace(toJail);

		p = new RealEstate();
		p.setName("Amagertorv");
		p.setCost(6000);
		p.setRent(600);
		p.setColorGroup(ColorGroup.yellow);
		p.setHousePrice(4000);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Vimmelskaftet");
		p.setCost(6000);
		p.setRent(600);
		p.setColorGroup(ColorGroup.yellow);
		p.setHousePrice(4000);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Nygade");
		p.setCost(6400);
		p.setRent(650);
		p.setColorGroup(ColorGroup.yellow);
		p.setHousePrice(4000);
		game.addSpace(p);

		f = new Ferry();
		f.setName("Bornholm");
		f.setCost(4000);
		f.setRent(500);
		p.setColorGroup(ColorGroup.pink);
		game.addSpace(f);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Frederiksberggade");
		p.setCost(7000);
		p.setRent(700);
		p.setColorGroup(ColorGroup.purple);
		p.setHousePrice(4000);
		game.addSpace(p);

		t = new Tax();
		t.setName("Pay tax (10% of your assets");
		game.addSpace(t);

		p = new RealEstate();
		p.setName("Rådhuspladsen");
		p.setCost(8000);
		p.setRent(1000);
		p.setColorGroup(ColorGroup.purple);
		p.setHousePrice(4000);
		game.addSpace(p);

//Added more cards to the pile
//@author s180911 Asger
		List<Card> cards = new ArrayList<Card>();

		CardMoveJail jailCard = new CardMoveJail();
		jailCard.setText("You've been caught speeding and been put to jail.");
		cards.add(jailCard);


		CardMove cardmove1 = new CardMove();
		cardmove1.setText("You have been visiting a friend, move to" + game.getSpaces().get(11).getName());
		cardmove1.setTarget(game.getSpaces().get(11));
		cards.add(cardmove1);

		CardMove cardmove2 = new CardMove();
		cardmove2.setText("You have been visiting a friend, move to" + game.getSpaces().get(18).getName());
		cardmove2.setTarget(game.getSpaces().get(18));
		cards.add(cardmove2);

		CardMove cardmove3 = new CardMove();
		cardmove3.setText("You have been visiting a friend, move to" + game.getSpaces().get(21).getName());
		cardmove3.setTarget(game.getSpaces().get(21));
		cards.add(cardmove3);

		CardMove cardmove4 = new CardMove();
		cardmove4.setText("You have been visiting a friend, move to" + game.getSpaces().get(29).getName());
		cardmove4.setTarget(game.getSpaces().get(29));
		cards.add(cardmove4);

		PayTax tax = new PayTax();
		tax.setText("Pay 10% income tax!");
		cards.add(tax);

		CardReceiveMoneyFromBank moneyBankA= new CardReceiveMoneyFromBank();
		moneyBankA.setText(" You receive 300$ from the bank.");
		moneyBankA.setAmount(300);
		cards.add(moneyBankA);

		CardReceiveMoneyFromBank moneyBankB = new CardReceiveMoneyFromBank();
		moneyBankB.setText("You won 500 bucks from your lottery ticket. You receive 100$.");
		moneyBankB.setAmount(500);
		cards.add(moneyBankB);

		CardReceiveMoneyFromBank moneyBankC = new CardReceiveMoneyFromBank();
		moneyBankC.setText("You sold your Bitcoins! You receive 4000$ from the bank!");
		moneyBankC.setAmount(4000);
		cards.add(moneyBankC);

		CardMonopolyLegat monopolyLegat = new CardMonopolyLegat();
		monopolyLegat.setText("You've been chosen for the monopolylegat. If your balance is under 15000$, you will receive 40000$");
		monopolyLegat.setAmount(40000);
		cards.add(monopolyLegat);

		CardReceiveMoneyFromPlayer bDay = new CardReceiveMoneyFromPlayer();
		bDay.setText("Its your birthday, you recieve 500$ from all the other players");
		bDay.setAmount(500);
		cards.add(bDay);

		CardPayMoneyToBank carFix = new CardPayMoneyToBank();
		carFix.setText("Your car broke down. You'll have to pay 3000$ in repairs");
		carFix.setAmount(3000);
		cards.add(carFix);
		
		game.setCardDeck(cards);

		return game;

	}

	/**
	 * This method will be called before the game is started to create
	 * the participating players.
	 */


	/**
	 *
	 * @param game
	 * @author s185034, s185466
	 */
	public static void createPlayers (Game game) {

		Object[] choices = {2,3,4};
		int input = (int) JOptionPane.showInputDialog(null, "How many players do you wish to play",
				"Players", JOptionPane.QUESTION_MESSAGE, null, choices, 2);

		for (int i=1; i <= input; i++) {
			String name = JOptionPane.showInputDialog(null,
					"Please enter your name", "Name", JOptionPane.QUESTION_MESSAGE);
			Player p = new Player();
			p.setName(name);
			p.setCurrentPosition(game.getSpaces().get(0));


			switch (i + 1) {

				case 1:
					p.setColor(Color.BLUE);
					break;
				case 2:
					p.setColor(Color.GREEN);
					break;
				case 3:
					p.setColor(Color.YELLOW);
					break;
				case 4:
					p.setColor(Color.RED);
					break;
				case 5:
					p.setColor(Color.PINK);
					break;
				case 6:
					p.setColor(Color.ORANGE);
					break;
			}

			game.addPlayer(p);

		}

		}


	/**
	 * The main method which creates a game, shuffles the chance
	 * cards, creates players, and then starts the game. Note
	 * that, eventually, the game could be loaded from a database.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
/**
 * @author s180911 Asger, s171281 Sascha, s185034 Andreas,
 */
		String result = JOptionPane.showInputDialog(null,
				"Do you wish to load a game?, 'yes' or 'no'", "Game", JOptionPane.QUESTION_MESSAGE);
		if (result.equals("yes")) {
			int usergameID = Integer.parseInt(JOptionPane.showInputDialog(null, "What game do you want to load", "GameLoader", JOptionPane.QUESTION_MESSAGE));

			Game game = createGame();
			Database gameDAO = new Database();
			gameDAO.loadGame(game, usergameID);

			game.shuffleCardDeck();
			GameController controller = new GameController(game);
			controller.initializeGUI();

		} else {
			Game game = createGame();
			game.shuffleCardDeck();
			createPlayers(game);

			GameController controller = new GameController(game);
			controller.initializeGUI();

			Database gameDAO = new Database();
			gameDAO.saveGame(game);

			try {
				controller.play();
			} catch (GameEndedException e) {
				e.printStackTrace();
			}


		}

	}
}