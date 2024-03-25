package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JobDao {
    protected ConnectionManager connectionManager;
    private static JobDao instance = null;
    protected JobDao(){
        connectionManager = new ConnectionManager();
    }
    public static JobDao getInstance() {
        if(instance==null) {
            instance = new JobDao();
        }
        return instance;
    }

    public Job create(Job job)throws SQLException{
        String insertJob = "INSERT INTO Job(`Name`,LevelCap) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,job.getName());
            insertStmt.setInt(2,job.getLevelCap());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int jobID = -1;
            if(resultKey.next()) {
                jobID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            job.setJobID(jobID);
            return job;

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
