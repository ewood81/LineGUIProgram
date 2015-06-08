
public class SetLines
{
    private double xHigh;
    private double xLow;
    private double m;
    private double b;

    public SetLines(double xHigh, double xLow, double m, double b)
    {
        this.xHigh = xHigh;
        this.xLow = xLow;
        this.m = m;
        this.b = b;
    }

    public double getXHigh(){return this.xHigh;}
    public double getXLow(){return this.xLow;}
    public double getM(){return this.m;}
    public double getB(){return this.b;}
}