package pm3.model;

public class CharacterEquippedGear {
    protected GearSlot gearSlot;
    protected Character character;
    protected Gear gear;

    public CharacterEquippedGear(GearSlot gearSlot, Character character) {
        this.gearSlot = gearSlot;
        this.character = character;
    }

    public CharacterEquippedGear(Gear gear) {
        this.gear = gear;
    }

    public CharacterEquippedGear(GearSlot gearSlot, Character character, Gear gear) {
        this.gearSlot = gearSlot;
        this.character = character;
        this.gear = gear;
    }

    public GearSlot getGearSlot() {
        return gearSlot;
    }

    public void setGearSlot(GearSlot gearSlot) {
        this.gearSlot = gearSlot;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }
}
