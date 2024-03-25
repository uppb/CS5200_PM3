package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GearSlotDao {
    protected ConnectionManager connectionManager;
    private static GearSlotDao instance = null;
    protected GearSlotDao(){
        connectionManager = new ConnectionManager();
    }
    public static GearSlotDao getInstance() {
        if(instance==null) {
            instance = new GearSlotDao();
        }
        return instance;
    }

    public GearSlot create(GearSlot gearSlot)throws SQLException{
        String insertGearSlot = "INSERT INTO GearSlot(TypeName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearSlot);

            insertStmt.setString(1,gearSlot.toString());

            insertStmt.executeUpdate();

            return gearSlot;

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
