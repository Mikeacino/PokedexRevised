package MainProject;
//I swear these are all needed somewhere or other
import static MainProject.PokemonDatabaseWork.getAlternateFormID;
import static MainProject.PokemonDatabaseWork.getAlternateFormsListFromDB;
import static MainProject.PokemonDatabaseWork.getFullPokemonListFromDB;
import static MainProject.PokemonDatabaseWork.getSinglePokemonData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Controller {
  boolean firstIteration = true;
  private int currentPokemonID = 1;
  private int currentSpeciesID = 1;

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
      @FXML
      private HBox hBoxButtons;
  ///////////////////////////////////// Buttons /////////////////////////////////////
  @FXML
  private Button btnRandom;
  @FXML
  private Button btnNext;
  @FXML
  private Button btnPrevious;
  @FXML
  private ComboBox<String> comboBoxPokemon;
  @FXML
  private Pane paneAlternateForms;


  /**
   * This is called when the fxml file is building the scene.
   * A lot of work is done here, i am seeing if i can offload some of it to other methods, but for
   * now it is a massive cluster-f***. The large slashes are my way of showing where I would usually
   * separate this into methods. The lower half involves building GUI components directly in the
   * controller, and will get very cluttered, as such the comments may get sparse.
   */
  public void initialize() {
    PokemonData newPoke = getSinglePokemonData(currentPokemonID);                                   //Build the Pokemon from the currentPokemonID
    currentSpeciesID = newPoke.getSpeciesID();                                                      //Starts the window at currentSpeciesID (Bulbasaur)

    //////////////////////////////////// Accessing Pokemon Data ////////////////////////////////////
    int pokemonID = newPoke.getPokemonID();                                                         //Species ID
    String pokemonName = newPoke.getSpeciesName();                                                  //Species Name
    int[] dummyBSTArray = newPoke.getBaseStats();                                                   //Base Stats
    ArrayList types = newPoke.getTypes();                                                           //Types
    ArrayList<String> dummyEggGroupArray = newPoke.getEggGroups();                                  //Egg Groups
    ArrayList<Ability> dummyAbilityArray = newPoke.getAbilities();                                  //Abilities
    ArrayList<PokemonMove> dummyMoves = newPoke.getMoves();                                         //Moves

    ///////////////////////////////////// comboBoxPokemon /////////////////////////////////////
    if (firstIteration){
      ArrayList<String> dummyPokemonList = getFullPokemonListFromDB();                              //Creates a list of all pokemon species for ComboBox selection
      ObservableList<String> dummyPokemonFullArrayList= FXCollections.observableArrayList(            //The list has to be an ObservableList
          dummyPokemonList);
      comboBoxPokemon.setItems(dummyPokemonFullArrayList);                                            //Set the comboBox to the list
    }
    ////////////////////// Full Pokemon List event //////////////////////
    EventHandler<ActionEvent> pokemonCBevent =
        e -> {
          if (comboBoxPokemon.getValue() != null) {
            currentPokemonID = Integer.parseInt(comboBoxPokemon.getValue()
                .substring(                 //Sets the users ComboBox choice as the current pokemon
                    0, comboBoxPokemon.getValue().indexOf(" ")));
            comboBoxPokemon.getSelectionModel().clearSelection();
            initialize();                                                                           //Re-Initializes the window with the new current Pokemon
          }
        };
    comboBoxPokemon.setOnAction(pokemonCBevent);                                                    //Add the event to be performed on action

    ///////////////////////////////////// ComboBoxForms /////////////////////////////////////
    ArrayList<String> dummyFormList = getAlternateFormsListFromDB(currentSpeciesID);                //List of alternate forms, currently bugged
    paneAlternateForms.getChildren().clear();
    if (dummyFormList.size() > 1) {                                                                 //Does this pokemon have an alternate form?
      ComboBox comboBoxForms = new ComboBox();
      ObservableList<String> dummyFormsArrayList = FXCollections.observableArrayList(dummyFormList);
      comboBoxForms.setItems(dummyFormsArrayList);                                                  //Set the ComboBox to the list
      comboBoxForms.getSelectionModel().selectFirst();
      //////////////////// Create Alternate Forms event ////////////////////////
      EventHandler<ActionEvent> event =
          e -> {
            String dummmy = (String) comboBoxForms.getValue();
            currentPokemonID = getAlternateFormID(dummmy); //getAlternateFormID(comboBoxForms.getValue());
            initialize();
          };
      comboBoxForms.setOnAction(event);
      paneAlternateForms.getChildren().add(comboBoxForms);
    }
    ///////////////////////////////////// Images /////////////////////////////////////
    Image imgNormal = new Image("File:///C://Users//chris//OneDrive//IntelliJ work (2018)//"    //Sprite for Normal Pokemon
        + "Programs//Pokedex 2.0//src//pokemon//" + pokemonID + ".png");
    Image imgShiny = new Image("File:///C://Users//chris//OneDrive//IntelliJ work (2018)//"     //Sprite for Shiny Pokemon
        + "Programs//Pokedex 2.0//src//shiny//" + pokemonID + ".png");
    Image imgType1 = new Image("File:///C://Users//chris//OneDrive//IntelliJ work (2018)//"     //Sprite for the pokemon's Type, needs to be renamed
        + "Programs//Pokedex 2.0//src//Pokemon type images//" + types.get(0) + "_serebii.gif");
    imgNormalSprite.setImage(imgNormal);                                                            //Set the normal ImageView to the Image made here
    imgShinySprite.setImage(imgShiny);                                                              //Set the shiny ImageView to the Image made here
    imgTypeOne.setImage(imgType1);                                                                  //Set the first type ImageView to the Image made here
    if (types.size() > 1) {                                                                         //Only runs if the pokemon has more than one type
      imgTypeTwo.setVisible(true);                                                                  //Makes the second type image visible
      Image imgType2 = new Image("file:///C://Users//chris//OneDrive//Pokemon 2018 Test Files/"
          + "Pokemon type images/" + types.get(1) + "_serebii.gif");
      imgTypeTwo.setImage(imgType2);                                                                ////Set the second type ImageView to the Image made here
    } else{                                                                                         //Only runs if the pokemon has one type
      imgTypeTwo.setVisible(false);                                                                 //Sets the unused image to invisible
    }

    ///////////////////////////////////// Abilities /////////////////////////////////////
    VBoxAbilities.setPadding(new Insets(10, 10, 10, 10));                     //Set the ability Padding
    Font small = new Font("Arial", 20);                                                  //Next three lines are fonts for easy access
    Font medium = new Font("Arial", 26);
    Font large = new Font("Arial", 28);

    VBoxAbilities.getChildren().clear();                                                            //Clearing the vBox to get rid of any potential leftovers
    for (Ability dummyAbility:dummyAbilityArray){                                                   //Loops through every ability and sets them to a nice readable size
      Label abilityName = new Label(dummyAbility.getAbilityName().substring(
          0, 1).toUpperCase()
          + dummyAbility.getAbilityName().substring(1));
      Label abilityDescription = new Label("\t" + dummyAbility.getAbilityDescription());
      abilityName.setFont(medium);
      abilityDescription.setFont(small);
      VBoxAbilities.getChildren().add(abilityName);                                                 //Add the ability Nodes to the VBox that is part of the scene
      VBoxAbilities.getChildren().add(abilityDescription);
    }

    ///////////////////////////////////// Moves /////////////////////////////////////               //Much of this section is loops
    vBoxMovesLevelUp.getChildren().clear();                                                         //Clear the vBox for moves before building a new one
    vBoxMovesTM.getChildren().clear();                                                              //I use four VBoxes to separate moves based on how they are learned
    vBoxMovesMoveTutor.getChildren().clear();
    vBoxMovesEgg.getChildren().clear();

    GridPane gridPaneHeader = new GridPane();
    gridPaneHeader.getChildren().clear();

    final VBox layoutLvl = new VBox();                                                              //Build a GridPane for every type of move
    final VBox layoutTM = new VBox();
    final VBox layoutTutor = new VBox();
    final VBox layoutEgg = new VBox();

    ColumnConstraints columnOne = new ColumnConstraints(200);                                 //Setting the columnConstraints for the gridPanes later
    gridPaneHeader.getColumnConstraints().add(columnOne);

    ColumnConstraints columnTwo = new ColumnConstraints(70);
    gridPaneHeader.getColumnConstraints().add(columnTwo);

    ColumnConstraints columnThree = new ColumnConstraints(70);
    gridPaneHeader.getColumnConstraints().add(columnThree);

    ColumnConstraints columnFour = new ColumnConstraints(60);
    gridPaneHeader.getColumnConstraints().add(columnFour);

    ColumnConstraints columnFive = new ColumnConstraints(60);
    gridPaneHeader.getColumnConstraints().add(columnFive);

    ColumnConstraints columnSix= new ColumnConstraints(60);
    gridPaneHeader.getColumnConstraints().add(columnSix);

    VBox[] gridPaneArray = {layoutLvl, layoutTM, layoutTutor, layoutEgg};                           //Allows me to loop through all four VBoxes
    for (VBox dummyGridPane:gridPaneArray){                                                         //Loops through all move-GridPanes
      dummyGridPane.setPadding(new Insets(10, 10, 20, 10));                   //Padding for the GridPanes
    }

    //Setting various labels and their sizes
    Label headerLvl = new Label("\tLevel-Up");                                                 //Adds the headers for the various columns. I change these often to make it more readable
    headerLvl.setFont(large);
    layoutLvl.getChildren().add(headerLvl);

    Label headerTM = new Label("\tTM/HM");
    headerTM.setFont(large);
    layoutTM.getChildren().add(headerTM);

    Label headerTutor = new Label("\tMove Tutor");
    headerTutor.setFont(large);
    layoutTutor.getChildren().add(headerTutor);

    Label headerEgg = new Label("\tEgg Moves");
    headerEgg.setFont(large);
    layoutEgg.getChildren().add(headerEgg);

    //Generic headers for the move boxes
    Label headerType = new Label("Type");
    headerType.setFont(small);
    gridPaneHeader.add(headerType, 1, 0);

    Label headerDmgclass = new Label("Status");
    headerDmgclass.setFont(small);
    gridPaneHeader.add(headerDmgclass, 2, 0);

    Label headerPower = new Label("Power");
    headerPower.setFont(small);
    gridPaneHeader.add(headerPower, 3, 0);

    Label headerAcc = new Label("Acc.");
    headerAcc.setFont(small);
    gridPaneHeader.add(headerAcc, 4, 0);

    Label headerPP = new Label("PP");
    headerPP.setFont(small);
    gridPaneHeader.add(headerPP, 5, 0);

    Label headerPriority = new Label("Priority");
    headerPriority.setFont(small);
    gridPaneHeader.add(headerPriority, 6, 0);

    layoutLvl.getChildren().add(gridPaneHeader);                                                    //Currently only sets these headers to one GridPane, until i decide i like this look

    for (PokemonMove dmyMove:dummyMoves){                                                           //Loops through all moves for the pokemon. Much of this is in flux right now
      String power;                                                                                 //Move Power
      String accuracy;                                                                              //Move Accuracy
      Label lblMoveIdentifier = new Label(dmyMove.getMoveIdentifier());                             //Move name

      String moveType = dmyMove.getType();                                                          //Move Type
      ImageView typeImage = new ImageView("file:///C://Users//chris//OneDrive//Pokemon 2018 "   //Image for the type of the move
          + "Test Files/Pokemon type images/" + moveType + "_serebii.gif");

      String dmgType = dmyMove.getDamageClassDescription();                                         //Damage Class
      ImageView dmgClass = new ImageView("File:///C://Users//chris//OneDrive//IntelliJ work "   //Path should be relative now, and work after submission
          + "(2018)//Programs//Pokedex 2.0//src//DmgClassIcons//"+ dmgType +".png");

      if (dmyMove.getPower() == -1){                                                                //Power
        power = "-";
      } else{
        power = dmyMove.getPower() + "";
      }
      Label lblPower = new Label(power);

      if (dmyMove.getAccuracy() == -1){                                                             //The data for Accuracy has negatives when it should be null, so i handle it here
        accuracy = "-";
      } else{
        accuracy = dmyMove.getAccuracy() + "";
      }
      Label lblAccuracy = new Label(accuracy);                                                      //Accuracy

      Label lblPP = new Label(dmyMove.getPp() + "");                                           //PP

      Label lblPriority = new Label(dmyMove.getPriority() + "");                               //Priority

      int varGP;                                                                                    //Integer for easier navigation through GridPanes
      switch (dmyMove.getMethodLearned()){                                                          //Switch statement chooses which GridPane to add the move
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

      lblMoveIdentifier.setFont(medium);                                                            //Sets the font and format for the labels/images
      typeImage.setPreserveRatio(true);
      typeImage.setFitHeight(25);
      dmgClass.setPreserveRatio(true);
      dmgClass.setFitHeight(25);
      lblPower.setFont(medium);
      lblAccuracy.setFont(medium);
      lblPP.setFont(medium);
      lblPriority.setFont(medium);

      GridPane moveData = new GridPane();

      moveData.getColumnConstraints().add(columnOne);                                               //Set the columnConstraints for the columns
      moveData.getColumnConstraints().add(columnTwo);
      moveData.getColumnConstraints().add(columnThree);
      moveData.getColumnConstraints().add(columnFour);
      moveData.getColumnConstraints().add(columnFive);
      moveData.getColumnConstraints().add(columnSix);

      moveData.add(lblMoveIdentifier, 0, 0);                                    //Add the data for the moves to the Labels
      moveData.add(typeImage, 1, 0);
      moveData.add(dmgClass, 2, 0);
      moveData.add(lblPower, 3, 0);
      moveData.add(lblAccuracy, 4, 0);
      moveData.add(lblPP, 5, 0);
      moveData.add(lblPriority, 6, 0);

      gridPaneArray[varGP].getChildren().add(moveData);                                             //Add the move Labels to the appropriate GridPane

      Label moveDescription = new Label(dmyMove.getEffectDescription());                            //Adds the description as a second row, will be phased out
      moveDescription.setFont(small);
      moveDescription.setPadding(new Insets(0, 0, 20, 10));
      gridPaneArray[varGP].getChildren().add(moveDescription);                                      //Move Descriptions
    }
    vBoxMovesLevelUp.getChildren().add(layoutLvl);                                                  //Finally add the gridPanes to the parent VBox
    vBoxMovesLevelUp.getChildren().add(layoutTM);
    vBoxMovesLevelUp.getChildren().add(layoutTutor);
    vBoxMovesLevelUp.getChildren().add(layoutEgg);

    ///////////////////////////////////// Pokemon Data /////////////////////////////////////
    lblSpeciesName.setText(pokemonName.substring(0, 1).toUpperCase() +                        //Sets various labels
        pokemonName.substring(1));
    lblSpeciesNumber.setText("#"+Integer.toString(currentSpeciesID));
    lblHeight.setText(Double.toString(newPoke.getHeight()/10) + "m");
    lblWeight.setText(Double.toString(newPoke.getWeight()/10) + "kg");

    ///////////////////////////////////// Base Stats /////////////////////////////////////
    gridPaneBST.setPadding(new Insets(10, 10, 10, 10));                       //Sets the Labels for the Base Stats GridPane
    lblBaseHP.setText(Integer.toString(dummyBSTArray[0]));
    lblBaseAttack.setText(Integer.toString(dummyBSTArray[1]));
    lblBaseDefense.setText(Integer.toString(dummyBSTArray[2]));
    lblBaseSpecialAttack.setText(Integer.toString(dummyBSTArray[3]));
    lblBaseSpecialDefense.setText(Integer.toString(dummyBSTArray[4]));
    lblBaseSpeed.setText(Integer.toString(dummyBSTArray[5]));

    ///////////////////////////////////// Buttons /////////////////////////////////////
    if (currentPokemonID == 1){                                                                     //Disables next button when there are none left, Disables previous when on the first Pokemon
      btnPrevious.setDisable(true);
      btnNext.setDisable(false);
    } else if (currentPokemonID == 802){
      btnPrevious.setDisable(false);
      btnNext.setDisable(true);
    } else{
      btnNext.setDisable(false);
      btnPrevious.setDisable(false);
    }

    ///////////////////////////////////// Rectangles /////////////////////////////////////
    Rectangle[] dummy = {rectHP, rectAtt, rectDef, rectSpAtt, rectSpDef, rectSpeed};                //Scales the rectangles based on the Base Stat in question
    for (int i = 0; i < 6; i++){
      dummy[i].setWidth(2*dummyBSTArray[i]);
      dummy[i].setFill(Color.rgb(255 - dummyBSTArray[i], 0,                              //Changes the color of the rectangles based Stats, may be replaced with a tiered color system
          255 - dummyBSTArray[i]));
    }

    ///////////////////////////////////// Egg Groups /////////////////////////////////////
    lblEggGroupOne.setText(dummyEggGroupArray.get(0));                                              //More Label work
    if (dummyEggGroupArray.size() >1){                                                              //Same as types, dealing with Pokemon who have more than one Egg Group
      lblEggGroupTwo.setText(dummyEggGroupArray.get(1));
    } else{
      lblEggGroupTwo.setText("");
    }
    firstIteration = false;
  }

  /**
   * Handles the pressing of the Previous button
   * @param event - button being pressed
   */
  @FXML
  private void handlePreviousButtonAction(ActionEvent event){
    currentPokemonID = currentSpeciesID-1;
    currentSpeciesID--;
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
    currentPokemonID = rand.nextInt(801)+1;
    initialize();
  }

  /**
   * Handles the pressing of the Next button
   * @param event - button being pressed
   */
  @FXML
  private void handleNextButtonAction(ActionEvent event){
    currentPokemonID = currentSpeciesID+1;
    currentSpeciesID++;
    initialize();
  }
}