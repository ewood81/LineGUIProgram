/*
    Author note:
    This class functions as a way of hiding most of the implementation details from the
    user.  The purpose of this software is for a user to write a simple program that
    generates one or more linear curves, which are then displayed on a graphical user
    interface. Due to the simplicity required for the Main class's implementation, a data
    class (an admitted code smell) was necessary to allow the user to avoid having to delve
    too deeply into the details of object orientation and data structures.
 */


// Declaration of class SetLines
public class SetLines
{
    // Declare private field variables to hold values of the class
    private double xHigh;
    private double xLow;
    private double m;
    private double b;

    // Constructor for class that takes in four arguments
    public SetLines(double xHigh, double xLow, double m, double b)
    {
        // Assign each argument to it's corresponding field variable
        this.xHigh = xHigh;
        this.xLow = xLow;
        this.m = m;
        this.b = b;
    }

    // Four methods that simply return the value of the named field variables
    public double getXHigh(){return this.xHigh;}
    public double getXLow(){return this.xLow;}
    public double getM(){return this.m;}
    public double getB(){return this.b;}
}