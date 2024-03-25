package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PlayerDao {
    protected ConnectionManager connectionManager;
    private static PlayerDao instance = null;
    protected PlayerDao(){
        connectionManager = new ConnectionManager();
    }
    public static PlayerDao getInstance() {
        if(instance==null) {
            instance = new PlayerDao();
        }
        return instance;
    }

    public Player create(Player player) throws SQLException{
        String insertPlayer = "INSERT INTO Player(`Name`,EmailAddress) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,player.getName());
            insertStmt.setString(2,player.getEmailAddress());

            insertStmt.executeUpdate();


            resultKey = insertStmt.getGeneratedKeys();
            int playerID = -1;
            if(resultKey.next()) {
                playerID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            player.setPlayerID(playerID);
            return player;

        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }
}
