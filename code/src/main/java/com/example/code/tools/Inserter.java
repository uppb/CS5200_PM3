package com.example.code.tools;

import com.example.code.dao.CharacterEquippedGearDao;
import com.example.code.dao.GearDao;
import com.example.code.dao.ItemDao;
import com.example.code.dao.WeaponDao;
import com.example.code.dao.ConsumableDao;
import com.example.code.model.CharacterEquippedGear;
import com.example.code.model.Consumable;
import com.example.code.model.Gear;
import com.example.code.model.Item;
import com.example.code.model.Weapon;
import java.sql.SQLException;

public class Inserter {
  public static void insertItems(ItemDao itemDao) throws SQLException{
    // Create the items with their respective details
    Item item1 = new Item("Sword of Might", 1, true, 10);
    item1.setVendorPrice(200.00);
    itemDao.create(item1);

    Item item2 = new Item("Staff of Wisdom", 1, true, 8);
    item2.setVendorPrice(150.00);
    itemDao.create(item2);

    Item item3 = new Item("Healing Potion", 99, true, 0);
    item3.setVendorPrice(5.00);
    itemDao.create(item3);

    Item item4 = new Item("Mana Elixir", 99, true, 0);
    item4.setVendorPrice(10.00);
    itemDao.create(item4);

    Item item5 = new Item("Leather Eyepatch", 1, true, 12);
    item5.setVendorPrice(250.00);
    itemDao.create(item5);

    Item item6 = new Item("Giant Popoto Pancakes", 99, true, 20);
    item6.setVendorPrice(30.00);
    itemDao.create(item6);

    Item item7 = new Item("Iron Chestplate", 1, true, 15);
    item7.setVendorPrice(30.00);
    itemDao.create(item7);

    Item item8 = new Item("Silk Pants", 1, true, 20);
    item8.setVendorPrice(25.00);
    itemDao.create(item8);

    Item item9 = new Item("Leather Boots", 1, true, 25);
    item9.setVendorPrice(15.00);
    itemDao.create(item9);

    Item item10 = new Item("Leather Gloves", 1, true, 30);
    item10.setVendorPrice(10.00);
    itemDao.create(item10);

    Item item11 = new Item("Feather Earrings", 1, true, 35);
    item11.setVendorPrice(5.00);
    itemDao.create(item11);

    Item item12 = new Item("Silver Bracers", 1, true, 45);
    item12.setVendorPrice(10.00);
    itemDao.create(item12);

    Item item13 = new Item("Brass Dagger", 1, true, 20);
    item13.setVendorPrice(15.00);
    itemDao.create(item13);

    Item item14 = new Item("Ranger's Hat", 1, true, 40);
    item14.setVendorPrice(30.00);
    itemDao.create(item14);

    Item tmp = new Item("TO BE DELETED", 15, false, 40);
    itemDao.create(tmp);

    Item retrieved_item = itemDao.getItemByID(item5.getItemID());
    System.out.format("Reading item: n:%s \n", retrieved_item.getName());

    Item deleted_tmp = itemDao.delete(tmp);
    if(deleted_tmp == null) {
      System.out.format("Deleting item: n:%s \n", tmp.getName());
    }
  }

  public static void insertGear(GearDao gearDao) throws SQLException{
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

    Gear gear3 = new Gear(8, "Silk Pants", 1, true, 20, "Light", "Legs", 20, 25, 15);
    gear3.setVendorPrice(25.00);
    gear3.setStrengthBonus(0);
    gear3.setVitalityBonus(5);
    gear3.setCriticalHitBonus(0);
    gear3.setDeterminationBonus(4);
    gear3.setDexterityBonus(0);
    gear3.setMindBonus(0);
    gear3.setTenacityBonus(0);
    gear3.setSkillSpeedBonus(0);
    gear3.setCriticalHitRateBonus(0);
    gearDao.create(gear3);

    Gear gear4 = new Gear(9, "Leather Boots", 1, true, 25, "Medium", "Feet", 15, 30, 20);
    gear4.setVendorPrice(15.00);
    gear4.setStrengthBonus(0);
    gear4.setVitalityBonus(10);
    gear4.setCriticalHitBonus(0);
    gear4.setDeterminationBonus(5);
    gear4.setDexterityBonus(0);
    gear4.setMindBonus(0);
    gear4.setTenacityBonus(0);
    gear4.setSkillSpeedBonus(0);
    gear4.setCriticalHitRateBonus(0);
    gearDao.create(gear4);

    Gear gear5 = new Gear(10, "Leather Gloves", 1, true, 30, "Medium", "Hands", 30, 10, 5);
    gear5.setVendorPrice(10.00);
    gear5.setStrengthBonus(2);
    gear5.setVitalityBonus(2);
    gear5.setCriticalHitBonus(0);
    gear5.setDeterminationBonus(3);
    gear5.setDexterityBonus(0);
    gear5.setMindBonus(0);
    gear5.setTenacityBonus(0);
    gear5.setSkillSpeedBonus(0);
    gear5.setCriticalHitRateBonus(0);
    gearDao.create(gear5);

    Gear gear6 = new Gear(11, "Feather Earrings", 1, true, 35, "Light", "Earring", 5, 5, 5);
    gear6.setVendorPrice(5.00);
    gear6.setStrengthBonus(1);
    gear6.setVitalityBonus(3);
    gear6.setCriticalHitBonus(0);
    gear6.setDeterminationBonus(1);
    gear6.setDexterityBonus(0);
    gear6.setMindBonus(0);
    gear6.setTenacityBonus(0);
    gear6.setSkillSpeedBonus(0);
    gear6.setCriticalHitRateBonus(0);
    gearDao.create(gear6);

    Gear gear7 = new Gear(12, "Silver Bracers", 1, true, 45, "Light", "Wrist", 10, 20, 10);
    gear7.setVendorPrice(10.00);
    gear7.setStrengthBonus(0);
    gear7.setVitalityBonus(5);
    gear7.setCriticalHitBonus(1);
    gear7.setDeterminationBonus(2);
    gear7.setDexterityBonus(0);
    gear7.setMindBonus(0);
    gear7.setTenacityBonus(0);
    gear7.setSkillSpeedBonus(0);
    gear7.setCriticalHitRateBonus(0);
    gearDao.create(gear7);

    Gear gear8 = new Gear(14, "Ranger's Hat", 1, true, 40, "Heavy", "Head", 10, 10, 5);
    gear8.setVendorPrice(30.00);
    gear8.setStrengthBonus(1);
    gear8.setVitalityBonus(2);
    gear8.setCriticalHitBonus(0);
    gear8.setDeterminationBonus(2);
    gear8.setDexterityBonus(0);
    gear8.setMindBonus(0);
    gear8.setTenacityBonus(0);
    gear8.setSkillSpeedBonus(0);
    gear8.setCriticalHitRateBonus(0);

    gearDao.create(gear8);

    Gear retrieved_gear = gearDao.getGearByID(12);
    System.out.format("Reading gear: n:%s t:%s s:%s\n", retrieved_gear.getName(),
        retrieved_gear.getGearType(), retrieved_gear.getEquippableSlot());

  }

  public static void insertWeapon(WeaponDao weaponDao) throws SQLException{
    Weapon weapon1 = new Weapon(1, "Sword of Might", 1, true, 10,
        "Sword", 10, 50, 25.00,1.5);
    weapon1.setVendorPrice(200.00);
    weapon1.setStrengthBonus(5);
    weapon1.setVitalityBonus(0);
    weapon1.setDeterminationBonus(3);
    weapon1.setDirectHitRateBonus(2);
    weapon1.setSkillSpeedBonus(0);
    weapon1.setTenacityBonus(0);
    weapon1.setCriticalHitBonus(2);
    weaponDao.create(weapon1);

    Weapon weapon2 = new Weapon(2, "Staff of Wisdom", 1, true, 8,
        "Staff", 15, 40, 20.00,2.0);
    weapon2.setVendorPrice(150.00);
    weapon2.setStrengthBonus(0);
    weapon2.setVitalityBonus(5);
    weapon2.setDeterminationBonus(4);
    weapon2.setDirectHitRateBonus(1);
    weapon2.setSkillSpeedBonus(0);
    weapon2.setTenacityBonus(0);
    weapon2.setCriticalHitBonus(0);
    weaponDao.create(weapon2);

    Weapon weapon3 = new Weapon(13, "Brass Dagger", 1, true, 20,
        "Dagger", 20, 60, 30.00,3.0);
    weapon3.setVendorPrice(15.00);
    weapon3.setStrengthBonus(10);
    weapon3.setVitalityBonus(0);
    weapon3.setDeterminationBonus(0);
    weapon3.setDirectHitRateBonus(5);
    weapon3.setSkillSpeedBonus(0);
    weapon3.setTenacityBonus(0);
    weapon3.setCriticalHitBonus(3);
    weaponDao.create(weapon3);

    Weapon retrieved_weapon = weaponDao.getWeaponByID(13);
    System.out.format("Reading weapon: n:%s t:%s \n", retrieved_weapon.getName(),
        retrieved_weapon.getWeaponType());
  }

  public static void insertConsumable(ConsumableDao consumableDao) throws SQLException{
    Consumable consumable1 = new Consumable(3, "Healing Potion", 99,
        true, 0, "Restores 100 HP");
    consumable1.setVendorPrice(5.00);
    consumableDao.create(consumable1);

    Consumable consumable2 = new Consumable(4, "Mana Elixir", 99,
        true, 0, "Restores 50 Mana");
    consumable2.setVendorPrice(10.00);
    consumableDao.create(consumable2);

    Consumable retrieved_consumable = consumableDao.getConsumableByID(4);
    System.out.format("Reading consumable: n:%s d:%s \n", retrieved_consumable.getName(),
        retrieved_consumable.getDescription());
  }

  public static void insertCharacterEquippedGear(CharacterEquippedGearDao characterEquippedGearDao) throws SQLException{
    CharacterEquippedGear characterEquippedGear1 = new CharacterEquippedGear("Head", 1, 14);
    characterEquippedGearDao.create(characterEquippedGear1);

    CharacterEquippedGear characterEquippedGear2 = new CharacterEquippedGear("Body", 1, 7);
    characterEquippedGearDao.create(characterEquippedGear2);

    CharacterEquippedGear characterEquippedGear3 = new CharacterEquippedGear("Feet", 1, 9);
    characterEquippedGearDao.create(characterEquippedGear3);

    CharacterEquippedGear characterEquippedGear4 = new CharacterEquippedGear("Hands", 1, 10);
    characterEquippedGearDao.create(characterEquippedGear4);

    CharacterEquippedGear characterEquippedGear5 = new CharacterEquippedGear("Head", 2, 5);
    characterEquippedGearDao.create(characterEquippedGear5);

    CharacterEquippedGear characterEquippedGear6 = new CharacterEquippedGear("Legs", 2, 8);
    characterEquippedGearDao.create(characterEquippedGear6);

    CharacterEquippedGear characterEquippedGear7 = new CharacterEquippedGear("Wrist", 2, 12);
    characterEquippedGearDao.create(characterEquippedGear7);

    CharacterEquippedGear characterEquippedGear8 = new CharacterEquippedGear("Earring", 2, 11);
    characterEquippedGearDao.create(characterEquippedGear8);

    CharacterEquippedGear retrieved_characterEquippedGear =
        characterEquippedGearDao.getCharacterEquippedGearBySlotNameAndID("Feet", 10);
    System.out.format("Reading Character Equipped Gear: s:%s c_id:%d g_id:%d\n",
        retrieved_characterEquippedGear.getSlotName(), retrieved_characterEquippedGear.getCharacterID(),
        retrieved_characterEquippedGear.getGearID());
  }
  public static void main(String[] args) throws SQLException{
    ItemDao itemDao = ItemDao.getInstance();
    GearDao gearDao = GearDao.getInstance();
    WeaponDao weaponDao = WeaponDao.getInstance();
    ConsumableDao consumableDao = ConsumableDao.getInstance();
    CharacterEquippedGearDao characterEquippedGearDao = CharacterEquippedGearDao.getInstance();

    insertItems(itemDao);
    insertGear(gearDao);
    insertWeapon(weaponDao);
    insertConsumable(consumableDao);
    insertCharacterEquippedGear(characterEquippedGearDao);
  }
}
