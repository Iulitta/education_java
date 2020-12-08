package Labs.lab6;

import java.awt.geom.Rectangle2D;

/**
 * Этот класс предоставляет общий интерфейс и операции
 * для фрактальных генераторов, которые можно просмотреть в обозревателе фракталов.
 **/
public abstract class FractalGenerator {

    /**
     * Эта статическая вспомогательная функция принимает целочисленную координату и
     * преобразует ее в значение двойной точности, соответствующее определенному диапазону.
     * Он используется для преобразования координат пикселей в значения двойной точности
     * для вычисления фракталов и т. д.
     *
     * @param rangeMin минимальное значение диапазона с плавающей запятой
     * @param rangeMax максимальное значение диапазона с плавающей запятой
     *
     * @param size размер измерения, из которого берется координата пикселя.
     *        Например, это может быть ширина изображения или высота изображения.
     *
     * @param coord координата для вычисления значения двойной точности.
     *        Координата должна находиться в диапазоне [0, size].
     **/
    public static double getCoord(double rangeMin, double rangeMax, int size, int coord) {
        assert size > 0;
        assert coord >= 0 && coord < size;
        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }

    /** Задает прямоугольник, содержащий начальный диапазон, подходящий для генерируемого фрактала. **/
    public abstract void getInitialRange(Rectangle2D.Double range);

    /**
     * Обновляет текущий диапазон для центрирования по указанным координатам и
     * увеличения или уменьшения масштаба с помощью указанного коэффициента масштабирования.
     **/
    public static void recenterAndZoomRange(Rectangle2D.Double range, double centerX, double centerY, double scale) {
        double newWidth = range.width * scale;
        double newHeight = range.height * scale;
        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }

    /**
     * Учитывая координату <em>x</em> + <em>iy</em> в комплексной плоскости,
     * вычисляет и возвращает число итераций до того,
     * как фрактальная функция выйдет за пределы ограничивающей области для этой точки.
     * Точка, которая не убегает до достижения предела итерации, указывается с результатом -1.
     **/
    public abstract int numIterations(double x, double y);
}