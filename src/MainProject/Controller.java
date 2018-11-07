package MainProject;
import static MainProject.PokemonDatabaseWork.getSinglePokemonData;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
import javafx.scene.text.Font;

public class Controller {
  private static int currentID = 1;

  //Trying to visually show the various components and their children
  @FXML
  private ScrollPane scrollPaneMain;
    @FXML
    private VBox vBoxMain;
      ////////////////////////////// Holds Sprites and generic data //////////////////////////////
      @FXML
      private HBox hBoxFirst;
        @FXML
        private ImageView imgNormalSprite;
        @FXML
        private ImageView imgShinySprite;
        @FXML
        private ImageView imgTypeOne;
        @FXML
        private ImageView imgTypeTwo;
        @FXML
        private Label lblSpeciesName;
        @FXML
        private Label lblSpeciesNumber;
        @FXML
        private Label lblHeight;
        @FXML
        private Label lblWeight;
        @FXML
        private Label lblEggGroupOne;
        @FXML
        private Label lblEggGroupTwo;
      /////////////////////////////// Builds to fit abilities ///////////////////////////////
      @FXML
      private VBox VBoxAbilities;
      /////////////////////////////// Base Stats and Rectangles ///////////////////////////////
      @FXML
      private GridPane gridPaneBST;
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
      /////////////////////////////// Moves learned by Level-Up ///////////////////////////////
      @FXML
      private VBox vBoxMovesLevelUp;
      /////////////////////////////// Moves Learned by TM/HMs ///////////////////////////////
      @FXML
      private VBox vBoxMovesTM;
      /////////////////////////////// Moves Learned by Move Tutor ///////////////////////////////
      @FXML
      private VBox vBoxMovesMoveTutor;
      /////////////////////////////// Moves Learned by Egg Breeding ///////////////////////////////
      @FXML
      private VBox vBoxMovesEgg;
  ///////////////////////////////////// Buttons /////////////////////////////////////
  @FXML
  private Button btnRandom;
  @FXML
  private Button btnNext;
  @FXML
  private Button btnPrevious;
  @FXML
  private ComboBox<String> comboBoxPokemon;

  /**
   * This is called when the fxml file is building the scene.
   * A lot of work is done here, i am seeing if i can offload some of it to other methods, but for
   * now it is a massive cluster-fuck.
   */
  public void initialize() {
    PokemonData newPoke = getSinglePokemonData(currentID);    //Build the Pokemon from the currentID
    int pokemonID = newPoke.getSpeciesID();                                                         //Species ID
    String pokemonName = newPoke.getSpeciesName();                                                  //Species Name
    int[] dummyBSTArray = newPoke.getBaseStats();                                                   //Base Stats
    ArrayList types = newPoke.getTypes();                                                           //Types
    ArrayList<String> dummyEggGroupArray = newPoke.getEggGroups();                                  //Egg Groups
    ArrayList<Ability> dummyAbilityArray = newPoke.getAbilities();                                  //Abilities
    ArrayList<PokemonMove> dummyMoves = newPoke.getMoves();                                         //Moves

    ///////////////////////////////////// Images /////////////////////////////////////
    Image imgNormal = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/" + pokemonID + ".png");
    Image imgShiny = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/shiny/" + pokemonID + ".png");
    Image imgType1 = new Image("file:///C://Users//chris//OneDrive//Pokemon 2018 Test Files/Pokemon type images/" + types.get(0) + "_serebii.gif");
    imgNormalSprite.setImage(imgNormal);
    imgShinySprite.setImage(imgShiny);
    imgTypeOne.setImage(imgType1);
    //if (types.size() > 1) {
      System.out.println("There are two types!");
      Image imgType2 = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/PokemonEssentials type images/bug_serebii.gif");
      imgTypeTwo.setImage(imgType2);
    //}
    ///////////////////////////////////// Abilities /////////////////////////////////////
    Font medium = new Font("Arial", 20);
    Font large = new Font("Arial", 26);
    VBoxAbilities.getChildren().clear();
    for (int i = 0; i < dummyAbilityArray.size(); i++){
      String abilName = dummyAbilityArray.get(i).getAbilityName();
      Label abilityName = new Label(abilName.substring(0, 1).toUpperCase() + abilName.substring(1));
      Label abilityDescription = new Label("\t" + dummyAbilityArray.get(i).getAbilityDescription());
      abilityName.setFont(large);
      abilityDescription.setFont(medium);
      VBoxAbilities.getChildren().add(abilityName);
      VBoxAbilities.getChildren().add(abilityDescription);
    }

    ///////////////////////////////////// Moves /////////////////////////////////////
    vBoxMovesLevelUp.getChildren().clear();    //Clear the vBox for moves before building a new one
    vBoxMovesTM.getChildren().clear();
    vBoxMovesMoveTutor.getChildren().clear();
    vBoxMovesEgg.getChildren().clear();

    final GridPane layoutLvl = new GridPane();
    final GridPane layoutTM = new GridPane();
    final GridPane layoutTutor = new GridPane();
    final GridPane layoutEgg = new GridPane();

    Label headerLvl = new Label("Level-Up");
    Label headerTM = new Label("TM/HM");
    Label headerTutor = new Label("Move Tutor");
    Label headerEgg = new Label("Egg Moves");

    Label headerType = new Label("Type");
    Label headerDmgclass = new Label("DmgType");
    Label headerPower = new Label("Power");
    Label headerAcc = new Label("Accuracy");
    Label headerPP = new Label("PP");
    Label headerPriority = new Label("Priority");

    layoutLvl.add(headerLvl, 0, 0);
    layoutLvl.add(headerDmgclass, 2, 0);
    layoutLvl.add(headerType, 1, 0);
    layoutLvl.add(headerPower, 3, 0);
    layoutLvl.add(headerPP, 4, 0);
    layoutLvl.add(headerAcc, 5, 0);
    layoutLvl.add(headerPriority, 6, 0);

    layoutTM.add(headerTM, 0, 0);

    layoutTutor.add(headerTutor, 0, 0);

    layoutEgg.add(headerEgg, 0, 0);

    int rowCount = 1;   //Start at 1 to skip the header
    for (PokemonMove dmyMove:dummyMoves){       //Builds a grid of all moves dynamically
      String power = "";
      String accuracy = "";
      Label lbl1 = new Label(dmyMove.getMoveIdentifier());                                          //Move Description
      String moveType = dmyMove.getType();                                                          //Move Type
      ImageView typeImage = new ImageView("file:///C://Users//chris//OneDrive//Pokemon 2018 "
          + "Test Files/Pokemon type images/" + moveType + "_serebii.gif");
      String dmgType = dmyMove.getDamageClassDescription();                                         //Damage Class
      ImageView dmgClass = new ImageView("File:///C://Users//chris//OneDrive//Pokemon 2018 "
          + "Test Files/move damage class icons/" + dmgType + ".png");

      if (dmyMove.getPower() == -1){                                                                 //Power
        power = "-";
      } else{
        power = dmyMove.getPower() + "";
      }
      Label lbl4 = new Label(power);

      if (dmyMove.getAccuracy() == -1){                                                             //The data for Acc has negatives when it should be null, so i handle it here
        accuracy = "-";
      } else{
        accuracy = dmyMove.getAccuracy() + "";
      }
      Label lbl5 = new Label(accuracy);                                                             //Accuracy

      Label lbl6 = new Label(dmyMove.getPp() + "");                                            //PP

      Label lbl7 = new Label(dmyMove.getPriority() + "");                                      //Priority

      GridPane[] dummyGP = {layoutLvl, layoutTM, layoutTutor, layoutEgg};                           //Makes my code more compact, Places the moves in their proper box
      int varGP;
      switch (dmyMove.getMethodLearned()){
        case "Level-Up":
          varGP = 0;
          break;
        case "TM/HM":
          varGP = 1;
          break;
        case "Move Tutor":
          varGP = 2;
          break;
        case "Egg Move":
          varGP = 3;
          break;
        default:
          varGP = 0;
          break;
      }

      lbl1.setFont(large);                                                                          //Set the font and format for the labels/images
      typeImage.setPreserveRatio(true);
      typeImage.setFitHeight(25);
      dmgClass.setPreserveRatio(true);
      dmgClass.setFitHeight(25);
      lbl4.setFont(large);
      lbl5.setFont(large);

      dummyGP[varGP].add(lbl1, 0, 2*rowCount);                                  //Add the labels and images to the GridPane
      dummyGP[varGP].add(typeImage, 1, 2*rowCount);
      dummyGP[varGP].add(dmgClass, 2, 2*rowCount);
      dummyGP[varGP].add(lbl4, 3, 2*rowCount);
      dummyGP[varGP].add(lbl5, 4, 2*rowCount);
      dummyGP[varGP].add(lbl6, 5, 2*rowCount);
      dummyGP[varGP].add(lbl7, 6, 2*rowCount);

      dummyGP[varGP].setHgap(15);                                                                   //Set the height of the GridPane



      Label moveDescription = new Label(dmyMove.getEffectDescription());
      moveDescription.setFont(medium);
      dummyGP[varGP].add(moveDescription, 0, 2*rowCount + 1);
      rowCount++;
    }
    vBoxMovesLevelUp.getChildren().add(layoutLvl);
    vBoxMovesLevelUp.getChildren().add(layoutTM);
    vBoxMovesLevelUp.getChildren().add(layoutTutor);
    vBoxMovesLevelUp.getChildren().add(layoutEgg);

    ///////////////////////////////////// Pokemon Data /////////////////////////////////////
    lblSpeciesName.setText(pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1));
    lblSpeciesNumber.setText("#"+Integer.toString(pokemonID));
    lblHeight.setText(Double.toString(newPoke.getHeight()/10) + "m");
    lblWeight.setText(Double.toString(newPoke.getWeight()/10) + "kg");

    ///////////////////////////////////// Base Stats /////////////////////////////////////
    lblBaseHP.setText(Integer.toString(dummyBSTArray[0]));
    lblBaseAttack.setText(Integer.toString(dummyBSTArray[1]));
    lblBaseDefense.setText(Integer.toString(dummyBSTArray[2]));
    lblBaseSpecialAttack.setText(Integer.toString(dummyBSTArray[3]));
    lblBaseSpecialDefense.setText(Integer.toString(dummyBSTArray[4]));
    lblBaseSpeed.setText(Integer.toString(dummyBSTArray[5]));

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

  /**
   * Handles the pressing of the Previous button
   * @param event - button being pressed
   */
  @FXML
  private void handlePreviousButtonAction(ActionEvent event){
    currentID--;
    initialize();
  }

  /**
   * Handles the pressing of the Random button
   * @param event - button being pressed
   */
  @FXML
  private void handleRandomButtonAction(ActionEvent event) {
    // Button was clicked, do something…
    Random rand = new Random();
    currentID = rand.nextInt(801)+1;
    initialize();
  }

  /**
   * Handles the pressing of the Next button
   * @param event - button being pressed
   */
  @FXML
  private void handleNextButtonAction(ActionEvent event){
    currentID++;
    initialize();
  }
}