package pm3.model;

public class CharacterInventory {
    protected int inventorySlotID;
    protected Character character;
    protected Item item;
    protected int stackSize;

    public CharacterInventory(int inventorySlotID) {
        this.inventorySlotID = inventorySlotID;
    }

    public int getInventorySlotID() {
        return inventorySlotID;
    }

    public void setInventorySlotID(int inventorySlotID) {
        this.inventorySlotID = inventorySlotID;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }
}
