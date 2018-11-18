package MainProject;

import java.util.ArrayList;

public class FullPokemonList {

  private static boolean isFirstPokemon = true;
  private static ArrayList<String> fullPokemonList = new ArrayList<>();

  public static boolean getIsFirstPokemon() {
    return isFirstPokemon;
  }

  public static void setFirstPokemon(boolean firstPokemon) {
    isFirstPokemon = firstPokemon;
  }

  public static ArrayList<String> getFullPokemonList() {
    return fullPokemonList;
  }

  public static void setFullPokemonList(ArrayList<String> fullPokemonList) {
    FullPokemonList.fullPokemonList = fullPokemonList;
  }
}