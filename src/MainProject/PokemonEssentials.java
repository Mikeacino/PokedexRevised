package MainProject;
/**
 * Interface for Pokemon Data that is used in damage calculations.
 */

import java.util.ArrayList;

public interface PokemonEssentials {
  ///////////////////////////////////// Fields /////////////////////////////////////
  String speciesName = null;
  int speciesID = 0;
  double weight = 0;
  int[] baseStats = new int[6];
  ArrayList<Ability> abilities = new ArrayList<>();
  ArrayList<String> types = new ArrayList<>();
  ArrayList<PokemonMove> moves = new ArrayList<>();

  ///////////////////////////////////// Accessors /////////////////////////////////////
  public String getSpeciesName();
  public int getSpeciesID();
  public double getWeight();
  public int[] getBaseStats();
  public ArrayList<Ability> getAbilities();
  public ArrayList<String> getTypes();
  public ArrayList<PokemonMove> getMoves();
}
