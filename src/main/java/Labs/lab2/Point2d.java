package Labs.lab2;

public class Point2d {
    private double x; // Координата по оси X, поле класса
    private double y; // Координата по оси Y

    /**
     * Конструктор по умолчанию
     */
    public Point2d() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Конструктор с передачей параметров
     * @param x входная координата по оси X
     * @param y входная координата по оси Y
     */
    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату точки по оси X
     * @return координата точки по оси X
     */
    public double getX() {
        return this.x;
    }

    /**
     * Возвращает координату точки по оси Y
     * @return координата точки по оси Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Устанавливает новый параметр для координаты точки по оси X
     * @param x новое значение координаты точки по оси X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Устанавливает новый параметр для координаты точки по оси Y
     * @param y новое значение координаты точки по оси Y
     */
    public void setY(double y) {
        this.y = y;
    }
}