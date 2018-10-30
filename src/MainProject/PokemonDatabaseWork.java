package MainProject;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class PokemonDatabaseWork {
  private static final String DATABASE_URL = "jdbc:derby:C:\\Apache\\db-derby-10.14.2.0-bin\\bin\\PokemonDB";

  public static void printFullPokemonTable(){
    try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
      // specify JdbcRowSet properties
      rowSet.setUrl(DATABASE_URL);
      rowSet.setCommand("SELECT * FROM pokemon_data"); // set query
      rowSet.execute(); // execute query

      ResultSetMetaData metaData = rowSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();
      System.out.println("Raw Pokemon Data:\n");

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
  }

  /**
   * This static method is used to get a Pokemon object with all related data from only the id or species name.
   * @param pokemon either a string of the pokemon's name, or the pokemon's id number
   * @return returns a pokemon with all fields filled.
   */
  public static PokemonData getSinglePokemonData(Object pokemon) {
    String commandVar;  //Helps avoid scope issue
    PokemonData newPoke = null;
    try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
      rowSet.setUrl(DATABASE_URL);

      if (pokemon instanceof String) {  //Handles the ID and species parameters, while closing the program if bad data is entered
        commandVar = "SELECT * FROM pokemon_data where IDENTIFIER = " + pokemon;
      } else if (pokemon instanceof Integer) {
        commandVar = "SELECT * FROM pokemon_data where POKEMON_ID = " + pokemon;
      } else {
        commandVar = "";
        System.exit(2);
      }

      String command = commandVar;  //Used an extra String to avoid scope issue with command
      rowSet.setCommand(command); // set query
      rowSet.execute(); // execute query
      ResultSetMetaData metaData = rowSet.getMetaData();
      rowSet.next();
      //System.out.print(rowSet.getString(2)+rowSet.getInt(1)+rowSet.getDouble(4)+rowSet.getDouble(5));

      //Dummy Stats while i update the database with all the relevant data.
      int[] baseStats = {50,70,50,50,50,40};
      ArrayList<String> abilities = null;
      ArrayList<String> eggGroups = new ArrayList<String>();
      eggGroups.add("Fake");
      //eggGroups.add("Faker");
      ArrayList<String> types = null;
      ArrayList<PokemonMove> moves = null;
      newPoke = new PokemonData(rowSet.getInt(1), rowSet.getString(2),
          rowSet.getDouble(4), rowSet.getDouble(5), baseStats, abilities, eggGroups, types, moves);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(1);
    }
    return newPoke;
  }
}
