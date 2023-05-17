// -----------------------------------------------------
// Assignment 1
// Written by: Alexandre Payumo 40249777, Benjamin Nguyen 40242621
// -----------------------------------------------------

// This class implements the COLOUR feature of our game using the ASCII colour codes supported by MOST terminals
// We developped this game in VS Code, where the console supports ASCII colour codes,
// however not all IDE's support this feature (although most do), such as ECLIPSE
// This class is meant to keep track of colours so that the players and special board positions can
// be displayed uniquely. This class also contains a dictionary that maps the player's colour input
// to a certain colour.

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

public class Colour {
    //Codes for text colour so that we don't have to type out the ASCII codes every time
    public static final String Reset = "\u001b[0m";
    public static final String Red = "\u001b[31m";
    public static final String Blue = "\u001b[34m";
    public static final String Yellow = "\u001b[33m";
    public static final String Purple = "\u001b[35m";
    public static final String Green = "\u001b[32m";
    public static final String Cyan = "\u001b[36m";

    //Codes for text background colour
    public static final String BackgroundRed = "\u001b[41m";
    public static final String BackgroundBlue = "\u001b[44m";
    public static final String BackgroundYellow = "\u001b[43m";
    public static final String BackgroundPurple = "\u001b[45m";
    public static final String BackgroundGreen = "\u001b[42m";
    public static final String BackgroundCyan = "\u001b[46m";


    // These are the dictionaries and lists
    // colourList is used for when the player chooses their colour
    // making it into a list allows us to more easily remove elements when needed
    // (eg. when player 1 chooses red, then player 2 shouldn't have the option of choosing red)
    // colourDict assigns the keyboard inputs to the right colours
    // matchingDict merges the two, assigning the keyboard inputs to the String used for selecting colour by the players

    private List<String> colourList = new ArrayList();
    private Hashtable<String, String> colourDict = new Hashtable<String, String>();
    private Hashtable<String, String> matchingDict = new Hashtable<String, String>();

    public Colour(){
        //Mapping key inputs to a certain colour        
        this.colourDict.put("r", Red);
        this.colourDict.put("b", Blue);
        this.colourDict.put("g", Green);
        this.colourDict.put("y", Yellow);
        this.colourDict.put("p", Purple);
        this.colourDict.put("c", Cyan);

        //Strings that can be used in the driver
        this.colourList.add("[r] for " + Red + "RED" + Reset);
        this.colourList.add("[b] for " + Blue + "BLUE" + Reset);
        this.colourList.add("[g] for " + Green + "GREEN" + Reset);
        this.colourList.add("[y] for " + Yellow + "YELLOW" + Reset);
        this.colourList.add("[p] for " + Purple + "PURPLE" + Reset);
        this.colourList.add("[c] for " + Cyan + "CYAN" + Reset);

        //Mapping inputs to elements in the dictionary
        this.matchingDict.put("r", "[r] for " + Red + "RED" + Reset);
        this.matchingDict.put("b", "[b] for " + Blue + "BLUE" + Reset);
        this.matchingDict.put("g", "[g] for " + Green + "GREEN" + Reset);
        this.matchingDict.put("y", "[y] for " + Yellow + "YELLOW" + Reset);
        this.matchingDict.put("p", "[p] for " + Purple + "PURPLE" + Reset);
        this.matchingDict.put("c", "[c] for " + Cyan + "CYAN" + Reset);
    }
    
    //Getter methods
    public String getFromColourList(int index){
        return this.colourList.get(index);
    }

    public String getFromColourDict(String colour){
        return this.colourDict.get(colour);
    }

    public int getColourListSize(){
        return this.colourList.size();
    }

    //Method to remove elements from the colour dictionary
    public void removeInColourDict(String colour){
        this.colourDict.remove(colour);
    }

    //Method to remove elements from the colour list
    public void removeInColourList(String colour){
        this.colourList.remove(this.matchingDict.get(colour));
        this.matchingDict.remove(colour);
    }

}
