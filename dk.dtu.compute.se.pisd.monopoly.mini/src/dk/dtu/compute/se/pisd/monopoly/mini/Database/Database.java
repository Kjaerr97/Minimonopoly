package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.Ferry;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.RealEstate;
import dk.dtu.compute.se.pisd.monopoly.mini.model.properties.Soda;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements IGameDAO {

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s180911",
                "s180911", "QekXA8EbroGRPIN1MOpRI"); // indsæt egne værdier her

    }

    /**
     *   @author Andreas s185034, Markus s174879, Asger s180911 og Sascha s171281
     */

    @Override

    public void loadGame(Game game, int gameID) {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
//PLAYER LOAD
            PreparedStatement playerLoad = connection.prepareStatement("SELECT * FROM player WHERE game_id = ?");
            playerLoad.setInt(1, gameID);

            ResultSet playerResultset = playerLoad.executeQuery();
            List<Player> listOfPlayer = new ArrayList<>();
            while (playerResultset.next()) {


                Player player = new Player();
                player.setBroke(playerResultset.getBoolean("isBroke"));
                player.setBalance(playerResultset.getInt("balance"));
                player.setCurrentPosition(game.getSpaces().get(playerResultset.getInt("currentPosition")));
                player.setColor(new Color(playerResultset.getInt("color")));
                player.setPlayerID((playerResultset.getInt("player_id")));
                player.setInPrison(playerResultset.getBoolean("inPrison"));
                player.setName(playerResultset.getString("playerName"));

                //tilføjer playeren til vores array
                listOfPlayer.add(playerResultset.getInt("player_id"), player);

            }
            //Sætter vores array til gamets nuværende player array.
            game.setPlayers(listOfPlayer);

//PROPERTY LOAD
            PreparedStatement propertyLoad = connection.prepareStatement("SELECT * FROM property WHERE game_id = ?");
            propertyLoad.setInt(1,gameID);

            ResultSet propertyResults = propertyLoad.executeQuery();

            List<Space> SpaceList = new ArrayList<Space>();
            SpaceList.addAll(game.getSpaces());
            while (propertyResults.next()) {
                if(propertyResults.getString("propertyType").equals("realestate")){
                    RealEstate realEstate = (RealEstate) SpaceList.get(playerResultset.getInt("boardPosition"));

                    realEstate.setHouses(propertyResults.getInt("houses"));
                    realEstate.setOwner(game.getPlayers().get(propertyResults.getInt("player_id")));

                    SpaceList.set(propertyResults.getInt("boardPostition"), realEstate);
                }

                if(propertyResults.getString("propertyType").equals("ferry")){

                    Ferry ferry = (Ferry) SpaceList.get(playerResultset.getInt("boardPosition"));
                    ferry.setOwner(game.getPlayers().get(propertyResults.getInt("player_id")));
                    SpaceList.set(propertyResults.getInt("boardPosition"), ferry);

                }
                if(propertyResults.getString("propertyType").equals("soda")) {

                    Soda soda = (Soda) SpaceList.get(playerResultset.getInt("boardPosition"));
                    soda.setOwner(game.getPlayers().get(propertyResults.getInt("player_id")));
                    SpaceList.set(propertyResults.getInt("boardPosition"), soda);

                }
                game.setSpaces(SpaceList);
            }


            } catch(SQLException e){
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

            PreparedStatement statement = connection.prepareStatement("DELETE * FROM game WHERE game_id= ?");
            PreparedStatement statement1 = connection.prepareStatement("DELETE *  FROM player WHERE game_id = ?");
            PreparedStatement statement2 = connection.prepareStatement("DELETE * FROM property WHERE game_id = ? ");

            statement.setInt(1,gameID);
            statement1.setInt(1,gameID);
            statement2.setInt(1,gameID);

            statement.executeUpdate();
            statement1.executeUpdate();
            statement2.executeUpdate();
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
                    "INSERT INTO game VALUES (null)", Statement.RETURN_GENERATED_KEYS);

           createGame.executeUpdate();

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
            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO player VALUES (?,?,?,?,?,?,?,?)");

            int playerID = 0;
            for (Player player : game.getPlayers()) {
                playerID = game.getPlayers().indexOf(player);


                statement2.setInt(1, playerID);
                statement2.setInt(2,gameID);
                statement2.setInt(3, player.getCurrentPosition().getIndex());
                statement2.setBoolean(4, player.isBroke());
                statement2.setBoolean(5, player.isInPrison());
                statement2.setInt(6, player.getBalance());
                statement2.setInt(7, player.getColor().getRGB());
                statement2.setString(8,player.getName());
                statement2.executeUpdate();
            }
//Properties
            PreparedStatement spaceStatement = connection.prepareStatement("INSERT INTO property " + "VALUES(?,?,?,?,?,?);");

              //  for (Space space : game.getSpaces()) {
            for (int i = 0; i >= game.getSpaces().size(); i++) {
                if (game.getSpaces().get(i) instanceof Property) {
                    spaceStatement.setInt(1, game.getSpaces().get(i).getIndex());
                }

                // if (space instanceof Property) {
                //      spaceStatement.setInt(1, space.getIndex());
                int player_id = -1;
                for (Player player : game.getPlayers()) {
                    if (player.getOwnedProperties().contains(game.getSpaces().get(i))) {
                        player_id = game.getPlayers().indexOf(player);
                        spaceStatement.setInt(2, player_id);
                    }

                }
                if (game.getSpaces().get(i) instanceof Ferry) {

                    spaceStatement.setString(3, "ferry");
                }
                if (game.getSpaces().get(i) instanceof Soda) {

                    spaceStatement.setString(3, "soda");
                }

                if (game.getSpaces().get(i) instanceof RealEstate) {
                    RealEstate realEstate = (RealEstate) game.getSpaces().get(i);
                    spaceStatement.setInt(4, realEstate.getHouses());
                    spaceStatement.setString(5, "realestate");
                }
                spaceStatement.setInt(6, gameID);
                spaceStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}