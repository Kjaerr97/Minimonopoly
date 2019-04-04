package dk.dtu.compute.se.pisd.monopoly.mini.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class s171281 {


    public class UserDAOImpls185034 extends GameDAO {
        private Connection createConnection() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s171281",
                    "s171281", "6ixUAhvpEnhjDB6CxunnF");

        }

        public void createGame(GameDAO game) throws DALException {
            try (Connection c = createConnection()) {

                PreparedStatement stm = c.prepareStatement("INSERT INTO game VALUES (?, ?)");

                stm.setInt(1, getGame_id());

                stm.setInt(2, getPlayer_id()); //TODO skal lige overvejes

                stm.executeUpdate();


            } catch (SQLException e) {
                throw new DALException(e.getMessage());

            }
        }


        public void createPlayers(GameDAO game) throws DALException {
            try (Connection c = createConnection()) {

                PreparedStatement stm = c.prepareStatement("INSERT INTO players VALUES (?, ?)");

                stm.setInt(1, getPlayer_id());
                stm.setString(2, getPlayerName());
                stm.executeUpdate();


            } catch (SQLException e) {
                throw new DALException(e.getMessage());
            }

        }
    }

    // TODO Vi mangler at tilkoble et game et game_id
    // TODO Vi mangler at tilkoble et player_id til en player
    // TODO ER DAO NØDVENDIG?
    // 

    }
