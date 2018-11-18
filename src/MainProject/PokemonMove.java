package MainProject;

public class PokemonMove{
  private int moveID;
  private String moveIdentifier;
  private String type;
  private String methodLearned;
  private int levelLearned;
  private int power;
  private int pp;
  private int accuracy;
  private int priority;
  private String targetIdentifier;
  private String damageClassDescription;
  private int effectChance;
  private String effectDescription;

  /**
   * The basic outline of a move. Currently mostly simple, but with some Capitalization and cleaning
   * up some text
   * @param moveID - int - the id number for the move
   * @param moveIdentifier - String - the name of the move
   * @param type - String - the type of the move
   * @param methodLearned - String - how the pokemon learns this particular move
   * @param levelLearned - int - what level the pokemon learns this move, if they learn by level-up
   * @param power - int - base power of the move
   * @param pp - int - how many times the move can be used in a single battle
   * @param accuracy - int - the chance the move has to hit
   * @param priority - int - the order the move is used in battle, -6 is last to move, 6 is first
   * @param targetIdentifier - String - what pokemon can be targeted by this move
   * @param damageClassDescription - String - whether the move is Physical, Special, or non-damaging
   * @param effectChance - int - chance for the effect to occur
   * @param effectDescription - String - what the effect does
   */
  PokemonMove(int moveID, String moveIdentifier, String type, String methodLearned,
      int levelLearned, int power, int pp, int accuracy, int priority, String targetIdentifier,
      String damageClassDescription, int effectChance, String effectDescription) {
    this.moveID = moveID;
    if (moveIdentifier.contains("-")){
      this.moveIdentifier = moveIdentifier.substring(0, 1).toUpperCase() +                    //Just capitalizing words
          moveIdentifier.substring(1, moveIdentifier.indexOf('-')+1) +
          moveIdentifier.substring(moveIdentifier.indexOf('-')+1,
              moveIdentifier.indexOf('-')+2).toUpperCase() +
          moveIdentifier.substring(moveIdentifier.indexOf('-') + 2);
    }else {
      this.moveIdentifier = (moveIdentifier.substring(0, 1).toUpperCase() +                   //More Capitalizing
          moveIdentifier.substring(1));
    }
    this.type = type;
    this.methodLearned = methodLearned;
    this.levelLearned = levelLearned;
    this.power = power;
    this.pp = pp;
    this.accuracy = accuracy;
    this.priority = priority;
    this.targetIdentifier = targetIdentifier;
    this.damageClassDescription = damageClassDescription;
    this.effectChance = effectChance;
    this.effectDescription = effectDescription.replace("$effect_chance",                //The effect in the move description is not in the database, so i add it in here
        Integer.toString(effectChance));
  }

  /**
   * Simple accessor
   * @return - int - ID number of the move
   */
  public int getMoveID() {
    return moveID;
  }

  /**
   * Simple accessor
   * @return - String - identifier of the move
   */
  public String getMoveIdentifier() {
    return moveIdentifier;
  }

  /**
   * Simple accessor
   * @return - String - type of the move
   */
  public String getType() {
    return type;
  }

  /**
   * Simple accessor
   * @return - String - how the move is learned
   */
  public String getMethodLearned() {
    return methodLearned;
  }

  /**
   * Simple accessor
   * @return - int - what level the move is learned
   */
  public int getLevelLearned() {
    return levelLearned;
  }

  /**
   * Simple accessor
   * @return - int - the power of the move
   */
  public int getPower() {
    return power;
  }

  /**
   * Simple accessor
   * @return - int - gets the base PP for the move
   */
  public int getPp() {
    return pp;
  }

  /**
   * Simple accessor
   * @return - int - base accuracy for the move
   */
  public int getAccuracy() {
    return accuracy;
  }

  /**
   * Simple accessor
   * @return - int - priority for the move, ranges from -6 to 6, 6 being top priority
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Simple accessor
   * @return - String - what targets are hit by a move
   */
  public String getTargetIdentifier() {
    return targetIdentifier;
  }

  /**
   * Simple accessor
   * @return - String - what type of damage the move does
   */
  public String getDamageClassDescription() {
    return damageClassDescription;
  }

  /**
   * Simple accessor
   * @return - int - the chance for the effect to occur
   */
  public int getEffectChance() {
    return effectChance;
  }

  /**
   * Simple accessor
   * @return - String - what effect does this move have on the target
   */
  public String getEffectDescription() {
    return effectDescription;
  }
}