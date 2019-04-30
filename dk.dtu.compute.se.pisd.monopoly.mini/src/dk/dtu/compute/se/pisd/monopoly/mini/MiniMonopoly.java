package dk.dtu.compute.se.pisd.monopoly.mini;

import dk.dtu.compute.se.pisd.monopoly.mini.Database.Database;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.*;
import dk.dtu.compute.se.pisd.monopoly.mini.model.cards.CardMove;
import dk.dtu.compute.se.pisd.monopoly.mini.model.cards.CardReceiveMoneyFromBank;
import dk.dtu.compute.se.pisd.monopoly.mini.model.cards.PayTax;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.GameEndedException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.Utility;
import gui_main.GUI;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		Property p = new RealEstate();
		p.setName("Rødovrevej");
		p.setCost(1200);
		p.setRent(50);
		p.setColorGroup(ColorGroup.lightblue);
		game.addSpace(p);
		
		Chance chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);
		
		p = new RealEstate();
		p.setName("Hvidovrevej");
		p.setCost(1200);
		p.setRent(50);
		p.setColorGroup(ColorGroup.lightblue);
		game.addSpace(p);
		
		Tax t = new Tax();
		t.setName("Pay tax (10% on Cash)");
		game.addSpace(t);

		Utility s = new Utility();
		s.setName("Øresund");
		s.setCost(4000);
		s.setRent(500);
		p.setColorGroup(ColorGroup.blue);
		game.addSpace(s);

		p = new RealEstate();
		p.setName("Roskildevej");
		p.setCost(2000);
		p.setRent(100);
		p.setColorGroup(ColorGroup.salmon);
		game.addSpace(p);
		
		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);
		
		p = new RealEstate();
		p.setName("Valby Langgade");
		p.setCost(2000);
		p.setRent(100);
		p.setColorGroup(ColorGroup.salmon);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Allégade");
		p.setCost(2400);
		p.setRent(150);
		p.setColorGroup(ColorGroup.salmon);
		game.addSpace(p);
		
		Space prison = new Space();
		prison.setName("Prison");
		game.addSpace(prison);
		
		p = new RealEstate();
		p.setName("Frederiksberg Allé");
		p.setCost(2800);
		p.setRent(200);
		p.setColorGroup(ColorGroup.green);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Coca-Cola Tapperi");
		p.setCost(3000);
		p.setRent(300);
		p.setColorGroup(ColorGroup.darkgreen);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Bülowsvej");
		p.setCost(2800);
		p.setRent(200);
		p.setColorGroup(ColorGroup.green);
		game.addSpace(p);
		
		p = new RealEstate();
		p.setName("Gl. Kongevej");
		p.setCost(3200);
		p.setRent(250);
		p.setColorGroup(ColorGroup.green);
		game.addSpace(p);

		/**
		 * @Markus s174879 resten af felterne nedenfor.
		 */

		s = new Utility();
		s.setName("D.F.D.S.");
		s.setCost(4000);
		s.setRent(200);
		p.setColorGroup(ColorGroup.blue);
		game.addSpace(s);

		p = new RealEstate();
		p.setName("Bernstorffsvej");
		p.setCost(2000);
		p.setRent(300);
		p.setColorGroup(ColorGroup.grey);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Hellerupvej");
		p.setCost(2100);
		p.setRent(500);
		p.setColorGroup(ColorGroup.grey);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Strandvejen");
		p.setCost(2500);
		p.setRent(600);
		p.setColorGroup(ColorGroup.grey);
		game.addSpace(p);

		Space parkering = new Space();
		parkering.setName("Parkering");
		game.addSpace(parkering);

		p = new RealEstate();
		p.setName("Trianglen");
		p.setCost(2600);
		p.setRent(500);
		p.setColorGroup(ColorGroup.red);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Østerbrogade");
		p.setCost(3000);
		p.setCost(650);
		p.setColorGroup(ColorGroup.red);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Grønningen");
		p.setCost(3200);
		p.setRent(600);
		p.setColorGroup(ColorGroup.red);
		game.addSpace(p);

		s = new Utility();
		s.setName("Ø.S.");
		s.setCost(4000);
		s.setRent(500);
		p.setColorGroup(ColorGroup.blue);
		game.addSpace(s);

		p = new RealEstate();
		p.setName("Bredgade");
		p.setCost(3500);
		p.setRent(500);
		p.setColorGroup(ColorGroup.white);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Kgs. Nytorv");
		p.setCost(3800);
		p.setRent(700);
		p.setColorGroup(ColorGroup.white);
		game.addSpace(p);

		s = new Utility();
		s.setName("Carlsberg");
		s.setCost(3000);
		s.setRent(400);
		p.setColorGroup(ColorGroup.darkgreen);
		game.addSpace(s);

		p = new RealEstate();
		p.setName("Østergade");
		p.setCost(3800);
		p.setRent(500);
		p.setColorGroup(ColorGroup.white);
		game.addSpace(p);

		Space toJail = new Space();
		toJail.setName("Gå i fængsel");
		game.addSpace(toJail);

		p = new RealEstate();
		p.setName("Amagertorv");
		p.setCost(4000);
		p.setRent(550);
		p.setColorGroup(ColorGroup.yellow);
		game.addSpace(p);

		p = new RealEstate();
		p.setName("Vimmelskaftet");
		p.setCost(4100);
		p.setRent(600);
		p.setColorGroup(ColorGroup.yellow);
		game.addSpace(p);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new RealEstate();
		p.setName("Nygade");
		p.setCost(4500);
		p.setRent(700);
		p.setColorGroup(ColorGroup.yellow);
		game.addSpace(p);

		s = new Utility();
		s.setName("Bornholm");
		s.setCost(4000);
		s.setRent(500);
		p.setColorGroup(ColorGroup.blue);
		game.addSpace(s);

		chance = new Chance();
		chance.setName("Chance");
		game.addSpace(chance);

		p = new Property();
		p.setName("Frederiksberggade");
		p.setCost(5000);
		p.setRent(750);
		p.setColorGroup(ColorGroup.purple);
		game.addSpace(p);

		t = new Tax();
		t.setName("Ekstraordinær statsskat. Betal 100");
		game.addSpace(t);

		p = new Property();
		p.setName("Rådhuspladsen");
		p.setCost(5200);
		p.setRent(800);
		p.setColorGroup(ColorGroup.purple);
		game.addSpace(p);

		//Added more cards to the pile
		//@s180911 Asger
		List<Card> cards = new ArrayList<Card>();
		
		
		PayTax tax = new PayTax();
		tax.setText("Pay 10% income tax!");
		cards.add(tax);
		
		CardReceiveMoneyFromBank b = new CardReceiveMoneyFromBank();
		b.setText("You receive 100$ from the bank.");
		b.setAmount(100);
		cards.add(b);


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
	 * @param players
	 * @author s185034, s185466
	 */
	public void createPlayers (Game game, Game players) {
		
		for (int i=0; i <= players.sizeOfList(); i++){
			String name = JOptionPane.showInputDialog(null,
					"Please enter your name","Name",JOptionPane.QUESTION_MESSAGE);
			Player p = new Player();
			p.setName(name);
			p.setCurrentPosition(game.getSpaces().get(0));

			switch (i+1){

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
			i++;

/*
			 p.setColor(colorlist.get(i)); //colorlist kommer
			 p.setColor(randomColor); // måske virker dette? - Rasmus

			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, g, b);
			 */
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
				"Do you wish to load a game?, 'yes' or 'no'","Game",JOptionPane.QUESTION_MESSAGE);
        if(result.equals("yes")){
		int usergameID = Integer.parseInt(JOptionPane.showInputDialog(null, "What game do you want to load","GameLoader",JOptionPane.QUESTION_MESSAGE));

		Game game = createGame();
		Database gameDAO = new Database();
		gameDAO.loadGame(game, usergameID);

			game.shuffleCardDeck();
			createPlayers(game);
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


/** For at fjerne vores non-static problem kan vi sætte nedenstående del ind i gamecontroller (hvor gui'en er) og så kalde det herinde i vores main.
*/

/*
String result = JOptionPane.showInputDialog(null,
				"Do you wish to load a game?, 'yes' or 'no'","Game",JOptionPane.QUESTION_MESSAGE);
        if(result.equals("yes")){
		int usergameID = Integer.parseInt(JOptionPane.showInputDialog(null, "What game do you want to load","GameLoader",JOptionPane.QUESTION_MESSAGE));

		Game game = createGame();
		Database gameDAO = new Database();
		gameDAO.loadGame(game, usergameID);

			game.shuffleCardDeck();
			createPlayers(game);
			GameController controller = new GameController(game);
			controller.initializeGUI();

		} else {
			Game game = createGame();
			game.shuffleCardDeck();
			createPlayers(game);

 */