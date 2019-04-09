package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import java.util.ArrayList;
import java.util.List;

public interface IGameDAO {

    void saveGame (Game game);

    void loadGame (Game game, int gameID);

    void updateGame (Game game, int gameID);

    void deleteGame (Game game, int gameID);

}
