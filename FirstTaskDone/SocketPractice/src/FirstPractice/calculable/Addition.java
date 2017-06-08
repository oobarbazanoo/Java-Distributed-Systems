package FirstPractice.calculable;

import java.io.Serializable;

public class Addition implements Calculable, Serializable
{
    private double firstAddendum, secondAddendum;

    public Addition(double firstAddendum, double secondAddendum)
    {
        this.firstAddendum = firstAddendum;
        this.secondAddendum = secondAddendum;
    }

    public double calc()
    {
        return firstAddendum + secondAddendum;
    }
}
