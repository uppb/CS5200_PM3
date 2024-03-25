package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GearTypeJobDao {
    protected ConnectionManager connectionManager;
    private static GearTypeJobDao instance = null;
    protected GearTypeJobDao(){
        connectionManager = new ConnectionManager();
    }
    public static GearTypeJobDao getInstance() {
        if(instance==null) {
            instance = new GearTypeJobDao();
        }
        return instance;
    }

    public GearTypeJob create(GearTypeJob gearTypeJob) throws SQLException{
        String insertGearTypeJob = "INSERT INTO GearTypeJob(JobID, GearType) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearTypeJob);

            insertStmt.setInt(1,gearTypeJob.getJob().getJobID());
            insertStmt.setString(2,gearTypeJob.getGearType().toString());

            insertStmt.executeUpdate();

            return gearTypeJob;
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
