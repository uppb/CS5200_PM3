package pm3.model;

public class Consumable extends Item{
  protected String Description;
  protected Double TenactiyBonus;
  protected Double VitalityBonus;
  protected Double DeterminationBonus;
  protected Integer TenacityCap;
  protected Integer VitalityCap;
  protected Integer DeterminationCap;

  public Consumable(Integer itemID, String name, Integer maxStackSize, Boolean forSale,
      Integer itemLevel, String description) {
    super(itemID, name, maxStackSize, forSale, itemLevel);
    Description = description;
  }

  public Consumable(String name, Integer maxStackSize, Boolean forSale, Integer itemLevel,
      String description) {
    super(name, maxStackSize, forSale, itemLevel);
    Description = description;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public Double getTenactiyBonus() {
    return TenactiyBonus;
  }

  public void setTenactiyBonus(Double tenactiyBonus) {
    TenactiyBonus = tenactiyBonus;
  }

  public Double getVitalityBonus() {
    return VitalityBonus;
  }

  public void setVitalityBonus(Double vitalityBonus) {
    VitalityBonus = vitalityBonus;
  }

  public Double getDeterminationBonus() {
    return DeterminationBonus;
  }

  public void setDeterminationBonus(Double determinationBonus) {
    DeterminationBonus = determinationBonus;
  }

  public Integer getTenacityCap() {
    return TenacityCap;
  }

  public void setTenacityCap(Integer tenacityCap) {
    TenacityCap = tenacityCap;
  }

  public Integer getVitalityCap() {
    return VitalityCap;
  }

  public void setVitalityCap(Integer vitalityCap) {
    VitalityCap = vitalityCap;
  }

  public Integer getDeterminationCap() {
    return DeterminationCap;
  }

  public void setDeterminationCap(Integer determinationCap) {
    DeterminationCap = determinationCap;
  }
}
