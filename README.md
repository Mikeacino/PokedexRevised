# PokedexRevised - Pokedex 2.000 
  A more thought out pokedex, and hopefully a battle calculator down the line.
  Currently only has all Pokemon from Bulbasaur(1) to Marshadow(802)

List of Classes in Project:
  Ability.Java
    Outlines what an ability xhould have
    
  Controller.Java
    Controller to adjuct the scene and add data from the database to the scene
    
  FullPokemonList.Java
    Currently unused until I finish adjusting it and applying it
    
  Main.Java
    Standard Main, calls the Controller and FXML
    
  pokedexWindow.fxml
    FXML file built mainly by SceneBuilder
    
  PokemonData.Java
    Class for the Pokedex specifically. Implements PokemonEssentials and PokemonDescription
    
  PokemonDatabaseWork.Java
    This class uses a rowSet to pull the data out of the database and into classes to be used
  
  PokemonDescription.Java - Interface
    Contains data for a Pokemon that is not essential to battle, but may be good to know in a Pokedex
  
  PokemonEssentials.Java - Interface
    Contains only the data that would be needed in battle calculations for a Pokemon
  
  PokemonMove.Java
    Contains all the data for a move.

Built With:
  IntelliJ
  SceneBuilder
  Apache Derby (Database)
  
Authors:
  Michael Carracino
  
Acknowledgements:
  pokekotlin-master 
    This was the source of my database data files, downloaded off of GitHub
  Serebii.net
    Provided a lot of inspiration for some of the design, and for checking my data
  Smogon.com
    Also provided much inspiration, especially for the base stat rectangles
