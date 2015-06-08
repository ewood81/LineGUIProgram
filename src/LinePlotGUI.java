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

public class LinePlotGUI extends Application
{
    private Axis axis = new Axis(400,400,-10,10,1,-10,10,1);

    private ArrayList<Draw> getElements(ArrayList <SetLines> l)
    {
        ArrayList<Draw> list = new ArrayList<>();
        int count = 0;

        while(count < l.size())
        {
            list.add(new Draw(l.get(count).getXHigh(), l.get(count).getXLow(), l.get(count).getM(), l.get(count).getB()));
            count++;
        }

        return list;
    }

    @Override
    public void start(final Stage primaryStage)
    {
        ArrayList<Draw> list;

        list = getElements(Test.list);

        StackPane layout = new StackPane(axis);

        layout.getChildren().addAll(list);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgb(255, 255, 255);");

        primaryStage.setTitle("Slope Intercept");
        primaryStage.setScene(new Scene(layout, Color.rgb(255, 255, 255)));
        primaryStage.show();
    }

    class Axis extends Pane
    {
        private NumberAxis y;
        private NumberAxis x;

        public Axis(int width, int height, double xLow, double xHigh,
                    double xTick, double yLow, double yHigh, double yTick)
        {
            setSizes(height, width);
            x = setX(xLow, xHigh, xTick, width);
            y = setY(yLow, yHigh, yTick, height, width);

            getChildren().setAll(x,y);
        }

        private void setSizes(int height, int width)
        {
            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(width, height);
            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        }

        private NumberAxis setX(double xLow, double xHigh, double xTick, int width)
        {
            x = new NumberAxis(xLow, xHigh, xTick);
            x.setSide(Side.BOTTOM);
            x.setMinorTickVisible(false);
            x.setPrefWidth(width);
            x.setLayoutY(width/2);

            return x;
        }

       private NumberAxis setY(double yLow, double yHigh, double yTick, int height, int width)
       {
           y = new NumberAxis(yLow, yHigh, yTick);
           y.setSide(Side.LEFT);
           y.setMinorTickVisible(false);
           y.setPrefHeight(height);
           y.layoutXProperty().bind(Bindings.subtract((width / 2) + 1,y.widthProperty()));

           return y;
       }

        public NumberAxis getX()
        {
            return this.x;
        }

        public NumberAxis getY()
        {
            return this.y;
        }
    }

    class Draw extends Pane
    {
        public Draw(double xHigh, double xLow, double m, double b)
        {
            Line line = new Line();

            double yStart = m * xLow + b;
            double xStart = ((yStart - b)/m);
            double yEnd = m * xHigh + b;
            double xEnd = ((yEnd - b)/m);

            line.setStartX(mapX(xStart, axis));
            line.setStartY(mapY(yStart, axis));
            line.setEndX(mapX(xEnd, axis));
            line.setEndY(mapY(yEnd, axis));
            line.setStroke(Color.TEAL);
            line.setStrokeWidth(2);

            setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            setPrefSize(axis.getPrefWidth(), axis.getPrefHeight());

            setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
            getChildren().setAll(line,axis);
        }

        private double mapX(double x, Axis axis)
        {
            double tx = axis.getPrefWidth() / 2;
            double sx = axis.getPrefWidth() / (axis.getX().getUpperBound() - axis.getX().getLowerBound());

            return x * sx + tx;
        }

        private double mapY(double y, Axis axis) {
            double ty = axis.getPrefHeight() / 2;
            double sy = axis.getPrefHeight() / (axis.getY().getUpperBound() - axis.getY().getLowerBound());

            return -y * sy + ty;
        }
    }
}





