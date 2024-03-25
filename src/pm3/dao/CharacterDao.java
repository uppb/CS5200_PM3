package pm3.dao;

import pm3.model.*;
import pm3.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CharacterDao {
    protected ConnectionManager connectionManager;

    private static CharacterDao instance = null;
    protected CharacterDao(){
        connectionManager = new ConnectionManager();
    }
    public static CharacterDao getInstance() {
        if(instance==null) {
            instance = new CharacterDao();
        }
        return instance;
    }

    public Character create(Character character) throws SQLException{
        String insertCharacter =
                "INSERT INTO `Character` (FirstName, LastName, HPMax, MPMAX, CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, SpellSpeed, AverageItemLevel, Tenacity, Piety) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try{
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacter, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, character.getFirstName());
            insertStmt.setString(2, character.getLastName());
            insertStmt.setInt(3, character.getMaxHP());
            insertStmt.setInt(4, character.getMaxMP());
            insertStmt.setInt(5, character.getCurrentJob().getJobID());
            //insertStmt.setInt(6, character.getMainHandWeapon().getWeaponID()));
            insertStmt.setInt(7, character.getStrength());
            insertStmt.setInt(8, character.getDexterity());
            insertStmt.setInt(9, character.getVitality());
            insertStmt.setInt(10, character.getIntelligence());
            insertStmt.setInt(11, character.getMind());
            insertStmt.setInt(12, character.getCriticalHit());
            insertStmt.setInt(13, character.getDetermination());
            insertStmt.setInt(14, character.getDirectHitRate());
            insertStmt.setInt(15, character.getDefense());
            insertStmt.setInt(16, character.getMagicDefense());
            insertStmt.setInt(17, character.getAttackPower());
            insertStmt.setInt(18, character.getSkillSpeed());
            insertStmt.setInt(19, character.getAttackMagicPotency());
            insertStmt.setInt(20, character.getHealingMagicPotency());
            insertStmt.setInt(21, character.getSpellSpeed());
            insertStmt.setInt(22, character.getAverageItemLevel());
            insertStmt.setInt(23, character.getTenacity());
            insertStmt.setInt(24, character.getPiety());

            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int characterID = -1;
            if(resultKey.next()) {
                characterID = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            character.setCharacterID(characterID);
            return character;

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
