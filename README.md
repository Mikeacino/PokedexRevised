# PokédexRevised - Pokédex 2.000 
This project is a remake of an earlier project made when I first started coding, a Pokédex based on the Pokémon series of games. 
A Pokédex is an encyclopedia of the creatures in the Pokémon series of games and shows.


Author:
	Michael Carracino


Project Goal:
	There were three main goals of this project.
		1.) To create an improved Pokédex, with more features.
		2.) To practice using JavaFX GUI elements.
		3.) To practice using SQL with a Derby database.


Built With:
	Java - IntelliJ IDEA 2018.2.2
SceneBuilder
Apache Derby (Database)


Notes on current version:
	The current version covers the first 802 Pokémon and their alternate forms.
The source files from which I built my database are incomplete, with some files almost completely blank, some images missing or poor quality, and some data out of date. 
The errors in the database can cause certain sections of the program to not function properly, such as missing image files, incorrect data, and certain Pokémon forms may not be available to view.


Future for this project:
Due to the limitations of the source files, I do not plan on continuing this project any further.
I plan on refining the classes in this project for future use and for my own edification.


Classes in Project:
Ability.Java
Outlines what a basic ability should be.
Controller.Java
Adjusts the scene and adds GUI elements where needed
Main.Java
Standard main method, calls the Controller and FXML to build the scene.
PokedexWindow.fxml
Built using SceneBuilder and edited manually where needed.
PokemonData.Java
Class for the Pokédex specifically. Implements PokemonEssentials and PokemonDescription
PokemonDatabaseWork.Java
This class uses a rowSet to pull the data out of the database and into classes to be used
PokemonDescription.Java - Interface
Contains data for a Pokemon that is not essential to battle, but may be good to know in a Pokedex
PokemonEssentials.Java - Interface
Contains only the data that would be needed in battle calculations for a Pokemon
PokemonMove.Java
Contains all the data for a Pokémon’s move.

	
Acknowledgements:
pokekotlin-master 
This was the source of my database data files, downloaded off of GitHub
Serebii.net
Provided a lot of inspiration for some of the design, and for checking my data
Smogon.com
Also provided much inspiration, especially for the base stat rectangles
