package dk.dtu.compute.se.pisd.monopoly.mini.model;

import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import gui_main.GUI;
import org.junit.Test;

import java.awt.*;

import static dk.dtu.compute.se.pisd.monopoly.mini.MiniMonopoly.createGame;
import static org.junit.Assert.*;

public class PlayerTest {
    private Game game;
    private boolean broke = false;
    private int balance = 100;

    @Test
    public void getColor() {
        Player player = new Player();
        Game game = createGame();

        game.addPlayer(player);
        player.setColor(Color.red);

        player.getColor();
    }

    @Test
    public void isBroke() {
        Player player = new Player();
        Game game = createGame();
        game.addPlayer(player);

        player.setBroke(true);

        assertEquals(true,player.isBroke());
    }

    @Test
    public void isInPrison() {
        Player player = new Player();
        Game game = createGame();
        game.addPlayer(player);

        player.setInPrison(true);

        assertEquals(true,player.isInPrison());
    }

    @Test
    public void playerAssets(){
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
        int assets = 0;
        assets += balance + p.getCost();

        assertEquals(1300,assets);
    }

}