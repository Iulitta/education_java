package Labs.lab5;

import java.awt.geom.Rectangle2D;

/**
 * Подкласс для фракталов второго вида
 **/
public class Tricorn extends FractalGenerator {

    // Константа с максимальным количеством итераций
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Переопределения метода для указания области рисования конкретного факториала
     * @param range прямоугольный объект
     **/
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Изменение полей прямоугольника
        range.x = -2.0;
        range.y = -2.0;
        range.height = 4.0;
        range.width = 4.0;
    }

    /**
     * Переопределение функции нахождения количества итерация для фрактала
     * @param x действительная часть комплексного числа
     * @param y мнимая часть комплексного числа
     * @return количество итераций, не превышающее максимальное (2000), или -1, если превышает
     **/
    @Override
    public int numIterations(double x, double y) {
        ComplexNumber cn1 = new ComplexNumber(x, -y);
        ComplexNumber cn2 = new ComplexNumber();
        int iterations = 0;
        while (cn2.getX() * cn2.getX() + cn2.getY() * cn2.getY() <= 4 && iterations < MAX_ITERATIONS) {
            double tempX = cn2.getX();
            cn2.setX(cn2.getX() * cn2.getX() + cn1.getX() - cn2.getY() * cn2.getY()); // cn2.x^2 + cn1.x - cn2.y^2
            cn2.setY(-1 * (2 * tempX * cn2.getY() + cn1.getY())); // -(2 * cn2.x + cn2.y * cn1.y)
            iterations++;
        }
        if (iterations >= MAX_ITERATIONS) return -1;
        else return iterations;
    }

    /**
     * Переопределение функции toString для удобной работы
     * @return возвращает имя класса
     **/
    @Override
    public String toString() {
        return "Tricorn";
    }
}