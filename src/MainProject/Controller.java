package MainProject;
import static MainProject.PokemonDatabaseWork.getSinglePokemonData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;

public class Controller {
  private static int currentID = 1;
  ///////////////////////////////////// Rectangles /////////////////////////////////////
  @FXML
  public Rectangle rectHP;
  @FXML
  public Rectangle rectAtt;
  @FXML
  public Rectangle rectDef;
  @FXML
  public Rectangle rectSpAtt;
  @FXML
  public Rectangle rectSpDef;
  @FXML
  public Rectangle rectSpeed;

  ///////////////////////////////////// Base Stats /////////////////////////////////////
  @FXML
  private Label lblBaseHP;
  @FXML
  private Label lblBaseAttack;
  @FXML
  private Label lblBaseDefense;
  @FXML
  private Label lblBaseSpecialAttack;
  @FXML
  private Label lblBaseSpecialDefense;
  @FXML
  private Label lblBaseSpeed;

  ///////////////////////////////////// Pokemon Data /////////////////////////////////////
  @FXML
  private Label lblSpeciesName;
  @FXML
  private Label lblSpeciesNumber;
  @FXML
  private Label lblHeight;
  @FXML
  private Label lblWeight;

  ///////////////////////////////////// Egg Groups /////////////////////////////////////
  @FXML
  private Label lblEggGroupOne;
  @FXML
  private Label lblEggGroupTwo;

  ///////////////////////////////////// Buttons /////////////////////////////////////
  @FXML
  private Button btnRandom;
  @FXML
  private Button btnNext;
  @FXML
  private Button btnPrevious;

  ///////////////////////////////////// comboBox /////////////////////////////////////
  @FXML
  private ComboBox<String> comboBoxPokemon;
  @FXML
  private GridPane gridPaneBST;

  ///////////////////////////////////// Abilities /////////////////////////////////////
  @FXML
  private Label lblAbilityOne;
  @FXML
  private Label lblAbilityTwo;
  @FXML
  private Label lblAbilityThree;
  @FXML
  private Label lblAbilityOneDescription;
  @FXML
  private Label lblAbilityTwoDescription;
  @FXML
  private Label lblAbilityThreeDescription;

  ///////////////////////////////////// Images /////////////////////////////////////
  @FXML
  private ImageView imgNormalSprite;
  @FXML
  private ImageView imgShinySprite;
  @FXML
  private ImageView imgTypeOne;
  @FXML
  private ImageView imgTypeTwo;

  public void initialize() {
    Random rand = new Random();
    PokemonData newPoke = getSinglePokemonData(currentID);
    int pokemonID = newPoke.getSpeciesID();
    String pokemonName = newPoke.getSpeciesName();
    int[] dummyBSTArray = newPoke.getBaseStats();
    ArrayList types = newPoke.getTypes();
    ArrayList<String> dummyEggGroupArray = newPoke.getEggGroups();
    ArrayList<Ability> dummyAbilityArray = newPoke.getAbilities();
    Image imgNormal = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/" + pokemonID + ".png");
    Image imgShiny = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/shiny/" + pokemonID + ".png");
    Image imgType1 = new Image("file:///C://Users//chris//OneDrive//Pokemon 2018 Test Files/Pokemon type images/" + types.get(0) + "_serebii.gif");

    ///////////////////////////////////// Pokemon Data /////////////////////////////////////
    lblSpeciesName.setText(pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1));
    lblSpeciesNumber.setText("#"+Integer.toString(pokemonID));
    lblHeight.setText(Double.toString(newPoke.getHeight()/10) + "m");
    lblWeight.setText(Double.toString(newPoke.getWeight()/10) + "kg");

    ///////////////////////////////////// Images /////////////////////////////////////
    imgNormalSprite.setImage(imgNormal);
    imgShinySprite.setImage(imgShiny);
    imgTypeOne.setImage(imgType1);
    if (types.size() > 1) {
      Image imgType2 = new Image(
          "file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/PokemonEssentials type images/" + types
              .get(1) + "_serebii.gif");
      imgTypeTwo.setImage(imgType2);
    }

    ///////////////////////////////////// Base Stats /////////////////////////////////////
    lblBaseHP.setText(Integer.toString(dummyBSTArray[0]));
    lblBaseAttack.setText(Integer.toString(dummyBSTArray[1]));
    lblBaseDefense.setText(Integer.toString(dummyBSTArray[2]));
    lblBaseSpecialAttack.setText(Integer.toString(dummyBSTArray[3]));
    lblBaseSpecialDefense.setText(Integer.toString(dummyBSTArray[4]));
    lblBaseSpeed.setText(Integer.toString(dummyBSTArray[5]));

    ///////////////////////////////////// Abilities /////////////////////////////////////
    //Clear ability text before using.
    lblAbilityOne.setText(dummyAbilityArray.get(0).getAbilityName());                               //Pokemon always ahve at least one ability
    lblAbilityOneDescription.setText(dummyAbilityArray.get(0).getAbilityDescription());
    lblAbilityTwo.setText(null);
    lblAbilityTwoDescription.setText(null);
    lblAbilityThree.setText(null);
    lblAbilityThreeDescription.setText(null);
    if (dummyAbilityArray.size() == 2){                                                             //Two abilities
      lblAbilityTwo.setText(dummyAbilityArray.get(1).getAbilityName());
      lblAbilityTwoDescription.setText(dummyAbilityArray.get(1).getAbilityDescription());
    } else if (dummyAbilityArray.size() == 3){                                                                                        //Three abilities
      lblAbilityTwo.setText(dummyAbilityArray.get(1).getAbilityName());
      lblAbilityTwoDescription.setText(dummyAbilityArray.get(1).getAbilityDescription());
      lblAbilityThree.setText(dummyAbilityArray.get(2).getAbilityName());
      lblAbilityThreeDescription.setText(dummyAbilityArray.get(2).getAbilityDescription());
    }

    ///////////////////////////////////// Buttons /////////////////////////////////////
    if (currentID == 1){
      btnPrevious.setDisable(true);
      btnNext.setDisable(false);
    } else if (currentID == 802){
      btnPrevious.setDisable(false);
      btnNext.setDisable(true);
    } else{
      btnNext.setDisable(false);
      btnPrevious.setDisable(false);
    }

    ///////////////////////////////////// Rectangles /////////////////////////////////////
    Rectangle[] dummy = {rectHP, rectAtt, rectDef, rectSpAtt, rectSpDef, rectSpeed};
    for (int i = 0; i < 6; i++){
      dummy[i].setWidth(2*dummyBSTArray[i]);
      dummy[i].setFill(Color.rgb(255 - dummyBSTArray[i], 0,255 - dummyBSTArray[i]));
    }

    ///////////////////////////////////// Egg Groups /////////////////////////////////////
    lblEggGroupOne.setText(dummyEggGroupArray.get(0));
    if (dummyEggGroupArray.size() >1){
      lblEggGroupTwo.setText(dummyEggGroupArray.get(1));
    } else{
      lblEggGroupTwo.setText("");
    }
  }

  @FXML
  private void handlePreviousButtonAction(ActionEvent event){
    currentID--;
    initialize();
  }

  @FXML
  private void handleRandomButtonAction(ActionEvent event) {
    // Button was clicked, do something…
    Random rand = new Random();
    currentID = rand.nextInt(801)+1;
    initialize();
  }

  @FXML
  private void handleNextButtonAction(ActionEvent event){
    currentID++;
    initialize();
  }
}