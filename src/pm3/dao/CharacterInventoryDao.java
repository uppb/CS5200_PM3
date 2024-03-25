package pm3.dao;

import pm3.model.CharacterInventory;

import java.sql.*;

public class CharacterInventoryDao {
    protected ConnectionManager connectionManager;
    private static CharacterInventoryDao instance = null;
    protected CharacterInventoryDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterInventoryDao getInstance() {
        if(instance==null) {
            instance = new CharacterInventoryDao();
        }
        return instance;
    }

    public CharacterInventory create(CharacterInventory characterInventory) throws SQLException{
        String insertCharacterInventory = "INSERT INTO CharacterInventory(CharacterID,ItemID,StackSize) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterInventory, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setInt(1,characterInventory.getCharacter().getCharacterID());
            //insertStmt.setInt(2,characterInventory.getItem().getItemID());
            insertStmt.setInt(3,characterInventory.getStackSize());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();

            int inventorySlotID = -1;
            if(resultKey.next()) {
                inventorySlotID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            characterInventory.setInventorySlotID(inventorySlotID);

            return characterInventory;
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
