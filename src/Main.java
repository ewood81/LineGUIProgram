/**
 * @author Edward Wood
 * @version 1.0 6/7/2015
 *
 */

// Imported libraries
import java.util.*;

// Class declaration
public class Main
{
    // Static ArrayList declared to store SetLines objects; these will be
    // used by the LinePlotGUI class when it's start method executes
    public static ArrayList<SetLines> list = new ArrayList<>();

    // Main method declared
    public static void main(String args[])
    {
        // Field variables declared to hold the various values needed to create a line
        double m = .5;
        double b = 1;
        double xHigh = 5;
        double xLow = -5;

        // Call to the add() function of the ArrayList to add a new SetLines object to the list
        list.add(new SetLines(xHigh, xLow, m,b));

        // Reassign the line's variables to new values
        m = .5;
        b = 3;
        xHigh = 5;
        xLow = -5;

        // Another call to the add() function of the ArrayList to add a new SetLines object to the list
        list.add(new SetLines(xHigh, xLow, m, b));

        // Call to the launch() function to initialize and run the GUI class and Runtime
        LinePlotGUI.launch(LinePlotGUI.class);
    }
}

