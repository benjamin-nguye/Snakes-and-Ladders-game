// -----------------------------------------------------
// Assignment 1
// Written by: Alexandre Payumo 40249777, Benjamin Nguyen 40242621
// -----------------------------------------------------

/*
 * This class defines the board of the Ladders and Snakes game
 * The board has a size (we set it at 10 because the game is usually played on a 10x10 board) but it can easily be changed
 * What makes this class special however, is that it generates positions for snakes and ladders.
 * So the players will get a different board with different ladders and snakes every game, instead of playing on the same board every time
 */


public class Board{
    // the attributes for the board: size (10x10) and the snakes and ladders
    private int size;
    // snakes and ladders are 9x2 arrays
    // There are 9 snakes and 9 ladders, and every one of those has an end point and a starting point (so 2 coordinates each)
    private int[][] snakes = new int[9][2];
    private int[][] ladders = new int[9][2];

    public Board(){
        // initialize the board size to 10
        this.size = 10;
        
        // This code initalizes the array for the snakes on the board
        // The rows are the heads of snakes and the columns are the tails
        for (int i = 0; i < snakes.length; i++){
            boolean invalid = false;
            int snakeHead;
            int snakeTail;

            // Starts by generating the head of the snake
            do {

                // uses Math.random() to generate a float between 0 and 1
                // and multiply by 97 because we don't want a snake head on 100 and 1 (99 - 2)
                // add 2 to start at 2
                snakeHead = (int) (Math.random() * 97) + 2;

                // if the tile is already a snake or a ladder, then re-generate it
                invalid = checkInArrays(this.snakes, this.ladders, snakeHead);

            } while (invalid);

            // assign this position to the head of the snake
            this.snakes[i][0] = snakeHead;

            // Then generate tail of snake
            invalid = false;
            do {
                // again uses Math.random() but the range is from 1 to the ehad of the snake (we don't want a tail higher than the head)
                snakeTail = (int) (Math.random() * (snakeHead - 1)) + 1;

                // checks if the tile is already a snake or a ladder
                invalid = checkInArrays(this.snakes, this.ladders, snakeTail);
            } while (invalid);

            // assign position to tail of snake
            this.snakes[i][1] = snakeTail;
        }

        // This code generates the positions for the ladders
        // the same logic used for the snakes is applied but in reverse (we want the top of the ladder to come after the bottom)
        for (int i = 0; i < ladders.length; i++){
            boolean invalid = false;
            int ladderBottom;
            int ladderTop;

            // generate ladder bottom
            do {

                ladderBottom = (int) (Math.random() * 98) + 1;

                // if the tile is already a snake or a ladder, then re-generate it
                invalid = checkInArrays(this.snakes, this.ladders, ladderBottom);

            } while (invalid);

            // assign posiiton to the bottom of the ladder
            this.ladders[i][0] = ladderBottom;

            //Generate top of ladder
            invalid = false;
            do {
                ladderTop = (int) (Math.random() * (100 - ladderBottom)) + ladderBottom;

                invalid = checkInArrays(this.snakes, this.ladders, ladderTop);
            } while (invalid);

            // assign position to top of ladder
            this.ladders[i][1] = ladderTop;
        }



    }

    // accessor methods
    public int getSize(){
        return this.size;
    }

    public int[][] getSnakes(){
        return this.snakes;
    }

    public int[][] getLadders(){
        return this.ladders;
    }

    // this method checks if a specified number is any of two given arrays
    // this is useful for checking if a position is already used by a snake or a ladder
    public static boolean checkInArrays(int[][] arr1, int[][] arr2, int num){
        for (int j = 0; j < arr1.length ; j++){
            for (int k = 0; k < arr1[0].length; k++){
                if (arr1[j][k] == num || arr2[j][k] == num)
                return true;  
            }
        }
        return false;
    }

    // this method checks if a value is in the rows of an array (ie if it is the head of a snake or the bottom of a ladder)
    public static boolean checkInArray(int[][] arr, int num){
        for (int j = 0; j < arr.length ; j++){
            if (arr[j][0] == num)
            return true; 
        }
        return false;
    }

    // this method checks if a value is in the rows of an array (ie if it is the tail of a snake or top of a ladder)
    public static boolean checkInArrayEnd(int[][] arr, int num){
        for (int j = 0; j < arr.length ; j++){
            if (arr[j][1] == num)
            return true; 
        }
        return false;
    }
}