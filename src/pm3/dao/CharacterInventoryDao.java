package pm3.dao;

import pm3.model.Character;
import pm3.model.CharacterInventory;
import pm3.model.Item;

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
    public CharacterInventory getByInventorySlotID(int inventorySlotID) throws SQLException {
        String selectCharacterInventory = "SELECT InventorySlotID, CharacterID, ItemID, StackSize FROM CharacterInventory WHERE InventorySlotID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterInventory);
            selectStmt.setInt(1, inventorySlotID);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int foundInventorySlotID = results.getInt("InventorySlotID");
                int characterID = results.getInt("CharacterID");
                int itemID = results.getInt("ItemID");
                int stackSize = results.getInt("StackSize");

                Character character = CharacterDao.getInstance().getCharacterById(characterID); // Assuming this method exists
                Item item = ItemDao.getInstance().getItemByID(itemID); // Assuming this method exists

                CharacterInventory characterInventory = new CharacterInventory(foundInventorySlotID);
                characterInventory.setCharacter(character);
                characterInventory.setItem(item);
                characterInventory.setStackSize(stackSize);

                return characterInventory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(results != null) {
                results.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public CharacterInventory updateStackSize(int inventorySlotID, int newStackSize) throws SQLException {
        String updateCharacterInventory = "UPDATE CharacterInventory SET StackSize = ? WHERE InventorySlotID = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCharacterInventory);
            updateStmt.setInt(1, newStackSize);
            updateStmt.setInt(2, inventorySlotID);

            updateStmt.executeUpdate();

            return getByInventorySlotID(inventorySlotID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(updateStmt != null) {
                updateStmt.close();
            }
            if(connection != null) {
                connection.close();
            }
        }
    }
}
