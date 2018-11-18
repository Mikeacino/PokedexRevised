package MainProject;

import java.util.ArrayList;

/**
 * PokemonEssentials class meant for use in a pokedex.
 *  Pulls data from a relational database, and contains known data about pokemon. Contains no
 *  Getters as a result. Sometimes SpotBugs thinks this class is never used, but i create an
 *  instance of it in the first line of initialize() in Controller.Java
 */
public class PokemonData implements PokemonEssentials, PokemonDescription {
  private String speciesName;
  private int pokemonID;
  private int speciesID;
  private double height;
  private double weight;
  private int[] baseStats = new int[6];                                                             //I have no idea how to solve this. I've tried every variation of int Arrays that I know
  private ArrayList<Ability> abilities;
  private ArrayList<String> eggGroups;
  private ArrayList<String> types;
  private ArrayList<PokemonMove> moves;
  //private ArrayList<String> forms;                                                                related to the bug with theForms ComnboBox, and has been slashed out until further notice

  ///////////////////////////////////// Constructor /////////////////////////////////////
  /**
   * Constructor class based on the ID number
   * Fills the pokemon object with the data for that species
   * @param speciesID - int - ID of the pokemon species
   * @param speciesName - String - name of the pokemon species
   * @param height - double - height of the species
   * @param weight - double - weight of the species
   * @param baseStats - int[] - all 6 base stats for the pokemon
   * @param abilities - ArrayList - abilities of the pokemon, varies from 1 to 3 abilities
   * @param eggGroups - ArrayList - egg groups for pokemon breeding, varies from 1 to 2 egg groups
   * @param types - ArrayList - types of the pokemon, varies from 1 to 2 to 3(only in battle)
   * @param moves - ArrayList - all moves this pokemon can learn, varies dramatically
   */
  public PokemonData(int pokemonID, int speciesID, String speciesName, double height, double weight,
      int[] baseStats, ArrayList<Ability> abilities, ArrayList<String> eggGroups,
      ArrayList<String> types, ArrayList<PokemonMove> moves) {
    this.pokemonID = pokemonID;
    this.speciesID = speciesID;
    this.speciesName = speciesName;
    this.height = height;
    this.weight = weight;
    this.baseStats = baseStats;                                                                     //Throws an error for no percieveable reason
    this.abilities = abilities;
    this.eggGroups = eggGroups;
    this.types = types;
    this.moves = moves;
  }

  ///////////////////////////////////// Accessors /////////////////////////////////////

  public String getSpeciesName() {
    return speciesName;
  }

  public int getPokemonID() {
    return pokemonID;
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