package MainProject;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;

public class Controller {
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
  @FXML
  private Label lblEggGroupOne;
  @FXML
  private Label lblEggGroupTwo;

  ///////////////////////////////////// Images /////////////////////////////////////
  @FXML
  private ImageView imgNormalSprite;
  @FXML
  private ImageView imgShinySprite;


  public void initialize() {
    PokemonData newPoke = new PokemonDatabaseWork().getSinglePokemonData(258);
    int pokemonID = newPoke.getSpeciesID();
    int[] dummyBSTArray = newPoke.getBaseStats();
    ArrayList<String> dummyEggGroupArray = newPoke.getEggGroups();
    Image imgNormal = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/" + pokemonID + ".png");
    Image imgShiny = new Image("file:///C:/Users/chris/OneDrive/Pokemon 2018 Test Files/sprites/pokemon/shiny/" + pokemonID + ".png");

    lblSpeciesName.setText(newPoke.getSpeciesName());
    lblSpeciesNumber.setText(Integer.toString(pokemonID));
    lblHeight.setText(Double.toString(newPoke.getHeight()));
    lblWeight.setText(Double.toString(newPoke.getWeight()));

    imgNormalSprite.setImage(imgNormal);
    imgShinySprite.setImage(imgShiny);

    lblBaseHP.setText(Integer.toString(dummyBSTArray[0]));
    lblBaseAttack.setText(Integer.toString(dummyBSTArray[1]));
    lblBaseDefense.setText(Integer.toString(dummyBSTArray[2]));
    lblBaseSpecialAttack.setText(Integer.toString(dummyBSTArray[3]));
    lblBaseSpecialDefense.setText(Integer.toString(dummyBSTArray[4]));
    lblBaseSpeed.setText(Integer.toString(dummyBSTArray[5]));

    rectHP.setWidth(2*dummyBSTArray[0]);
    rectAtt.setWidth(2*dummyBSTArray[1]);
    rectDef.setWidth(2*dummyBSTArray[2]);
    rectSpAtt.setWidth(2*dummyBSTArray[3]);
    rectSpDef.setWidth(2*dummyBSTArray[4]);
    rectSpeed.setWidth(2*dummyBSTArray[5]);


    lblEggGroupOne.setText(dummyEggGroupArray.get(0));
    if (dummyEggGroupArray.size() >1){
      lblEggGroupTwo.setText(dummyEggGroupArray.get(1));
    }else{
      lblEggGroupTwo.setText("");
    }
  }
}