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

            insertStmt.setString(1,characterEquippedGear.getGearSlot().toString());
            insertStmt.setInt(2,characterEquippedGear.getCharacter().getCharacterID());
            //insertStmt.setInt(3,characterEquippedGear.getGear().getGearID());

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
}
