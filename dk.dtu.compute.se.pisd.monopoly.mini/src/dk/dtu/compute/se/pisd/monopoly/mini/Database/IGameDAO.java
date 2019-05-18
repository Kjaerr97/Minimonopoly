package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;

public interface IGameDAO {

    void saveGame (Game game);

    void loadGame (Game game, int gameID);

    void deleteGame (Game game, int gameID);

}
