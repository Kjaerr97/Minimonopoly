package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import java.util.ArrayList;
import java.util.List;

public interface IGameDAO {

    void saveGame (Game game);

    Game load (Game game, Game gameID);

    void updateGame (Game game);

    void deleteGame (Game game);



}
