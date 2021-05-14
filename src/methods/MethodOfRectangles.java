package methods;

import equations.Equations;
import io.Output;

public class MethodOfRectangles{

    private int numOfFunction;
    private double eps;
    private double left;
    private double right;
    private int modification;
    private int n = 4;
    private Equations equations;
    private Output output;

    public MethodOfRectangles(int numOfFunction, double eps, double left, double right, int modification){
        this.eps = eps;
        this.left = left;
        this.numOfFunction = numOfFunction;
        this.right = right;
        this.modification = modification;
        equations = new Equations();
        output = new Output();
    }

    public void solve() {
        switch (modification){
            case (1):
                left();
                break;
            case (2):
                right();
                break;
            case (3):
                central();
                break;
            default:
                break;
        }
    }

    private void left(){
        double h = (right - left) / (double) n;
        double x = left;
        double sum = equations.getValue(numOfFunction, x);
        for (int i = 1; i < n; i++) {
            x+=h;
            sum+=equations.getValue(numOfFunction, x);
        }
        sum = sum * h;
        while(true) {
            if(n > 1000000){
                System.out.println("Не получилось расчитать интеграл с заданной точностью");
                break;
            }
            int doubleN = n * 2;
            double doubleH = (right - left) / (double) doubleN;
            x = left;
            double doubleSum = equations.getValue(numOfFunction, x);
            for (int i = 1; i < doubleN; i++) {
                x += doubleH;
                doubleSum += equations.getValue(numOfFunction, x);
            }
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

    private void right(){
        double h = (right - left) / (double) n;
        double x = left;
        double sum = 0;
        for (int i = 1; i < n; i++) {
            x+=h;
            sum+=equations.getValue(numOfFunction, x);
        }
        sum = sum * h;
        while(true) {
            if(n > 1000000){
                System.out.println("Не получилось расчитать интеграл с заданной точностью");
                break;
            }
            int doubleN = n * 2;
            double doubleH = (right - left) / (double) doubleN;
            x = left;
            double doubleSum = equations.getValue(numOfFunction, x);
            for (int i = 1; i < doubleN; i++) {
                x += doubleH;
                doubleSum += equations.getValue(numOfFunction, x);
            }
            doubleSum = doubleH * doubleSum;
            if (Double.compare(eps, Math.abs(sum - doubleSum)) >= 0) {
                break;
            }
            else{
                sum = doubleSum;
                n = doubleN;
            }
        }
        output.output(sum, n);
    }

    private void central(){
        double h = (right - left) / (double) n;
        double x = left;
        double sum = 0;
        double prevX;
        for (int i = 1; i < n; i++) {
            prevX = x;
            x+=h;
            sum+=equations.getValue(numOfFunction, (prevX + x) / 2);
        }
        sum = sum * h;
        while(true) {
            if(n > 1000000){
                System.out.println("Не получилось расчитать интеграл с заданной точностью");
                break;
            }
            int doubleN = n * 2;
            double doubleH = (right - left) / (double) doubleN;
            x = left;
            double doubleSum = equations.getValue(numOfFunction, x);
            for (int i = 1; i < doubleN; i++) {
                prevX = x;
                x+=h;
                doubleSum+=equations.getValue(numOfFunction, (prevX + x) / 2);
            }
            doubleSum = doubleSum * doubleH;
            if (Double.compare(eps, Math.abs(sum - doubleSum)) >= 0) {
                break;
            }
            else{
                sum = doubleSum;
                n = doubleN;
            }
        }
        output.output(sum, n);
    }

}
