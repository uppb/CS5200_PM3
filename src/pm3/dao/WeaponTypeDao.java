package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class WeaponTypeDao {
    protected ConnectionManager connectionManager;
    private static WeaponTypeDao instance = null;
    protected WeaponTypeDao(){
        connectionManager = new ConnectionManager();
    }
    public static WeaponTypeDao getInstance() {
        if(instance==null) {
            instance = new WeaponTypeDao();
        }
        return instance;
    }

    public WeaponType create(WeaponType weaponType)throws SQLException{
        String insertWeaponType = "INSERT INTO WeaponType(TypeName) VALUES(?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponType);

            insertStmt.setString(1,weaponType.toString());

            insertStmt.executeUpdate();

            return weaponType;

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
