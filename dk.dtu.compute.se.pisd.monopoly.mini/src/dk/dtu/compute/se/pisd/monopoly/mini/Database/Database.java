package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements IGameDAO {

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185466",
                "s185466", "J8LTqO61u5Ux9fEBcIm9Y"); // indsæt egne værdier her

    }

    /**
     *   @author Andreas s185034, Markus s174879, Asger s180911 og Sascha s171281
     */

    @Override

    public void loadGame(Game game, int gameID) {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
//Henter resultset fra databsen
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Player.playerID, Player.currentPosition, " +
                                                            "Player.inPrison, Player.isBroke, player.balance, player.colour gameID FROM Game WEHERE gameID=" + gameID);
            List<Player> listOfPlayer = new ArrayList<>();
            while(resultSet.next()){

                //Laver en player udfra resultset
                Player player = new Player();
                player.setBroke(resultSet.getBoolean("isBroke"));
                player.setBalance(resultSet.getInt("balance"));
                player.setCurrentPosition(game.getSpaces().get(resultSet.getInt("currentPosition")));
                player.setColor(new Color(resultSet.getInt("colour")));
                player.setPlayerID((resultSet.getInt("playerID")));
                player.setInPrison(resultSet.getBoolean("inPrison"));

                //tilføjer playeren til vores array
                listOfPlayer.add(resultSet.getInt("playerID"), player);

            }
            //Sætter vores array til gamets nuværende player array.
            game.setPlayers(listOfPlayer);




        } catch (SQLException e){
            e.printStackTrace();
        }

        }

    /**
     *   @author Andreas s185034, Markus s174879, Asger s180911 og Sascha s171281
     */

    @Override
    public void updateGame(Game game, int gameID) {

        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Player VALUES balance= ?, currentPosition=?, isBroke=?, inPrison=?,colour=? WHERE gameID=" + gameID);

            for ( Player player : game.getPlayers()) {
                statement2.setInt(1, player.getBalance());
                statement2.setInt(2, player.getCurrentPosition().getIndex());
                statement2.setBoolean(3, player.isBroke());
                statement2.setBoolean(4, player.isInPrison());
                statement2.setInt(5, player.getColor().getRGB());
                statement2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        }


    /**
     *   @author Andreas s185034, Markus s174879, Asger s180911 og Sascha s171281
     */

        @Override
    public void deleteGame(Game game, int gameID) {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("DELELTE FROM Game WHERE gameID=" + gameID);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *   @author Andreas s185034, Markus s174879, Asger s180911 og Sascha s171281
     */

    @Override
    public void saveGame(Game game) {

        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
//indsætter i vores Game tabel
            PreparedStatement createGame = connection.prepareStatement(
                    "INSERT INTO Game (gameName, currentplayer) VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);

            createGame.setInt(1, game.getPlayers().indexOf(game.getCurrentPlayer()));

            /*
            Vi opretter createGame ovenfor, hvor generated keys bliver returneret.
            I metoden nedenfor sætter vi gamekey til at være den generede key, herefter tildeler vi gamekey til gameID
            gameID bliver i setGameID() sat til at være int-værdien som returneres fra gamekey.getInt().
             */
            createGame.executeUpdate();
            ResultSet gamekey = createGame.getGeneratedKeys();
            int gameID = 0;
            if (gamekey.next()){
                gameID = gamekey.getInt(1);
                game.setGameID(gameID);

            }

//Indsætter i vores player tabel
            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Player VALUES (?,?,?,?,?,?)");

            int playerID = 0;
            for ( Player player : game.getPlayers()) {
                playerID = game.getPlayers().indexOf(player);

                statement2.setInt(1, playerID);
                statement2.setInt(2, player.getBalance());
                statement2.setInt(3, player.getCurrentPosition().getIndex());
                statement2.setBoolean(4, player.isBroke());
                statement2.setBoolean(5, player.isInPrison());
                statement2.setInt(6, player.getColor().getRGB());
                statement2.executeUpdate();

            }
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }


    }
}