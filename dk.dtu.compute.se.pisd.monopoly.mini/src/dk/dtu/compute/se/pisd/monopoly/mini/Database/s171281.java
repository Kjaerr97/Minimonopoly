package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class s171281 implements IGameDAO {

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s171281",
                "s171281", "6ixUAhvpEnhjDB6CxunnF");

    }

    @Override
    public Game load(Game game, int gameID) {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
//Henter resultset fra databsen
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Players.playerID, Players.currentPosition, " +
                                                            "Players.inPrison, Players.isBroke, gameID FROM Game WEHERE gameID=" + gameID);



            List<Player> listOfPlayer = new ArrayList<>();
            while(resultSet.next()){

                //Laver en pllayer udfra resultset
                Player player = new Player();
                player.setBroke(resultSet.getBoolean("isBroke"));
                player.setBalance(resultSet.getInt("balance"));
                player.setCurrentPosition(game.getSpaces().get(resultSet.getInt("currentPosition")));
                player.setColor(new Color(resultSet.getInt("colour")));
                player.setID(resultSet.getInt("playerID"));
                player.setInPrison(resultSet.getBoolean("inPrison"));

                //tilføjer playeren til vores array
                listOfPlayer.add(resultSet.getInt("playerID"), player);

            }
            //Sætter vores array til gamets nuværende player array.
            game.setPlayers(listOfPlayer);




        } catch (SQLException e){
            e.printStackTrace();
        }
            return null;
        }

    @Override
    public void updateGame(Game game, int gameID) {
            deleteGame(game, gameID);
            saveGame(game);
        }

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


    @Override
    public void saveGame(Game game) {


        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
//indsætter i vores Game tabel
            PreparedStatement createGame = connection.prepareStatement(
                    "INSERT INTO Game (gameName, currentplayer) VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);

            createGame.setString(1, "");
            createGame.setInt(2, game.getPlayers().indexOf(game.getCurrentPlayer()));
            createGame.executeUpdate();

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