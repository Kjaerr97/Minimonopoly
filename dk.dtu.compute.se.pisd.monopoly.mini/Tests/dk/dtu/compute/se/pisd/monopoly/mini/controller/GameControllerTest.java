package dk.dtu.compute.se.pisd.monopoly.mini.controller;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static dk.dtu.compute.se.pisd.monopoly.mini.MiniMonopoly.createGame;
import static org.junit.Assert.*;

public class GameControllerTest {

    private Game game;

    @Test
    public void payment() {
        Player payer = new Player();
        Player reciever = new Player();

        payer.setBalance(100);
        reciever.setBalance(100);

        int amount = 50;

        payer.payMoney(amount);
        reciever.receiveMoney(amount);
    }

    @Test
    public void moveToSpace(){
        Player player = new Player();
        Game game = createGame();

        game.addPlayer(player);
        game.getPlayers().get(0).setCurrentPosition(game.getSpaces().get(2));

        assertEquals(2,game.getPlayers().get(0).getCurrentPosition().getIndex());
    }



}