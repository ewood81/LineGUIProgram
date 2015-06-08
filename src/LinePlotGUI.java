
// import libraries for use in the class
import java.util.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

// Class declaration with inheritance relationship defined
public class LinePlotGUI extends Application
{
    // Private instance of the Axis inner class
    private Axis axis = new Axis(400,400,-10,10,1,-10,10,1);

    // Private ArrayList declared with a single ArrayList argument
    private ArrayList<Draw> getElements(ArrayList <SetLines> l)
    {
        // Local ArrayList created which will be returned on the methods exit
        ArrayList<Draw> list = new ArrayList<>();
        // Local integer variable created to function as an iterator
        int count = 0;

        // While loop that continues until the iterator equals the size of the passed in ArrayList
        while(count < l.size())
        {
            // Add a new Draw object to the local ArrayList by calling methods found in the SetLines object
            list.add(new Draw(l.get(count).getXHigh(), l.get(count).getXLow(),
                    l.get(count).getM(), l.get(count).getB()));
            // Increment the counter
            count++;
        }

        // Return the local ArrayList to the caller
        return list;
    }

    // Over-ridden start() method from the Application class
    @Override
    public void start(final Stage primaryStage)
    {
        // Declare a local ArrayList of Draw objects
        ArrayList<Draw> list;

        // Instantiate the ArrayList with the return value of a call to the getElements() local method,
        // supplied with a single argument from the Main class
        list = getElements(Main.list);

        // Creation of a new StackPane object supplied with an Axis object argument
        StackPane layout = new StackPane(axis);

        // Calls to methods of the StackPane class that add the Draw objects to the StackPane,
        // as well as the setting of a couple of attributes
        layout.getChildren().addAll(list);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgb(255, 255, 255);");

        // Calls to methods of the Stage class to set attributes of the GUI window
        primaryStage.setTitle("Slope Intercept");
        primaryStage.setScene(new Scene(layout, Color.rgb(255, 255, 255)));
        primaryStage.show();
    }

    // Inner class that creates the Cartesian graph for the GUI
    class Axis extends Pane
    {
        // Private instance variables of the NumberAxis class
        private NumberAxis y;
        private NumberAxis x;

        // Constructor that takes eight arguments
        public Axis(int width, int height, double xLow, double xHigh,
                    double xTick, double yLow, double yHigh, double yTick)
        {
            // Call to the setSizes() method supplied with two arguments
            setSizes(height, width);
            // Instantiate the NumberAxis field variables with the return values of calls to the setX and setY
            // methods respectively
            x = setX(xLow, xHigh, xTick, width);
            y = setY(yLow, yHigh, yTick, height, width);

            // Call to the getChildren method to add the NumberAxis variables to the GUI
            getChildren().setAll(x,y);
        }

        // Method that sets the size of the Axis node
        private void setSizes(int height, int width)
        {
            // Call to methods to set attributes of the Axis node
            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(width, height);
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        }

        // Method that takes four arguments and instantiates a new NumberAxis object
        private NumberAxis setX(double xLow, double xHigh, double xTick, int width)
        {
            // Instantiate a new NumberAxis object and set it's attributes
            x = new NumberAxis(xLow, xHigh, xTick);
            x.setSide(Side.BOTTOM);
            x.setMinorTickVisible(false);
            x.setPrefWidth(width);
            x.setLayoutY(width/2);

            return x;
        }

       // Method that takes in five arguments and instantiates a new NumberAxis object
        private NumberAxis setY(double yLow, double yHigh, double yTick, int height, int width)
       {
           // Instantiate a new NumberAxis object and set it's attributes
           y = new NumberAxis(yLow, yHigh, yTick);
           y.setSide(Side.LEFT);
           y.setMinorTickVisible(false);
           y.setPrefHeight(height);
           y.layoutXProperty().bind(Bindings.subtract((width / 2) + 1,y.widthProperty()));

           return y;
       }

        // Method that returns a NumberAxis field variable
        public NumberAxis getX()
        {
            return this.x;
        }

        // Method that returns a NumberAxis field variable
        public NumberAxis getY()
        {
            return this.y;
        }
    }

    // Inner class that creates linear curves to be displayed on the GUI
    class Draw extends Pane
    {
        // Constructor that takes in four arguments
        public Draw(double xHigh, double xLow, double m, double b)
        {
            // Creation of a new Line object that will serve as the linear curve shown on the GUI
            Line line = new Line();

            // Creation and instantiation of local variables used to determine the starting and ending
            // coordinates of a single line.  The calculations used take advantage of the fact that a
            // linear curve only requires the starting and ending coordinates in order to be mapped properly
            // to a Cartesian graph.
            double yStart = m * xLow + b;
            double xStart = ((yStart - b)/m);
            double yEnd = m * xHigh + b;
            double xEnd = ((yEnd - b)/m);

            // Call to methods of the Line class to set the starting and ending points for the line
            line.setStartX(mapX(xStart, axis));
            line.setStartY(mapY(yStart, axis));
            line.setEndX(mapX(xEnd, axis));
            line.setEndY(mapY(yEnd, axis));
            // Call to methods of the Line class to set attributes of the line to be drawn
            line.setStroke(Color.TEAL);
            line.setStrokeWidth(2);

            // Methods that set the attributes of the Draw nodes
            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(axis.getPrefWidth(), axis.getPrefHeight());
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            // Method that adds to the Draw and Axis objects to the GUI
            getChildren().setAll(line,axis);
        }

        // Method that determines the starting point of the X value of
        // linear curve within the GUI window
        private double mapX(double x, Axis axis)
        {
            double tx = axis.getPrefWidth() / 2;
            double sx = axis.getPrefWidth() / (axis.getX().getUpperBound() - axis.getX().getLowerBound());

            return x * sx + tx;
        }

        // Method that determines the starting point of the Y value of
        // linear curve within the GUI window
        private double mapY(double y, Axis axis) {
            double ty = axis.getPrefHeight() / 2;
            double sy = axis.getPrefHeight() / (axis.getY().getUpperBound() - axis.getY().getLowerBound());

            return -y * sy + ty;
        }
    }
}





