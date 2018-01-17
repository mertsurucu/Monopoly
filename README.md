# Monopoly
Text based Monopoly game (U.K version)
I was in my first year in Computer Engineering while I was implementing this with Java language.

I am adding assignment manual and needed input files(as an example) which are mentioned in manual to my repo. Please read the assignment manual before execute the code with my or your input files.

This program is a simple game of Monopoly but without visualization. And there is 2 different Players. 40 different squares players can lands. Those are Properties(Land, Railroads, Company), Chance Cards, Community Cards, Jail, Go To Jail Square, Tax Square, Go Square, Free Parking Square. And those squares have different features. Players do not dice in the game, their moves writes in command text file. They play according the text file and also program produce a new text file(output.txt file). This output file says every move on the game and everything that happened.


#System Chart
INPUT                 PROGRAMS        OUTPUT
command.txt           Main.java       output.txt
list.json
propert.json


I designed main class for read the commands from txt and send to the operation class.Besides, i designed other methods. Here are my methods;
Main.java: Program start to running this class. This class does not complicated. Main class has a main method which get an argument (command.txt).And converts it to array list.
And create object of Square class and send it to Square class by using constructor.
Square.java: This class purpose to define squares features and each dice player’s money and location change. Every square has different feature so that I create this class to define square feature and change the variables according to that.
Read.java: This class read a text file line by line and returns values to array. And also this class read 2 different Json file with help of json simple.jar. The class have two methods.
WriteFile.java: This class purpose to write data from other class in created text files
This method get a string variable for writing text file.
Player.java: Player class is accommodate the players moneys, locations, properties, railroad quantities.
