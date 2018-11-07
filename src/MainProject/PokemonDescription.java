package MainProject;
/**
 * interface for data about a Pokemon that may not be relevant for battle calculations
 */

import java.util.ArrayList;
import java.util.HashMap;

public interface PokemonDescription {
  ///////////////////////////////////// Fields /////////////////////////////////////
  double height = 0;
  ArrayList<String> eggGroups = new ArrayList<>();

  ///////////////////////////////////// Accessors /////////////////////////////////////
  public double getHeight();
  public ArrayList<String> getEggGroups();
}
