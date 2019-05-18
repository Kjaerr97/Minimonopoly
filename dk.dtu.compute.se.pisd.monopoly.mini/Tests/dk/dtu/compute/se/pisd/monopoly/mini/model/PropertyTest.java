package dk.dtu.compute.se.pisd.monopoly.mini.model;

import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import org.junit.Test;

import static dk.dtu.compute.se.pisd.monopoly.mini.MiniMonopoly.createGame;
import static org.junit.Assert.*;

public class PropertyTest {
    private Game game;

    @Test
    public void getOwner() {
        Player player = new Player();
        Game game = createGame();

        game.addPlayer(player);

        RealEstate p = new RealEstate();
        p.setName("Rødovrevej");
        p.setCost(1200);
        p.setRent(50);
        p.setColorGroup(ColorGroup.lightblue);
        p.setHousePrice(1000);
        game.addSpace(p);

        p.setOwner(player);

        assertEquals(player,player);

    }
}