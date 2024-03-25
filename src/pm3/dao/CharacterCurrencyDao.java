package pm3.dao;

import pm3.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterCurrencyDao {
    protected ConnectionManager connectionManager;
    private static CharacterCurrencyDao instance = null;
    protected CharacterCurrencyDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterCurrencyDao getInstance() {
        if(instance==null) {
            instance = new CharacterCurrencyDao();
        }
        return instance;
    }

    public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException{
        String insertCharacterCurrency = "INSERT INTO CharacterCurrency(CharacterID,CurrencyID,Amount,AmountEarnedWeek) VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterCurrency);

            insertStmt.setInt(1,characterCurrency.getCharacter().getCharacterID());
            insertStmt.setInt(2,characterCurrency.getCurrency().getCurrencyID());
            insertStmt.setInt(3,characterCurrency.getAmount());
            insertStmt.setInt(4,characterCurrency.getAmountEarnedWeek());

            insertStmt.executeUpdate();

            return characterCurrency;
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
