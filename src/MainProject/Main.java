package MainProject;
/**
 * Michael Carracino
 * 11/17/2018
 * Prints out the pokedex page for a pokemon.
 *
 * Known issues:
 *    Components in top right are scattered, needs to look nice
 *    UTF-8 needs to be in the database
 *    ComboBox for forms isn't working
 *          some images aren't loading
 *    Rename image variable names
 *    Make PokemonMove an extended version, method-learned is pokemon specific, not move specific...
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Standard Main method. Builds the scene.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pokedexWindow.fxml"));
        primaryStage.setTitle("Pokedex 2.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
/**
 * Comments for GUI database project.
 *   In order to connect to a database using JdbcRowSet, you must first establish a URL link.
 *   I used a constant URL to link to my database, then connected to it using the setURL() method
 *   for rowsets. Next I used the setCommand() method to pass a String of SQL code into the rowset,
 *   waiting to be executed. Next I used the execute() method to actually execute the query on the
 *   database. This step often threw an error when there was an error in the SQL code. after that
 *   the connection is made, and the results of the query can be read freely. The only caveat is
 *   that the rowSet needs the next() method called to place the cursor onto the first line.
 */