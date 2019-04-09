package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDAO extends Game {

    private String playerName;
    private String player_id;
    private Player current;
    private int game_id;
    private List<Player> players = new ArrayList<Player>();

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

   /*
    public String getPlayer_id() {

        this.playerName = player_id;
        return player_id;
    }
    */


    public Player getCurrent() {
        return current;
    }

    public void setCurrent(String current){
       this.current = getCurrentPlayer();
   }

    public void gameState (Player players, Game game){
        this.players = getPlayers();
        this.getCurrentPlayer();
        this.playerName = player_id;

    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

















}
