package HomeWork4;

import java.util.Scanner;

public class TriangleSquareCalculation {
    public static double countTriangleArea(double sideA, double sideB, double sideC) throws BadTriangleException {
        if (sideA < 0 || sideB < 0 || sideC < 0) throw new BadTriangleException();
        if (sideA + sideB < sideC || sideA + sideC < sideB || sideB + sideC < sideA) throw new BadTriangleException();
        double halfPerimeter = (sideA + sideB + sideC) / 2;
        double square = Math.sqrt(halfPerimeter * (halfPerimeter - sideA) *
                (halfPerimeter - sideB) * (halfPerimeter - sideC));
        return square;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Вычисление площади треугольника по трем сторонам");
        System.out.println("Введите длины сторон треугольника");
        System.out.print("sideA = ");
        double sideA = scan.nextInt();
        System.out.print("sideB = ");
        double sideB = scan.nextInt();
        System.out.print("sideC = ");
        double sideC = scan.nextInt();
        if (sideA + sideB < sideC || sideA + sideC < sideB || sideB + sideC < sideA)
            if  (sideA < 0 || sideB < 0 || sideC < 0) {
                System.out.println("Эти стороны не образуют треугольника");
                System.exit(0);
            }
        double halfPerimeter = (sideA + sideB + sideC) / 2.0;
        System.out.println("Площадь треугольника = " + Math.sqrt(halfPerimeter * (halfPerimeter - sideA) *
                (halfPerimeter - sideB) * (halfPerimeter - sideC)));

    }
}

