package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CurrencyDao {
    protected ConnectionManager connectionManager;
    private static CurrencyDao instance = null;
    protected CurrencyDao(){
        connectionManager = new ConnectionManager();
    }
    public static CurrencyDao getInstance() {
        if(instance==null) {
            instance = new CurrencyDao();
        }
        return instance;
    }

    public Currency create(Currency currency)throws SQLException{
        String insertCurrency = "INSERT INTO Currency(`Name`,TotalCap,WeeklyCap) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCurrency, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,currency.getName());
            insertStmt.setInt(2,currency.getTotalCap());
            insertStmt.setInt(3,currency.getWeeklyCap());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int jobID = -1;
            if(resultKey.next()) {
                jobID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            currency.setCurrencyID(jobID);
            return currency;

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
