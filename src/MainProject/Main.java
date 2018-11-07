package MainProject;
/**
 * Michael Carracino
 * 11/6/2018
 * Prints out the pokedex page for a pokemon.
 *
 * Known issues:
 *    Details in top right are scattered, needs to look nice
 *    Only one type icon is being displayed
 *    GridPanes for moves are still rough, need to be cleaned up
 *    The scene was made not resizable for convenience, but end result should probably be resizable
 *    Base Stat section is undersized
 *    General font changes, some fonts are too big or too small
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pokedexWindow.fxml"));
        primaryStage.setTitle("Pokedex 2.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}