import java.util.*;

public class Test
{
    static ArrayList<SetLines> list = new ArrayList<>();

    public static void main(String args[])
    {
        double m = .5;
        double b = 1;
        double xHigh = 5;
        double xLow = -5;

        list.add(new SetLines(xHigh, xLow, m,b));

        m = .5;
        b = 3;
        xHigh = 5;
        xLow = -5;

        list.add(new SetLines(xHigh, xLow, m, b));

        LinePlotGUI.launch(LinePlotGUI.class);
    }
}