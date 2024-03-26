package pm3.tools;

import pm3.dao.*;
import pm3.model.*;
import pm3.model.Character;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {


	public static void insertPlayers(PlayerDao playerDao) throws SQLException {
		Player player1 = new Player("Link", "link@hyrule.com");
		playerDao.create(player1);
		System.out.println("Created Player: " + player1.getName() + " with email: " + player1.getEmailAddress());

		Player player2 = new Player("Mario", "mario@1.com");
		playerDao.create(player2);
		System.out.println("Created Player: " + player2.getName() + " with email: " + player2.getEmailAddress());

		Player player3 = new Player("Lara Lara", "lara@croftmanor.com");
		playerDao.create(player3);
		System.out.println("Created Player: " + player3.getName() + " with email: " + player3.getEmailAddress());

		// Get one of the players by ID
		Player retrievedPlayer = playerDao.getPlayerById(player1.getPlayerID());
		if(retrievedPlayer != null) {
			System.out.println("Retrieved Player by ID: " + retrievedPlayer.getName() + " with ID: " + retrievedPlayer.getPlayerID());
		} else {
			System.out.println("Player with ID: " + player1.getPlayerID() + " not found.");
		}

		// Delete a player
		boolean deleteSuccess = playerDao.delete(player3.getPlayerID());
		if(deleteSuccess) {
			System.out.println("Deleted Player: " + player3.getName());
		} else {
			System.out.println("Could not delete Player: " + player3.getName());
		}

		Player checkDeletion = playerDao.getPlayerById(player3.getPlayerID());
		if(checkDeletion == null) {
			System.out.println("Player " + player3.getName() + " successfully deleted.");
		} else {
			System.out.println("Player " + player3.getName() + " was not deleted properly.");
		}
	}

	public static void insertCharacters(CharacterDao characterDao) throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		JobDao jobDao = JobDao.getInstance();
		Player player1 = new Player("Link", "link@hyrule.com");

		Job job1 = new Job("Warrior", 50);
		jobDao.create(job1);

		WeaponDao weaponDao = WeaponDao.getInstance();

		Weapon weapon1 = new Weapon("Sword of Might", 1, true, 10,
				"SWORD", 10, 50, 25.00,1.5);
		weapon1.setVendorPrice(200.00);
		weapon1.setStrengthBonus(5);
		weapon1.setVitalityBonus(0);
		weapon1.setDeterminationBonus(3);
		weapon1.setDirectHitRateBonus(2);
		weapon1.setSkillSpeedBonus(0);
		weapon1.setTenacityBonus(0);
		weapon1.setCriticalHitBonus(2);
		weaponDao.create(weapon1);
		System.out.println("Weapon1 okay");

		Character character1 = new Character(player1,"Cloud", "Ava", 999, 999, job1, weapon1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		characterDao.create(character1);
		System.out.println("Created Character: " + character1.getFirstName() + " " + character1.getLastName());

		Character retrievedCharacter = characterDao.getCharacterById(character1.getCharacterID());
		if(retrievedCharacter != null) {
			System.out.println("Retrieved Character by ID: " + retrievedCharacter.getFirstName() + " " + retrievedCharacter.getLastName());
		} else {
			System.out.println("Character with ID: " + character1.getCharacterID() + " not found.");
		}

		// Example for deleting a character
		boolean deleteSuccess = characterDao.deleteCharacter(character1.getCharacterID());
		if(deleteSuccess) {
			System.out.println("Deleted Character: " + character1.getFirstName() + " " + character1.getLastName());
		} else {
			System.out.println("Failed to delete Character: " + character1.getFirstName() + " " + character1.getLastName());
		}

		// Verification of deletion
		Character verifyDeletion = characterDao.getCharacterById(character1.getCharacterID());
		if(verifyDeletion == null) {
			System.out.println("Character " + character1.getFirstName() + " " + character1.getLastName() + " successfully deleted.");
		} else {
			System.out.println("Character " + character1.getFirstName() + " " + character1.getLastName() + " was not deleted properly.");
		}
	}

	public static void insertJobs(JobDao jobDao) throws SQLException {
		// Create jobs
		Job job1 = new Job("Warrior", 50);
		jobDao.create(job1);
		System.out.println("Created Job: " + job1.getName() + " with Level Cap: " + job1.getLevelCap());

		Job job2 = new Job("Mage", 50);
		jobDao.create(job2);
		System.out.println("Created Job: " + job2.getName() + " with Level Cap: " + job2.getLevelCap());

		// Update a Job's name and level cap
		job1.setName("Paladin");
		job1.setLevelCap(60);
		jobDao.updateJob(job1.getJobID(), job1.getName(), job1.getLevelCap());
		System.out.println("Updated Job ID: " + job1.getJobID() + " to Name: " + job1.getName() + " and Level Cap: " + job1.getLevelCap());

		// Fetch a job by ID
		Job retrievedJob = jobDao.getJobById(job2.getJobID());
		if(retrievedJob != null) {
			System.out.println("Retrieved Job by ID: " + retrievedJob.getJobID() + " Name: " + retrievedJob.getName() + " Level Cap: " + retrievedJob.getLevelCap());
		} else {
			System.out.println("Job with ID: " + job2.getJobID() + " not found.");
		}
	}

	public static void insertCharacterCurrencies(CharacterCurrencyDao characterCurrencyDao) throws SQLException {
		Player player1 = new Player("Link", "link@hyrule.com");
		Character character1 = new Character(player1,"Cloud", "Ava", 999, 999, null, null, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		Currency currency1 = new Currency("Gold", 10000, 500);

		CharacterCurrency characterCurrency1 = new CharacterCurrency(character1, currency1, 500, 50);
		characterCurrencyDao.create(characterCurrency1);
		System.out.println("Created CharacterCurrency: Character ID " + character1.getCharacterID() + ", Currency ID " + currency1.getCurrencyID() + ", Amount: " + characterCurrency1.getAmount());

		characterCurrencyDao.updateCharacterCurrencyAmount(character1.getCharacterID(), currency1.getCurrencyID(), 600, 60);
		System.out.println("Updated CharacterCurrency: New Amount: 600, New Amount Earned Week: 60");

		CharacterCurrency retrievedCharacterCurrency = characterCurrencyDao.getByCharacterIdAndCurrencyId(character1.getCharacterID(), currency1.getCurrencyID());
		if(retrievedCharacterCurrency != null) {
			System.out.println("Retrieved CharacterCurrency: " + "Character ID: " + retrievedCharacterCurrency.getCharacter().getCharacterID() + ", Currency ID: " + retrievedCharacterCurrency.getCurrency().getCurrencyID() + ", Amount: " + retrievedCharacterCurrency.getAmount() + ", Amount Earned This Week: " + retrievedCharacterCurrency.getAmountEarnedWeek());
		} else {
			System.out.println("CharacterCurrency not found for Character ID: " + character1.getCharacterID() + " and Currency ID: " + currency1.getCurrencyID());
		}
	}

	public static void insertCurrencies(CurrencyDao currencyDao) throws SQLException {
		Currency currency1 = new Currency("Gold", 10000, 500);
		currencyDao.create(currency1);
		System.out.println("Created Currency: " + currency1.getName() + " with Total Cap: " + currency1.getTotalCap() + " and Weekly Cap: " + currency1.getWeeklyCap());

		Currency currency2 = new Currency("Silver", 20000, 1000);
		currencyDao.create(currency2);
		System.out.println("Created Currency: " + currency2.getName() + " with Total Cap: " + currency2.getTotalCap() + " and Weekly Cap: " + currency2.getWeeklyCap());

		currencyDao.updateCurrency(currency1.getCurrencyID(), "Platinum", 5000, 250);
		System.out.println("Updated Currency ID: " + currency1.getCurrencyID() + " to Name: Platinum with new Total Cap: 5000 and new Weekly Cap: 250");

		Currency retrievedCurrency = currencyDao.getCurrencyById(currency2.getCurrencyID());
		if(retrievedCurrency != null) {
			System.out.println("Retrieved Currency by ID: " + retrievedCurrency.getCurrencyID() + " - Name: " + retrievedCurrency.getName() + ", Total Cap: " + retrievedCurrency.getTotalCap() + ", Weekly Cap: " + retrievedCurrency.getWeeklyCap());
		} else {
			System.out.println("Currency with ID: " + currency2.getCurrencyID() + " not found.");
		}
	}

	public static void insertGearSlots(GearSlotDao gearSlotDao) throws SQLException {
		for (GearSlot gearSlot : GearSlot.values()) {
			gearSlotDao.create(gearSlot);
			System.out.println("Inserted GearSlot: " + gearSlot.name());
		}

		String sampleTypeName = GearSlot.HEAD.name();
		GearSlot retrievedGearSlot = gearSlotDao.getGearSlotByTypeName(sampleTypeName);
		if (retrievedGearSlot != null) {
			System.out.println("Retrieved GearSlot by TypeName: " + retrievedGearSlot.name());
		} else {
			System.out.println("GearSlot with TypeName: " + sampleTypeName + " not found.");
		}

		List<GearSlot> allGearSlots = gearSlotDao.getAllGearSlots();
		System.out.println("All GearSlots: ");
		for (GearSlot slot : allGearSlots) {
			System.out.println(" - " + slot.name());
		}
	}

	public static void insertGearTypes(GearTypeDao gearTypeDao) throws SQLException {
		for (GearType gearType : GearType.values()) {
			gearTypeDao.create(gearType);
			System.out.println("Inserted GearType: " + gearType.name());
		}

		String sampleTypeName = GearType.ARMOR.name();
		GearType retrievedGearType = gearTypeDao.getGearTypeByTypeName(sampleTypeName);
		if (retrievedGearType != null) {
			System.out.println("Retrieved GearType by TypeName: " + retrievedGearType.name());
		} else {
			System.out.println("GearType with TypeName: " + sampleTypeName + " not found.");
		}

		List<GearType> allGearTypes = gearTypeDao.getAllGearTypes();
		System.out.println("All GearTypes:");
		allGearTypes.forEach(gt -> System.out.println(" - " + gt.name()));
	}

	public static void insertAndUpdateGearTypeJobs(GearTypeJobDao gearTypeJobDao) throws SQLException {
		JobDao jobDao = JobDao.getInstance();
		Job job1 = new Job("Warrior", 50);
		job1 = jobDao.create(job1);
		int jobId = job1.getJobID();
		Arrays Arrays = null;
		List<GearType> gearTypesToAssociate = Arrays.asList(GearType.HELMET, GearType.ARMOR);

		boolean added = gearTypeJobDao.addGearTypesForJobId(jobId, gearTypesToAssociate);
		if(added) {
			System.out.println("Gear types added for Job ID: " + jobId);
		} else {
			System.out.println("Failed to add gear types for Job ID: " + jobId);
		}

		List<GearType> retrievedGearTypes = gearTypeJobDao.getGearTypesByJobId(jobId);
		System.out.println("Retrieved Gear Types for Job ID " + jobId + ":");
		retrievedGearTypes.forEach(gearType -> System.out.println(" - " + gearType));

		boolean deleted = gearTypeJobDao.deleteGearTypesByJobId(jobId);
		if(deleted) {
			System.out.println("Gear types deleted for Job ID: " + jobId);
		} else {
			System.out.println("Failed to delete gear types for Job ID: " + jobId);
		}

		List<GearType> updatedGearTypes = Arrays.asList(GearType.WEAPON, GearType.BOOTS);
		boolean updated = gearTypeJobDao.addGearTypesForJobId(jobId, updatedGearTypes);
		if(updated) {
			System.out.println("Updated Gear Types for Job ID " + jobId + ":");
			updatedGearTypes.forEach(gearType -> System.out.println(" - " + gearType));
		} else {
			System.out.println("Failed to update gear types for Job ID: " + jobId);
		}
	}

	public static void insertAndManageEquippedGear(CharacterEquippedGearDao equippedGearDao) throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		Player player1 = new Player("Link", "link@hyrule.com");
		player1 = playerDao.create(player1);
		CharacterDao characterDao = CharacterDao.getInstance();
		Character character1 = new Character(player1,"Cloud", "Ava", 999, 999, null, null, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		character1 = characterDao.create(character1);
		int characterId = character1.getCharacterID();

		GearDao gearDao = GearDao.getInstance();
		Gear gear1 = new Gear(5, "Leather Eyepatch", 1, true, 12, "Light", "Head", 10, 20, 10);
		gear1.setVendorPrice(250.00);
		gear1.setStrengthBonus(5);
		gear1.setVitalityBonus(0);
		gear1.setCriticalHitBonus(2);
		gear1.setDeterminationBonus(3);
		gear1.setDexterityBonus(0);
		gear1.setMindBonus(0);
		gear1.setTenacityBonus(5);
		gear1.setSkillSpeedBonus(0);
		gear1.setCriticalHitRateBonus(0);
		gearDao.create(gear1);

		int initialGearId = gear1.getItemID();

		String slotName = "Head"; // Example slot name

		// Inserting a new equipped gear record
		CharacterEquippedGear newEquippedGear = new CharacterEquippedGear(slotName, characterId, initialGearId);
		equippedGearDao.create(newEquippedGear);
		System.out.println("Equipped new gear: " + initialGearId + " in slot: " + slotName + " for character ID: " + characterId);

		// Updating equipped gear for a slot
		Gear gear2 = new Gear(7, "Iron Chestplate", 1, true, 15, "Heavy", "Body", 25, 15, 5);
		gear2.setVendorPrice(30.00);
		gear2.setStrengthBonus(0);
		gear2.setVitalityBonus(3);
		gear2.setCriticalHitBonus(0);
		gear2.setDeterminationBonus(2);
		gear2.setDexterityBonus(0);
		gear2.setMindBonus(0);
		gear2.setTenacityBonus(0);
		gear2.setSkillSpeedBonus(0);
		gear2.setCriticalHitRateBonus(0);
		gearDao.create(gear2);
		int newGearId = gear2.getItemID();
		boolean updated = equippedGearDao.updateCharacterEquippedGear(characterId, slotName, newGearId);
		if(updated) {
			System.out.println("Updated gear in slot: " + slotName + " to new gear ID: " + newGearId + " for character ID: " + characterId);
		} else {
			System.out.println("Failed to update gear in slot: " + slotName + " for character ID: " + characterId);
		}

		// Fetching updated equipped gear by character ID and slot name
		CharacterEquippedGear fetchedEquippedGear = equippedGearDao.getByCharacterIdAndSlotName(characterId, slotName);
		if(fetchedEquippedGear != null) {
			System.out.println("Fetched equipped gear ID: " + fetchedEquippedGear.getGearID() + " in slot: " + fetchedEquippedGear.getSlotName() + " for character ID: " + fetchedEquippedGear.getCharacterID());
		} else {
			System.out.println("No equipped gear found in slot: " + slotName + " for character ID: " + characterId);
		}
	}

	public static void insertAndUpdateInventory(CharacterInventoryDao inventoryDao) throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		Player player1 = new Player("Link", "link@hyrule.com");
		player1 = playerDao.create(player1);
		CharacterDao characterDao = CharacterDao.getInstance();
		Character character1 = new Character(player1, "Cloud", "Ava", 999, 999, null, null, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		character1 = characterDao.create(character1);

		ItemDao itemDao = ItemDao.getInstance();

		Item item1 = new Item("Sword of Might", 1, true, 10);
		item1.setVendorPrice(200.00);
		itemDao.create(item1);
		int itemId = item1.getItemID();
		int initialStackSize = 10;

		CharacterInventory newInventory = new CharacterInventory(character1);
		newInventory = inventoryDao.create(newInventory);
		System.out.println("New inventory slot created with Inventory Slot ID: " + newInventory.getInventorySlotID());

		// Step 2: Update the newly created inventory record with ItemID and StackSize
		boolean updateSuccess = inventoryDao.updateInventoryItemAndSize(newInventory.getInventorySlotID(), itemId, initialStackSize);
		if (updateSuccess) {
			System.out.println("Inventory updated successfully with Item ID: " + itemId + " and Stack Size: " + initialStackSize);
		} else {
			System.out.println("Failed to update the inventory.");
		}

		// Step 3: Fetch the updated inventory record to verify
		CharacterInventory updatedInventory = inventoryDao.getByInventorySlotID(newInventory.getInventorySlotID());
		if (updatedInventory != null && updatedInventory.getItem() != null) {
			System.out.println("Fetched updated inventory: Item ID = " + updatedInventory.getItem().getItemID() +
					", Stack Size = " + updatedInventory.getStackSize());
		} else {
			System.out.println("Updated inventory not found or item details missing.");
		}
	}

	public static void insertAndUpdateCharacterJob(CharacterJobDao characterJobDao) throws SQLException {
		PlayerDao playerDao = PlayerDao.getInstance();
		Player player1 = new Player("Link", "link@hyrule.com");
		player1 = playerDao.create(player1);
		CharacterDao characterDao = CharacterDao.getInstance();
		Character character1 = new Character(player1, "Cloud", "Ava", 999, 999, null, null, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
		character1 = characterDao.create(character1);

		int characterId = character1.getCharacterID();

		JobDao jobdao = JobDao.getInstance();
		Job job1 = new Job("Warrior", 50);
		jobdao.create(job1);
		int jobId = job1.getJobID();
		int initialLevel = 10; // Initial level for the job

		CharacterJob newCharacterJob = new CharacterJob(character1, job1, initialLevel);
		newCharacterJob = characterJobDao.create(newCharacterJob);
		System.out.println("CharacterJob association created with Character ID: " + characterId + ", Job ID: " + jobId + ", Level: " + initialLevel);

		// Step 2: Update the level of the character's job
		int newLevel = 20; // New level to update to
		CharacterJob updatedCharacterJob = characterJobDao.updateCharacterJobLevel(characterId, jobId, newLevel);
		if(updatedCharacterJob != null) {
			System.out.println("CharacterJob level updated to: " + newLevel + " for Character ID: " + characterId + " and Job ID: " + jobId);
		} else {
			System.out.println("Failed to update CharacterJob level.");
		}

		// Step 3: Fetch the updated CharacterJob association to verify
		CharacterJob fetchedCharacterJob = characterJobDao.getByCharacterIdAndJobId(characterId, jobId);
		if(fetchedCharacterJob != null) {
			System.out.println("Fetched CharacterJob: Character ID = " + fetchedCharacterJob.getCharacter().getCharacterID() +
					", Job ID = " + fetchedCharacterJob.getJob().getJobID() + ", Level = " + fetchedCharacterJob.getLevel());
		} else {
			System.out.println("CharacterJob not found.");
		}
	}

	public static void manageWeaponTypes(WeaponTypeDao weaponTypeDao) {
		// Example: Insert Weapon Types
		try {
			for (WeaponType type : WeaponType.values()) {
				WeaponType inserted = weaponTypeDao.create(type);
				System.out.println("Inserted weapon type: " + inserted.name());
			}
		} catch (SQLException e) {
			System.err.println("Error inserting weapon types: " + e.getMessage());
			e.printStackTrace();
		}

		// Example: Fetch a Specific Weapon Type
		String typeNameToFetch = "SWORD"; // Assuming 'SWORD' is a valid entry in your enum
		try {
			WeaponType fetchedType = weaponTypeDao.getWeaponTypeByName(typeNameToFetch);
			if (fetchedType != null) {
				System.out.println("Successfully fetched weapon type: " + fetchedType.name());
			} else {
				System.out.println("Weapon type not found: " + typeNameToFetch);
			}
		} catch (SQLException e) {
			System.err.println("Error fetching weapon type: " + e.getMessage());
			e.printStackTrace();
		}

		// Example: List All Weapon Types
		try {
			List<WeaponType> allTypes = weaponTypeDao.getAllWeaponTypes();
			System.out.println("All weapon types:");
			allTypes.forEach(type -> System.out.println(type.name()));
		} catch (Exception e) {
			System.err.println("Error listing all weapon types.");
			e.printStackTrace();
		}
	}
	public static void manageWeaponTypesForJobs(WeaponTypeJobDao weaponTypeJobDao) throws SQLException {
		JobDao jobDao = JobDao.getInstance();
		Job job1 = new Job("Warrior", 50);
		job1 = jobDao.create(job1);
		int jobId = job1.getJobID();
		List<WeaponType> weaponTypesForJob = new ArrayList<>();
		weaponTypesForJob.add(WeaponType.SWORD);
		weaponTypesForJob.add(WeaponType.DAGGER);

		// Insert weapon types for the job
		boolean insertSuccess = weaponTypeJobDao.addWeaponTypesForJobId(jobId, weaponTypesForJob);
		System.out.println("Insertion of weapon types for job successful: " + insertSuccess);

		// Fetch and display weapon types for the job
		List<WeaponType> fetchedWeaponTypes = weaponTypeJobDao.getWeaponTypesByJobId(jobId);
		System.out.println("Fetched weapon types for job ID " + jobId + ":");
		for (WeaponType wt : fetchedWeaponTypes) {
			System.out.println(wt.name());
		}
	}

	public static void main(String[] args) {
		try {
			PlayerDao playerDao = PlayerDao.getInstance();
			CharacterDao characterDao = CharacterDao.getInstance();
			JobDao jobDao = JobDao.getInstance();
			CharacterCurrencyDao characterCurrencyDao = CharacterCurrencyDao.getInstance();
			CurrencyDao currencyDao = CurrencyDao.getInstance();
			GearSlotDao gearSlotDao = GearSlotDao.getInstance();
			GearTypeDao gearTypeDao = GearTypeDao.getInstance();
			GearTypeJobDao gearTypeJobDao = GearTypeJobDao.getInstance();
			CharacterJobDao characterJobDao = CharacterJobDao.getInstance();
			WeaponTypeDao weaponTypeDao = WeaponTypeDao.getInstance();
			CharacterInventoryDao characterInventoryDao = CharacterInventoryDao.getInstance();
			WeaponTypeJobDao weaponTypeJobDao = WeaponTypeJobDao.getInstance();
			CharacterEquippedGearDao characterEquippedGearDao = CharacterEquippedGearDao.getInstance();

			// Insert data
			insertPlayers(playerDao);
			insertCharacters(characterDao);
			insertJobs(jobDao);
			insertCharacterCurrencies(characterCurrencyDao);
			insertCurrencies(currencyDao);
			insertGearSlots(gearSlotDao);
			insertGearTypes(gearTypeDao);
			insertAndUpdateGearTypeJobs(gearTypeJobDao);
			insertAndManageEquippedGear(characterEquippedGearDao);
			insertAndUpdateInventory(characterInventoryDao);
			insertAndUpdateCharacterJob(characterJobDao);
			manageWeaponTypes(weaponTypeDao);
			manageWeaponTypesForJobs(weaponTypeJobDao);

		} catch (SQLException e) {
			System.err.println("SQL Exception Error: " + e.getMessage());
			e.printStackTrace();
		}
	}


}

