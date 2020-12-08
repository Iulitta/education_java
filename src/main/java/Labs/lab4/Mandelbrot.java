package Labs.lab4;

import java.awt.geom.Rectangle2D;

/** Подкласс для фракталов Мандельброта **/
public class Mandelbrot extends FractalGenerator {

    // Константа с максимальным количеством итераций
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Переопределения метода для указания области рисования конкретного фрактала
     * @param range прямоугольный объект
     **/
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Изменение полей прямоугольника
        range.x = -2.0;
        range.y = -1.5;
        range.height = 3.0;
        range.width = 3.0;
    }

    /**
     * Переопределение функции нахождения количества итерация для фрактала Мандельброта
     * @param x действительная часть комплексного числа
     * @param y мнимая часть комплексного числа
     * @return количество итераций, не превышающее максимальное (2000), или -1, если превышает
     **/
    @Override
    public int numIterations(double x, double y) {    //находим количество итераций по рекурентной формуле для каждого пикселя (координаты) и затем устанавливаем пикселю цвет на основе посчитанной итерации
        ComplexNumber cn1 = new ComplexNumber(x, y);
        ComplexNumber cn2 = new ComplexNumber();
        int iterations = 0;

        while (cn2.getX() * cn2.getX() + cn2.getY() * cn2.getY() <= 4 && iterations < MAX_ITERATIONS) {
            double tempX = cn2.getX();
            cn2.setX(cn2.getX() * cn2.getX() + cn1.getX() - cn2.getY() * cn2.getY()); // //(cn2.x^2 + cn1.x -cn2.y^2)
            cn2.setY(2 * tempX * cn2.getY() + cn1.getY()); // (2 * cn2.y * cn1.x + cn2.y)
            iterations++;
        }
        if (iterations >= MAX_ITERATIONS) return -1;
        else return iterations;
    }
}