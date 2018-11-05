package MainProject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * PokemonEssentials class meant for use in a pokedex.
 *  Pulls data from a relational database, and contains known data about pokemon. Contains no
 *  Getters as a result. Does not contain all data for a PokemonEssentials, may include more if time allows
 */
public class PokemonData implements PokemonEssentials, PokemonDescription {
  private String speciesName;
  private int speciesID;
  private double height;
  private double weight;
  private int[] baseStats = new int[6];
  private ArrayList<Ability> abilities;
  private ArrayList<String> eggGroups;
  private ArrayList<String> types;
  private ArrayList<PokemonMove> moves;

  ///////////////////////////////////// Constructor /////////////////////////////////////
  /**
   * Constructor class based on the ID number
   * Fills the pokemon object with the data for that species
   * @param speciesID
   * @param speciesName
   * @param height
   * @param weight
   * @param baseStats
   * @param abilities
   * @param eggGroups
   * @param types
   * @param moves
   */
  public PokemonData(int speciesID, String speciesName, double height, double weight,
      int[] baseStats, ArrayList<Ability> abilities, ArrayList<String> eggGroups,
      ArrayList<String> types, ArrayList<PokemonMove> moves) {
    this.speciesID = speciesID;
    this.speciesName = speciesName;
    this.height = height;
    this.weight = weight;
    this.baseStats = baseStats;
    this.abilities = abilities;
    this.eggGroups = eggGroups;
    this.types = types;
    this.moves = moves;
  }

  ///////////////////////////////////// Accessors /////////////////////////////////////

  public String getSpeciesName() {
    return speciesName;
  }

  public int getSpeciesID() {
    return speciesID;
  }

  public int[] getBaseStats() {
    return baseStats;
  }

  public ArrayList<Ability> getAbilities() {
    return abilities;
  }

  public double getHeight() {
    return height;
  }

  public double getWeight() {
    return weight;
  }

  public ArrayList<String> getEggGroups() {
    return eggGroups;
  }

  public ArrayList<String> getTypes() {
    return types;
  }

  public ArrayList<PokemonMove> getMoves() {
    return moves;
  }
}