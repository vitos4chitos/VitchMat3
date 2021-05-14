package methods;

import equations.Equations;
import io.Output;

public class TrapeziumMethod {

    private int numOfFunction;
    private double eps;
    private double left;
    private double right;
    private int n = 4;
    private Equations equations;
    private Output output;

    public TrapeziumMethod(int numOfFunction, double eps, double left, double right){
        this.eps = eps;
        this.left = left;
        this.numOfFunction = numOfFunction;
        this.right = right;
        equations = new Equations();
        output = new Output();
    }

    public void solve(){
        double h = (left - right) / n;
        double x = left;
        double sum = equations.getValue(numOfFunction, x) / 2;
        for (int i = 1; i < n; i++) {
            x += h;
            sum += equations.getValue(numOfFunction, x);
        }
        sum += equations.getValue(numOfFunction, right) / 2;
        sum = sum * h;
        while(true) {
            if(n > 1000000){
                System.out.println("Не получилось расчитать интеграл с заданной точностью");
                break;
            }
            int doubleN = n * 2;
            double doubleH = (right - left) / (double) doubleN;
            x = left;
            double doubleSum = equations.getValue(numOfFunction, x) / 2;
            for (int i = 1; i < doubleN; i++) {
                x += doubleH;
                doubleSum += equations.getValue(numOfFunction, x);
            }
            doubleSum += equations.getValue(numOfFunction, right) / 2;
            doubleSum = doubleSum * doubleH;
            if (Double.compare(eps, Math.abs(sum - doubleSum)) >= 0) {
                output.output(doubleSum, doubleN);
                break;
            }
            else{
                sum = doubleSum;
                n = doubleN;
            }
        }
    }
}
