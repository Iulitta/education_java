package Labs.lab2;

import java.util.Scanner;

public class laba2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите значения координат первой точки: ");
        double x = in.nextDouble();
        double y = in.nextDouble();
        double z = in.nextDouble();
        Point3d point1 = new Point3d(x, y, z);

        System.out.println("Введите значения координат второй точки: ");
        x = in.nextDouble();
        y = in.nextDouble();
        z = in.nextDouble();
        Point3d point2 = new Point3d(x, y, z);

        System.out.println("Введите значения координат третьей точки: ");
        x = in.nextDouble();
        y = in.nextDouble();
        z = in.nextDouble();
        Point3d point3 = new Point3d(x, y, z);

        if (point1.equals(point2) || point2.equals(point3) || point3.equals(point1)) {
            System.out.println("Одна или несколько точек равны между собой. Невозможно посчитать площадь треугольника, образованного этими тремя точками.");
        }
        else {
            System.out.println("Площадь треугольника, образованного этими тремя точками, равна: " + computeArea(point1, point2, point3));
        }
    }

    public static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
        double a = p1.distanceTo(p2), b = p2.distanceTo(p3), c = p3.distanceTo(p1);
        double p = (a + b + c) / 2.0;
        double result = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return Math.round(result * 100.0) / 100.0; // Округление до 2 знаков после запятой
    }
}
