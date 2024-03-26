package pm3.dao;

import pm3.model.*;

import java.sql.*;

public class CharacterEquippedGearDao {
    protected ConnectionManager connectionManager;
    private static CharacterEquippedGearDao instance = null;
    protected CharacterEquippedGearDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterEquippedGearDao getInstance() {
        if(instance==null) {
            instance = new CharacterEquippedGearDao();
        }
        return instance;
    }

    public CharacterEquippedGear create(CharacterEquippedGear characterEquippedGear) throws SQLException{
        String insertCharacterEquippedGear = "INSERT INTO CharacterEquippedGear(SlotName, CharacterID, GearID) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterEquippedGear, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1,characterEquippedGear.getSlotName().toString());
            insertStmt.setInt(2,characterEquippedGear.getCharacterID());
            insertStmt.setInt(3,characterEquippedGear.getGearID());

            insertStmt.executeUpdate();

            return characterEquippedGear;

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

    public CharacterEquippedGear getByCharacterIdAndSlotName(int characterId, String slotName) throws SQLException {
        String selectCharacterEquippedGear = "SELECT SlotName, CharacterID, GearID FROM CharacterEquippedGear WHERE CharacterID = ? AND SlotName = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacterEquippedGear);
            selectStmt.setInt(1, characterId);
            selectStmt.setString(2, slotName);

            results = selectStmt.executeQuery();
            if(results.next()) {
                String foundSlotName = results.getString("SlotName");
                Integer foundCharacterID = results.getInt("CharacterID");
                Integer gearID = results.getInt("GearID");

                CharacterEquippedGear characterEquippedGear = new CharacterEquippedGear(foundSlotName, foundCharacterID, gearID);
                return characterEquippedGear;
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

    public boolean updateCharacterEquippedGear(int characterId, String slotName, Integer newGearID) throws SQLException {
        String updateCharacterEquippedGear = "UPDATE CharacterEquippedGear SET GearID = ? WHERE CharacterID = ? AND SlotName = ?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCharacterEquippedGear);
            updateStmt.setInt(1, newGearID);
            updateStmt.setInt(2, characterId);
            updateStmt.setString(3, slotName);

            int affectedRows = updateStmt.executeUpdate();

            return affectedRows > 0;
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
