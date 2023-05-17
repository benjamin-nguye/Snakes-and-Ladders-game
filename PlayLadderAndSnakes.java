// -----------------------------------------------------
// Assignment 1
// Written by: Alexandre Payumo 40249777, Benjamin Nguyen 40242621
// -----------------------------------------------------

//NOTE: THIS PROGRAM WORKS BEST IN VISUALSTUDIO CODE. This program makes use of colour which is not
//shown in Eclipse!!

//This program is meant to simulate a snakes and ladders game. In this driver file, must begin by
//greeting the user, and asking for certain parameters such as the number of players, their names,
//and which colour they will be using. Then, this file determines who starts the snakes and ladders game,
//displays the board and ultimately ends with something winning the game.

import java.util.Scanner;

public class PlayLadderAndSnakes {
    public static void main(String[] args) {
        
        //Create scanner instance to collect user input
        Scanner kb = new Scanner(System.in);

        //Create LadderAndSnakes instance to use helper functions
        LadderAndSnakes las = new LadderAndSnakes();

        //Welcome player to the snakes and ladders game
        System.out.println("\n~~~~~~~~~ Welcome to this game of Snakes and Ladders ~~~~~~~~~~");

        //Tell user not to use Eclipse
        System.out.println("This program works best in VisualStudio code since it uses colours!!! Eclipse not recommended");
        
        //Give instructions to user
        System.out.println("\nPlease maximize console size for optimal viewing experience.");

        //Prompt user for number of player player and collect their input
        System.out.print("\nStart by entering the number of players (maximum: 6): ");
        int nbOfPlayers = kb.nextInt();

        //Initialize the number of players to 2 if the user chose a number of players above 2
        if (nbOfPlayers > 2) {
            System.out.println("Initialization was attempted for " + nbOfPlayers + " players; however, this is only expected for an extended version of the game. Value will be set to 2.");
            nbOfPlayers = 2;
        }
        //Exit the game of the user chose a number of players below 2
        else if (nbOfPlayers < 2) {
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            System.exit(0);
        }

        //Create an array that can contain all of the players
        Player[] playerArray = new Player[nbOfPlayers];
        
        //Create an instance of the colour class to use colours
        Colour colours = new Colour();

        //Initializing playerList
        for (int i = 0; i < nbOfPlayers; i++){
            
            System.out.println();
            //Ask each player for their name and save it in a variable
            System.out.print("Player " + (i+1) + ", what is your name? ");
            String playerName = kb.next();

            //Ask each player for a colour and save it in a variable
            System.out.println(playerName + ", what colour do you choose?");
            System.out.print("Enter");

            //Print the colours that are still available to the user
            for (int j=0; j<colours.getColourListSize(); j++) {
                if (j == 0) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(", ");
                }
                System.out.print(colours.getFromColourList(j));
            } System.out.print(": ");
            
            //Selecting the proper colour for each player's input
            String playerColour = kb.next().toLowerCase();
            String chosenColour = colours.getFromColourDict(playerColour);

            //Initializing a player with the inputted name and colour
            playerArray[i] = new Player(playerName, chosenColour);

            //Remove colour from colour dictionary so that it can't be chosen twice
            colours.removeInColourDict(playerColour);
            colours.removeInColourList(playerColour);
        }

        //Display starting message
        System.out.print("\nOkay ");
        for (int i = 0; i < playerArray.length; i++){
            System.out.print(playerArray[i].getColour() + playerArray[i].getName() + Colour.Reset + ", ");
        }
        System.out.println("let's play!");

        //Print to the player that the order determining sequence has begun
        System.out.println("\nNow deciding which player will start playing;");

        //Initializing isTie that can be looped over until there is no tie
        boolean isTie = true;

        //Keep track of number of ties so that it can later be displayed to the user
        int tieCounter = 1;

        //Redo tie determining sequence until there is no tie
        while (isTie == true) {
            //Prompt user to press enter
            System.out.println("\nPress [ENTER] to roll dice");
            kb.nextLine(); 

            //Roll the dice for each player and print it to the console
            for (int i = 0; i < playerArray.length; i++) {
                playerArray[i].setOrderRoll(las.flipDice());
                System.out.println(playerArray[i].getColour() + playerArray[i].getName() + Colour.Reset + " got a dice value of " + playerArray[i].getOrderRoll());
            }

            //Sort the playerArray by diceRolls using selection sort
            for (int i = 0; i < playerArray.length - 1; i++) {
                int max = i;
                for (int j = i + 1; j < playerArray.length; j++) {
                    if (playerArray[j].getOrderRoll() > playerArray[i].getOrderRoll()) {
                        max = j;
                    }
                }
                if (max != i) {
                    Player temp = playerArray[i];
                    playerArray[i] = playerArray[max];
                    playerArray[max] = temp;
                }
            }
            //If two adjacent members of the playerArray have the same dice roll, then there is still a tie
            //Otherwise, set isTie to false and exit the order determining sequence
            for (int i = 0; i < playerArray.length - 1; i++) {
                if (playerArray[i].getOrderRoll() == playerArray[i + 1].getOrderRoll()) {
                    System.out.println("A tie was achieved between " + playerArray[i].getColour() + playerArray[i].getName() + Colour.Reset + " and " + playerArray[i+1].getColour() + playerArray[i+1].getName() + Colour.Reset + ". Attempting to break the tie");
                    isTie = true;
                    tieCounter++;
                    continue;
                }
                else {
                    isTie = false;
                }
            }
        }

        //Let user know that the order determining sequence is over
        System.out.print("Reached final decision on order of playing. ");

        //Print order of players
        for (int i = 0; i < playerArray.length; i++) {
            if (i != playerArray.length - 1) {
                System.out.print(playerArray[i].getColour() + playerArray[i].getName() + Colour.Reset + " then ");
            }
            else {
                System.out.print(playerArray[i].getColour() + playerArray[i].getName() + Colour.Reset + ". ");
            }
        }
        //Print how many ties there were
        System.out.println("It took " + tieCounter + " attemps before a decision could be made.");

        //Prompt user to press [ENTER] to continue
        System.out.println("Press [ENTER] to continue");
        kb.nextLine();

        //Displaying the colour code for the board
        System.out.println();
        System.out.println("Color code: ");
        System.out.println(Colour.Yellow + "YELLOW" + Colour.Reset + " is the finish line.");
        System.out.println(Colour.Red + "RED" + Colour.Reset + " is the head of a snake.");
        System.out.println(Colour.Green + "GREEN" + Colour.Reset + " is the bottom of a ladder.");
        
        //Prompt the user to press [ENTER] to continues
        System.out.println();
        System.out.print("Press [ENTER] to display the board.");
        System.out.println();
        kb.nextLine();

        //Start the game engine using the LadderAndSnakes class' play function        
        las.play(kb, playerArray);
    }
}

