package MainProject;

public class Ability {
  private int abilityID;
  private String abilityName;
  private String abilityDescription;

  /**
   * The basic outline of an ability. This needed to be a class to reduce queries. This class may be
   * extended to add more functionality.
   * @param abilityID - int - the id number for the ability, not in-game
   * @param abilityName - String - the name of the ability
   * @param abilityDescription - String - a description of the ability
   */
  Ability(int abilityID, String abilityName, String abilityDescription){
    //this.abilityID = abilityID;   //currently unused, but will be useful in the future for sorting
    this.abilityName = abilityName;
    this.abilityDescription = abilityDescription;
  }

  /**
   * Simple Accessor
   * @return - String - the description of this ability, requires UTF-8 to properly render
   */
  public String getAbilityDescription() {
    return abilityDescription;
  }

  /**
   * Simple Accessor
   * @return - String - the name of the ability
   */
  public String getAbilityName() {
    return abilityName;
  }
}