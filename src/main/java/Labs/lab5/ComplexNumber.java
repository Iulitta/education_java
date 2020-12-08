package Labs.lab5;

/** Класс для хранения комплексных чисел **/
public class ComplexNumber {

    // Две переменные закрытого доступа для хранения мнимой и действительной части (общий вид: x + yi)
    private double x; // Действительная часть
    private double y; // Мнимая часть

    /** Конструктор класса по умолчанию **/
    ComplexNumber() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Конструктор класса с передачей переменных x и y
     * @param x действительная часть комплексного числа
     * @param y мнимая часть комплексного числа
     **/
    ComplexNumber(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Стандартный getter для x
     * @return возвращает x - действительную часть комплексного числа
     **/
    public double getX() {
        return this.x;
    }

    /**
     * Стандартный getter для y
     * @return возвращает y - мнимую часть комплексного числа
     **/
    public double getY() {
        return this.y;
    }

    /**
     * Стандартный setter для x
     * @param x действительная часть комплексного числа
     **/
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Стандартный setter для y
     * @param y мнимая часть комплексного числа
     **/
    public void setY(double y) {
        this.y = y;
    }
}