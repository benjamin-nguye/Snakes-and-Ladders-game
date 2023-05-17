// -----------------------------------------------------
// Assignment 1
// Written by: Alexandre Payumo 40249777, Benjamin Nguyen 40242621
// -----------------------------------------------------

//This class is used to represent a player. Has attributes for the player's position, name, colour, etc.
//Also tracks if a player has won the game yet.

public class Player {
    //Declaring private attributes
    private int position;
    private String name;
    private String colour;
    private int orderRoll;
    private boolean hasWon;
    private String colourBackground;
    
    //Parametrized constructor that allows inputs for players' name and colour
    public Player(String name, String colour) {
        this.position = 0;
        this.name = name;
        this.colour = colour;
        this.orderRoll = 0;
        this.hasWon = false;
        
        //Mapping a background colour to each player
        switch(colour){
            case Colour.Red:
                this.colourBackground = Colour.BackgroundRed;
                break;
            case Colour.Blue:
                this.colourBackground = Colour.BackgroundBlue;
                break;
            case Colour.Green:
                this.colourBackground = Colour.BackgroundGreen;
                break;
            case Colour.Yellow:
                this.colourBackground = Colour.BackgroundYellow;
                break;
            case Colour.Purple:
                this.colourBackground = Colour.BackgroundPurple;
                break;
            case Colour.Cyan:
                this.colourBackground = Colour.BackgroundCyan;
                break;
        }
    }

    //Default constructor
    public Player() {
        this.position = 0;
        this.name = "";
        this.colour = "";
        this.orderRoll = 0;
        this.hasWon = false;
        this.colourBackground = "";
    }

    //This function adds a given amount to the players' current position
    public void movePlayer(int moveAmount) {
        this.position += moveAmount;
    }

    //This function handles landing depending on what tile the player has landed on
    public void handleLand(Player[] players, int[][] snakes, int[][] ladders) {
        //If the player has landed on 100, then they have won
        if (this.position == 100) {
            this.hasWon = true;
            System.out.println();
            return;
        }
        //If a player lands on a snake head, move them to the snake bottom
        for (int i = 0; i < snakes.length; i++) {
            if (this.position == snakes[i][0]) {
                this.position = snakes[i][1];
                System.out.println("; landed on a snake. Going down to square " + snakes[i][1]);
                return;
            }
        }
        //If a player has landed on the foot of a ladder, send them to the top of it
        for (int i = 0; i < ladders.length; i++) {
            if (this.position == ladders[i][0]) {
                this.position = ladders[i][1];
                System.out.println("; landed on a ladder. Going up to square " + ladders[i][1]);
                return;
            }
        }
        //If a player has landed on another player, send the other player back 1 square
        for (int i = 0; i < players.length; i++) {
            if (this.name != players[i].name && this.position == players[i].getPosition() && this.position != 0) {
                players[i].setPosition(players[i].getPosition() - 1);
                System.out.println("; now in square " + this.position);
                System.out.print(players[i].getColour() + players[i].getName() + Colour.Reset + " rolled back");
                players[i].handleLand(players, snakes, ladders);
                return;
            }
        }
        //If a player has landed passed 100, send them back how much they have overstepped 100
        if (this.position > 100) {
            int backBounce = this.position - 100;
            this.position = 100 - backBounce;
            System.out.print("; bounced");
            this.handleLand(players, snakes, ladders);
            return;
        }
        //If a player hasn't had a special land case, just print where they have landed
        System.out.println("; now in square " + this.position);
    }

    // Setter methods
    public void setPosition(int position){
        this.position = position;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setColour(String colour){
        this.colour = colour; 
    }
    public void setOrderRoll(int orderRoll) {
        this.orderRoll = orderRoll;
    }

    // Getter methods
    public int getPosition(){
        return this.position;
    }
    public String getName(){
        return this.name;
    }
    public String getColour(){
        return this.colour;
    }
    public String getBackgroundColour() {
        return this.colourBackground;
    }
    public int getOrderRoll(){
        return this.orderRoll;
    }
    public boolean getHasWon(){
        return this.hasWon;
    }

    //toString method to display this object as a String
    public String toString(){
        return "(Player name: " + this.name + "; Colour: " + this.colour + ")";
    }
  
}