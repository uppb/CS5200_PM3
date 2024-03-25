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
            insertStmt.setInt(6, character.getMainHandWeapon().getItemID());
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

    public Character getCharacterById(int characterID) throws SQLException {
        String selectCharacter = "SELECT CharacterID, PlayerID, FirstName, LastName, MaxHP, MaxMP, CurrentJobID, MainHandWeaponID, Strength, Dexterity, Vitality, Intelligence, Mind, CriticalHit, Determination, DirectHitRate, Defense, MagicDefense, AttackPower, SkillSpeed, AttackMagicPotency, HealingMagicPotency, SpellSpeed, AverageItemLevel, Tenacity, Piety FROM `Character` WHERE CharacterID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacter);
            selectStmt.setInt(1, characterID);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultCharacterID = results.getInt("CharacterID");
                // Assuming getPlayerById returns a Player object
                Player player = PlayerDao.getInstance().getPlayerById(results.getInt("PlayerID"));
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                int maxHP = results.getInt("MaxHP");
                int maxMP = results.getInt("MaxMP");
                // Placeholder for fetching Job; implement according to your model
                Job currentJob = new Job(results.getInt("CurrentJobID"));
                // Placeholder for fetching Weapon; implement according to your model
                Weapon mainHandWeapon = new Weapon(results.getInt("MainHandWeaponID"));
                int strength = results.getInt("Strength");
                int dexterity = results.getInt("Dexterity");
                int vitality = results.getInt("Vitality");
                int intelligence = results.getInt("Intelligence");
                int mind = results.getInt("Mind");
                int criticalHit = results.getInt("CriticalHit");
                int determination = results.getInt("Determination");
                int directHitRate = results.getInt("DirectHitRate");
                int defense = results.getInt("Defense");
                int magicDefense = results.getInt("MagicDefense");
                int attackPower = results.getInt("AttackPower");
                int skillSpeed = results.getInt("SkillSpeed");
                int attackMagicPotency = results.getInt("AttackMagicPotency");
                int healingMagicPotency = results.getInt("HealingMagicPotency");
                int spellSpeed = results.getInt("SpellSpeed");
                int averageItemLevel = results.getInt("AverageItemLevel");
                int tenacity = results.getInt("Tenacity");
                int piety = results.getInt("Piety");

                Character character = new Character(characterID, player, firstName, lastName, maxHP, maxMP, currentJob, mainHandWeapon, strength, dexterity, vitality, intelligence, mind, criticalHit, determination, directHitRate, defense, magicDefense, attackPower, skillSpeed, attackMagicPotency, healingMagicPotency, spellSpeed, averageItemLevel, tenacity, piety);
                return character;
            }
        }  catch (SQLException e) {
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
}
