package equations;

public class Equations {

    public double getValue(int numOfFunction, double x){
        switch (numOfFunction){
            case (1):
                return -(x * x * x) - x * x + x + 3;
            case (2):
                return x * x;
            case (3):
                return 3 / x + x;
            default:
                return 0;
        }
    }
}
