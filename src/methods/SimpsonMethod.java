package methods;

import equations.Equations;
import io.Output;

public class SimpsonMethod {

    private int numOfFunction;
    private double eps;
    private double left;
    private double right;
    private int n = 4;
    private Equations equations;
    private Output output;

    public SimpsonMethod(int numOfFunction, double eps, double left, double right){
        this.eps = eps;
        this.left = left;
        this.numOfFunction = numOfFunction;
        this.right = right;
        equations = new Equations();
        output = new Output();
    }

    public void solve(){
        double h = (right-left)/ n;
        double x = left;
        double sum = equations.getValue(numOfFunction, left);
        for(int i=1; i<n; i++){
            x+=h;
            if(i%2==0)
                sum+=2*equations.getValue(numOfFunction, x);
            else
                sum+=4*equations.getValue(numOfFunction, x);
        }
        sum =  h/3*(sum+equations.getValue(numOfFunction, right));
        while(true) {
            if(n > 1000000){
                System.out.println("Не получилось расчитать интеграл с заданной точностью");
                break;
            }
            int doubleN = n * 2;
            double doubleH = (right - left) / (double) doubleN;
            x = left;
            double doubleSum = equations.getValue(numOfFunction, left);
            for (int i = 1; i < doubleN; i++) {
                x += doubleH;
                if(i%2==0)
                    doubleSum+=2*equations.getValue(numOfFunction, x);
                else
                    doubleSum+=4*equations.getValue(numOfFunction, x);
            }
            doubleSum =  doubleH/3*(doubleSum+equations.getValue(numOfFunction, right));
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
