package pm3.dao;

import pm3.model.WeaponTypeJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeaponTypeJobDao {
    protected ConnectionManager connectionManager;
    private static WeaponTypeJobDao instance = null;
    protected WeaponTypeJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static WeaponTypeJobDao getInstance() {
        if(instance==null) {
            instance = new WeaponTypeJobDao();
        }
        return instance;
    }

    public WeaponTypeJob create(WeaponTypeJob weaponTypeJob) throws SQLException{
        String insertWeaponTypeJob = "INSERT INTO WeaponTypeJob(JobID, GearType) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponTypeJob);

            insertStmt.setInt(1,weaponTypeJob.getJob().getJobID());
            insertStmt.setString(2,weaponTypeJob.getWeaponType().toString());

            insertStmt.executeUpdate();

            return weaponTypeJob;
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
