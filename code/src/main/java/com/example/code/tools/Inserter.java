package com.example.code.tools;

import com.example.code.dao.CharacterEquippedGearDao;
import com.example.code.dao.GearDao;
import com.example.code.dao.ItemDao;
import com.example.code.dao.WeaponDao;
import com.example.code.model.Item;
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

  public static void main(String[] args) throws SQLException{
    ItemDao itemDao = ItemDao.getInstance();
    GearDao gearDao = GearDao.getInstance();
    WeaponDao weaponDao = WeaponDao.getInstance();
    CharacterEquippedGearDao characterEquippedGearDao = CharacterEquippedGearDao.getInstance();

    insertItems(itemDao);

  }
}
