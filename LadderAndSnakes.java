// -----------------------------------------------------
// Assignment 1
// Written by: Alexandre Payumo 40249777, Benjamin Nguyen 40242621
// -----------------------------------------------------

//This class contains many helper functions that help with the functionality of the snakes and ladders game.
//Mostly to declutter the driver. Contains functions to display the board, flip the dice, and some getter methods.
import java.util.Random;
import java.util.Scanner;

public class LadderAndSnakes {
    //Initializing a new Board that will be used in the game
    Board laddersAndSnakesBoard = new Board();
    //Getting the board size
    private int[][] board = new int[laddersAndSnakesBoard.getSize()][laddersAndSnakesBoard.getSize()];
    
    //Function to display the board
    public void displayBoard(Player[] pArray) {
        //Print a number for each board tile
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                board[i][j] = i*10 + j + 1;
            }
           }
    
           for (int i = board.length-1; i > -1; i--){
            for (int j = board.length-1; j > -1; j--){
                boolean stop = false;
                //Print a different background colour depending on if there is a player situated there
                for (int k=0; k<pArray.length; k++){
                    if (board[i][j] == pArray[k].getPosition()){
                        System.out.print(pArray[k].getBackgroundColour() + board[i][j] + Colour.Reset + "\t");
                        stop = true;
                        break;
                    }  
                }

                if (stop)
                    continue;
                //Printing different colour for the end tile
                else if (board[i][j] == 100)
                    System.out.print(Colour.Yellow + board[i][j] + Colour.Reset + "\t");
                //Printing different colour for the snake head tile
                else if (Board.checkInArray(this.laddersAndSnakesBoard.getSnakes(), board[i][j]))
                    System.out.print(Colour.Red + board[i][j] + Colour.Reset + "\t");
                //Printing different colour for the ladder foot tile
                else if (Board.checkInArray(this.laddersAndSnakesBoard.getLadders(), board[i][j]))
                    System.out.print(Colour.Green + board[i][j] + Colour.Reset + "\t");
                //Printing some space between each tile
                else
                    System.out.print(board[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
           }
    }

    public void play(Scanner kb, Player[] playerArray) {
        this.displayBoard(playerArray);

        boolean hasWon = false;
        int turnCounter = 0;
        int diceRoll;

        //While loop that restarts until a player has won the game
        while (hasWon == false) {
            System.out.println("\nPress [ENTER] to go to the next turn...");
            kb.nextLine();
            //The condition below allows to alternate between players rolling
            if (turnCounter > playerArray.length - 1) {
                turnCounter -= playerArray.length;
            }
            //Rolling dice
            diceRoll = this.flipDice();
            System.out.print(playerArray[turnCounter].getColour() + playerArray[turnCounter].getName() + Colour.Reset + " got a dice value of " + diceRoll);
            //Moving player and handling the land depending on what the player landed on
            playerArray[turnCounter].movePlayer(diceRoll);
            playerArray[turnCounter].handleLand(playerArray, this.getSnakes(), this.getLadders());
            //The condition below is only triggered if a player has won
            if (playerArray[turnCounter].getHasWon() == true) {
                hasWon = true;
                System.out.println(playerArray[turnCounter].getColour() + playerArray[turnCounter].getName() + Colour.Reset + " has won!");
            }
            System.out.print("Press [d] to display the board, or ignore with [ENTER]: ");
            String dp = kb.nextLine();
            if (dp.equals("d")){
                System.out.println(); this.displayBoard(playerArray);
            }
            turnCounter++;
        }
    }

    //Method to flip the die
    public int flipDice() {
        Random random = new Random();
        //random.nextInt() method is an easy way to generate a random dice flip
        return random.nextInt(6) + 1;
    }

    //Getter method for the snake positions array
    public int[][] getSnakes() {
        return laddersAndSnakesBoard.getSnakes();
    }

    //Getter method for the ladders position array
    public int[][] getLadders() {
        return laddersAndSnakesBoard.getLadders();
    }
}
