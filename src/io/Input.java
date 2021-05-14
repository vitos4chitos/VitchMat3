package io;

import methods.MethodOfRectangles;
import methods.SimpsonMethod;
import methods.TrapeziumMethod;

import java.util.Scanner;

public class Input {


    public void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите номер выражения, которое хотите проинтегрировать: ");
        System.out.println("1) -x^3 - x^2 + x + 3");
        System.out.println("2) x^2");
        System.out.println("3) 3 / x + x");
        int numOfFunction;
        while (true){
            try {
                numOfFunction = Integer.parseInt(scanner.nextLine());
                if(numOfFunction < 1 || numOfFunction > 3){
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
                else{
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }

        }
        System.out.println("Введите номер метода: ");
        System.out.println("1) Метод прямоугольников");
        System.out.println("2) Метод трапеций");
        System.out.println("3) Метод Симпсона");
        int numOfMethod;
        while (true){
            try {
                numOfMethod = Integer.parseInt(scanner.nextLine());
                if(numOfMethod < 1 || numOfMethod > 3){
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
                else{
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        int numOfModification = 0;
        if(numOfMethod == 1){
            System.out.println("Выберите модификацию: ");
            System.out.println("1) Левые");
            System.out.println("2) Правые");
            System.out.println("3) Средние");
            while (true){
                try {
                    numOfModification = Integer.parseInt(scanner.nextLine());
                    if(numOfModification < 1 || numOfModification > 3){
                        System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                    }
                    else{
                        break;
                    }
                }
                catch (Exception e){
                    System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
                }
            }
        }
        System.out.println("Введите границы интегрирования построчно (левая и правая граница соответсвенно)");
        double left;
        double right;
        while (true){
            try {
                left = Double.parseDouble(scanner.nextLine());
                right = Double.parseDouble(scanner.nextLine());
                if(Double.compare(left, right) >= 0){
                    System.out.println("Левая граница должна быть меньше, чем правая. Попробуйте ещё раз");
                }
                else{
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        System.out.println("С какой точностью будут произвоидиться вычисления?");
        double eps;
        while (true){
            try {
               eps = Double.parseDouble(scanner.nextLine());
               if (Double.compare(eps, 0d) <= 0){
                   System.out.println("Погрешность не может быть отрицательной или равной нулю. Попробуйте ещё раз");
               }
               else{
                   break;
               }
            }
            catch (Exception e) {
                System.out.println("Вы ввели что-то не то, попробуйте ещё раз");
            }
        }
        switch (numOfMethod){
            case (1):
                MethodOfRectangles methodOfRectangles = new MethodOfRectangles(numOfFunction, eps, left, right, numOfModification);
                methodOfRectangles.solve();
                break;
            case (2):
                TrapeziumMethod trapeziumMethod = new TrapeziumMethod(numOfFunction, eps, left, right);
                trapeziumMethod.solve();
                break;
            case(3):
                SimpsonMethod simpsonMethod = new SimpsonMethod(numOfFunction, eps, left, right);
                simpsonMethod.solve();
                break;
            default:
                break;
        }


    }

}
