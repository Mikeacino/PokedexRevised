package MainProject;
/**
 *
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //PokemonDatabaseWork.printFullPokemonTable();
        Parent root = FXMLLoader.load(getClass().getResource("pokedexWindow.fxml"));
        primaryStage.setTitle("Pokedex 2.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}