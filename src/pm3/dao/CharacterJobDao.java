package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterJobDao {
    protected ConnectionManager connectionManager;
    private static CharacterJobDao instance = null;
    protected CharacterJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterJobDao getInstance() {
        if(instance==null) {
            instance = new CharacterJobDao();
        }
        return instance;
    }

    public CharacterJob create(CharacterJob characterJob) throws SQLException{
        String insertCharacterJob = "INSERT INTO CharacterJob(CharacterID,JobID,Level) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterJob);

            insertStmt.setInt(1,characterJob.getCharacter().getCharacterID());
            insertStmt.setInt(2,characterJob.getJob().getJobID());
            insertStmt.setInt(3,characterJob.getLevel());

            insertStmt.executeUpdate();

            return characterJob;
        } catch (SQLException e) {
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
