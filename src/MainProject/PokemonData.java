package MainProject;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
//import java.util.Set;

/**
 * Pokemon class meant for use in a pokedex.
 *  Pulls data from a relational database, and contains known data about pokemon. Contains no
 *  Getters as a result. Does not contain all data for a Pokemon, may include more if time allows
 */
public class PokemonData {

  private String speciesName;
  private int speciesID;
  private double height;
  private double weight;
  private int[] baseStats = new int[6];
  private ArrayList<String> abilities = new ArrayList<String>();
  private ArrayList<String> eggGroups = new ArrayList<String>();
  private ArrayList<String> types = new ArrayList<String>();
  private ArrayList<PokemonMove> moves = new ArrayList<PokemonMove>();

  ///////////////////////////////////// Constructor /////////////////////////////////////
  /**
   * Constructor class based on the ID number
   * Fills the pokemon object with the data for that species
   * @param abilities
   */
  public PokemonData(int speciesID, String speciesName, double height, double weight,
      int[] baseStats, ArrayList<String> abilities, ArrayList<String> eggGroups,
      ArrayList<String> types, ArrayList<PokemonMove> moves) {
    this.speciesName = speciesName;
    this.speciesID = speciesID;
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

  public ArrayList<String> getAbilities() {
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