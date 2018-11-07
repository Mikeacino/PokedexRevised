package MainProject;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class PokemonDatabaseWork {
  private static final String DATABASE_URL = "jdbc:derby:C:\\Apache\\db-derby-10.14.2.0-bin\\bin\\PokemonDB";

  /**
   * Prints all data in the rowSet passed to the method.
   * Will often cause an error after use, but after printing the table.
   * @param rowBoat - the rowSet to be printed.
   */
  public static void printFullRowSet(JdbcRowSet rowBoat){
    try (JdbcRowSet rowSet = rowBoat) {
      ResultSetMetaData metaData = rowSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();
      System.out.println("RowSet:\n");

      // display rowset header
      for (int i = 1; i <= numberOfColumns; i++)
        System.out.printf("%-20s\t", metaData.getColumnName(i)+" ");
      System.out.println();

      // display each row
      while (rowSet.next())
      {
        for (int i = 1; i <= numberOfColumns; i++)
          System.out.printf("%-20s\t", rowSet.getObject(i)+" ");
        System.out.println();
      }

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(1);
    }
  }                                       //Prints the table, but will cause an error afterward.

  /**
   * This static method is used to get a PokemonEssentials object with all related data from only the id or
   * species name. This does not include things like flavor text or ability descriptions. this is only
   * @param pokemonObj either a string of the pokemon's name, or the pokemon's id number
   * @return returns a pokemon with all fields filled.
   */
  public static PokemonData getSinglePokemonData(Object pokemonObj) {
    PokemonData newPoke = null;                                                                   //The object PokemonData that will be returned full.

    try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
      //Empty arrays and lists, to be filled and passed into the PokemonData constructor
      String speciesName;
      int speciesID;
      double height;
      double weight;
      int[] baseStats = new int[6];
      ArrayList<Ability> abilities = new ArrayList<>();
      ArrayList<String> eggGroups = new ArrayList<>();
      ArrayList<String> pokemonTypes = new ArrayList<>();
      ArrayList<PokemonMove> moves = new ArrayList<>();

      ///////////////////////////////////// pokemon_data /////////////////////////////////////
      rowSet.setUrl(
          DATABASE_URL);                                                                  //Path to PokemonDB database
      String commandQuery;//To start, I need to know what PokemonEssentials I need the data for.

      if (pokemonObj instanceof String) {                                                           //Directs the command to the pokemon name given by parameter.
        commandQuery = "SELECT * FROM pokemon_data where pokemon_identifier = " + pokemonObj;
      } else if (pokemonObj instanceof Integer) {                                                   //Directs the command to the ID number of the pokemon
        commandQuery = "SELECT * FROM pokemon_data where pokemon_id = " + pokemonObj;
      } else {                                                                                      //Error handling
        commandQuery = "error";
        System.exit(2);
      }

      rowSet.setCommand(commandQuery); // set query
      rowSet.execute(); // execute query
      rowSet.next();
      //Relevant data from the first table
      speciesID = rowSet.getInt(1);
      speciesName = rowSet.getString(2);
      height = rowSet.getDouble(4);
      weight = rowSet.getDouble(5);

      ///////////////////////////////////// base_stats /////////////////////////////////////
      String statCommand = "SELECT * FROM BASE_STATS where POKEMON_ID = " + speciesID;
      rowSet.setCommand(statCommand); // set query
      rowSet.execute(); // execute query
      while (rowSet.next()) {
        baseStats[rowSet.getInt(2) - 1] = rowSet.getInt(3);
      }

      ///////////////////////////////////// pokemon_egg_groups /////////////////////////////////////
      String eggCommand = "SELECT * FROM POKEMON_EGG_GROUPS "
          + "JOIN EGG_GROUPS "
          + "ON POKEMON_EGG_GROUPS.egg_group_id = EGG_GROUPS.egg_group_id "
          + "WHERE pokemon_id = " + speciesID
          + "ORDER BY POKEMON_EGG_GROUPS.pokemon_id";
      rowSet.setCommand(eggCommand); // set query
      rowSet.execute(); // execute query
      while (rowSet.next()) {
        eggGroups.add(rowSet.getString(4));
      }

      ///////////////////////////////////// pokemon_types /////////////////////////////////////
      String typeCommand = "SELECT * FROM POKEMON_TYPES "
          + "JOIN TYPES "
          + "ON POKEMON_TYPES.type_id = TYPES.type_id "
          + "WHERE pokemon_id = " + speciesID
          + "ORDER BY POKEMON_TYPES.pokemon_id";
      rowSet.setCommand(typeCommand); // set query
      rowSet.execute(); // execute query
      while (rowSet.next()) {
        pokemonTypes.add(rowSet.getString(5));
      }

      ///////////////////////////////////// pokemon_abilities /////////////////////////////////////
      String abilityCommand = "SELECT * FROM pokemon_abilities "
          + "JOIN ability_prose "
          + "ON pokemon_abilities.ability_id = ability_prose.ability_id "
          + "WHERE pokemon_id = " + speciesID
          + "ORDER BY pokemon_abilities.pokemon_id"; //Why order the abilities? order by slot, and pass HA value to ability object
      rowSet.setCommand(abilityCommand); // set query
      rowSet.execute(); // execute query
      while (rowSet.next()) {
        abilities.add(new Ability(rowSet.getInt(2), rowSet.getString(6), rowSet.getString(7)));
      }

      ///////////////////////////////////// Pokemon Moves /////////////////////////////////////
      String pokemonMoveCommand = "SELECT POKEMON_MOVES.POKEMON_ID, MOVES.MOVE_ID, MOVES.MOVE_IDENTIFIER, TYPES.TYPE_IDENTIFIER, "
          + "       POKEMON_MOVE_METHOD.MOVE_METHOD_DESCRIPTION, POKEMON_MOVES.MOVE_LEVEL_LEARNED, MOVES.MOVE_POWER, "
          + "       MOVES.pp, MOVES.ACCURACY, MOVES.PRIORITY, MOVE_TARGETS.TARGET_IDENTIFIER, DAMAGE_CLASS.DAMAGE_CLASS_DESCRIPTION, "
          + "       MOVES.EFFECT_CHANCE, MOVE_EFFECT_PROSE.EFFECT_DESCRIPTION "
          + "FROM POKEMON_MOVES "
          + "JOIN MOVES "
          + "    ON POKEMON_MOVES.move_id = MOVES.move_id "
          + "JOIN TYPES "
          + "    ON MOVES.TYPE_ID = TYPES.TYPE_ID "
          + "JOIN POKEMON_MOVE_METHOD "
          + "    ON POKEMON_MOVES.pokemon_move_method_id = POKEMON_MOVE_METHOD.pokemon_move_method_id "
          + "JOIN DAMAGE_CLASS "
          + "    ON MOVES.DAMAGE_CLASS_ID = DAMAGE_CLASS.DAMAGE_CLASS_ID "
          + "JOIN MOVE_EFFECT_PROSE "
          + "    ON MOVES.EFFECT_ID = MOVE_EFFECT_PROSE.EFFECT_ID "
          + "JOIN MOVE_TARGETS "
          + "    ON MOVE_TARGETS.TARGET_ID = MOVES.TARGET_ID "
          + "WHERE pokemon_id = " + speciesID
          + "ORDER BY pokemon_id";

      rowSet.setCommand(pokemonMoveCommand); // set query
      rowSet.execute(); // execute query
      while (rowSet.next()){
        moves.add(new PokemonMove(rowSet.getInt(2), rowSet.getString(3), rowSet.getString(4),
            rowSet.getString(5), rowSet.getInt(6), rowSet.getInt(7), rowSet.getInt(8),
            rowSet.getInt(9), rowSet.getInt(10),rowSet.getString(11), rowSet.getString(12),
            rowSet.getInt(13), rowSet.getString(14)));
      }
      newPoke = new PokemonData(speciesID, speciesName, height, weight, baseStats, abilities,
          eggGroups, pokemonTypes, moves);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(1);
    }
    return newPoke;
  }
}