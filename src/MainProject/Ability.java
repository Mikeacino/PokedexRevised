package MainProject;

public class Ability {
  private int abilityID;
  private String abilityName;
  public String abilityDescription;

  Ability(int abilityID, String abilityName, String abilityDescription){
    this.abilityID = abilityID;
    this.abilityName = abilityName;
    this.abilityDescription = abilityDescription;
  }

  public int getAbilityID() {
    return abilityID;
  }

  public String getAbilityDescription() {
    return abilityDescription;
  }

  public String getAbilityName() {
    return abilityName;
  }
}