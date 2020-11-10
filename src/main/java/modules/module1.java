package modules;

// Модуль 1/6

public class module1 {

    /**
     * Главная функция программы
     */
    public static void main(String[] argc) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(remainder(1,3));
        System.out.println(remainder(3,4));
        System.out.println(remainder(-9,45));
        System.out.println(remainder(5,5) + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(triArea(3,2));
        System.out.println(triArea(7,4));
        System.out.println(triArea(10,10) + "\n");

        // Задача 3
        System.out.println("Задача 3.");
        System.out.println(animals(2, 3, 5));
        System.out.println(animals(1, 2, 3));
        System.out.println(animals(5, 2, 8) + "\n");

        // Задача 4
        System.out.println("Задача 4.");
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(profitableGamble(0.9, 1, 2));
        System.out.println(profitableGamble(0.9, 3, 2) + "\n");

        // Задача 5
        System.out.println("Задача 5.");
        System.out.println(Operation(24, 15, 9));
        System.out.println(Operation(24, 26, 2));
        System.out.println(Operation(22, 11, 2));
        System.out.println(Operation(7, 49, 7));
        System.out.println(Operation(15, 11, 11) + "\n");

        // Задача 6
        System.out.println("Задача 6.");
        System.out.println(ctoa('A'));
        System.out.println(ctoa('m'));
        System.out.println(ctoa('['));
        System.out.println(ctoa('/') + "\n");

        // Задача 7
        System.out.println("Задача 7.");
        System.out.println(addUpTo(3));
        System.out.println(addUpTo(10));
        System.out.println(addUpTo(7) + "\n");

        // Задача 8
        System.out.println("Задача 8.");
        System.out.println(nextEdge(8, 10));
        System.out.println(nextEdge(5, 7));
        System.out.println(nextEdge(9, 2) + "\n");

        // Задача 9
        System.out.println("Задача 9.");
        System.out.println(sumOfCubes(new int[]{1, 5, 9}));
        System.out.println(sumOfCubes(new int[]{3, 4, 5}));
        System.out.println(sumOfCubes(new int[]{2}));
        System.out.println(sumOfCubes(new int[]{}) + "\n");

        // Задача 10
        System.out.println("Задача 10.");
        System.out.println(abcMath(42, 5, 10));
        System.out.println(abcMath(5, 2, 1));
        System.out.println(abcMath(1, 2, 3) + "\n");
    }

    // Задача 1
    /**
     * Функция возвращает остаток от операции деления
     * @param a первый параметр
     * @param b второй параметр, на который делится первый параметр
     * @return остаток от операции деления
     */
    public static int remainder(int a, int b) {
        return a % b;
    }

    // Задача 2
    /**
     * Функция возвращает площадь треугольника по принимаемым высоте и основаниям
     * @param a основание треугольника
     * @param b высота треугольника
     * @return площадь треугольника
     */
    public static double triArea(double a, double b) {
        return (a*b)/2;
    }

    // Задача 3
    /**
     * Функция возвращает количество ног животных по получаемому количеству животных
     * @param chickens количество курочек
     * @param cows количество коров
     * @param pigs количество свиней
     * @return количество ног всех животных
     */
    public static int animals(int chickens, int cows, int pigs) {
        return (chickens*2 + cows*4 + pigs*4);
    }

    // Задача 4
    /**
     * Функция возвращает true или false в зависимости от получаемых параметров
     * @param prob коэффициент от приза
     * @param prize приз
     * @param pay вложение денег для участия в игре
     * @return boolean в зависимости от получаемых параметров
     */
    public static boolean profitableGamble(double prob, double prize, double pay) {
        return prob * prize > pay;
    }

    // Задача 5
    /**
     * Функция возвращает строкой название операции по получаемым параметрам
     * @param res результат операции между a и b
     * @param a первое число
     * @param b второе число
     * @return строка "added", "subtracted", "multiplied", "divided" или "none"
     */
    public static String Operation(int res, int a, int b) {
        if (a+b == res) {
            return "added";
        }
        else if (a-b == res) {
            return "subtracted";
        }
        else if (a*b == res) {
            return "multiplied";
        }
        else if (a/b == res) {
            return "divided";
        }
        else {
            return "none";
        }
    }

    // Задача 6
    /**
     * Функция возвращает числовое значение ASCII переданного символа
     * @param input вводимый символ
     * @return числовое значение символа в ASCII таблице
     */
    public static int ctoa(char input) {
        return input;
    }

    // Задача 7
    /**
     * Функция возвращает сумму чисел от 1 до n с шагом 1
     * @param n входное число
     * @return возвращает сумму чисел от 1 до n с шагом 1
     */
    public static int addUpTo(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + n;
            n--;
        }
        return sum;
    }

    // Задача 8
    /**
     * Функция находит максимальное значение третьего ребра треугольника по двум известным
     * @param a первое ребро треугольник
     * @param b второе ребро треугольника
     * @return максимальное значение третьего ребра треугольника
     */
    public static int nextEdge(int a, int b) {
        return (a + b - 1);
    }

    // Задача 9
    /**
     * Функция возвращает сумму чисел массива, возведённых в куб
     * @param array входной массив
     * @return сумма элементов массива, возведённого в куб
     */
    public static int sumOfCubes(int[] array) {
        int sum = 0, length = array.length;
        for (int j = 0; j < length; j++) { // Для каждого элемента j массива array
            sum = sum + array[j] * array[j] * array[j];
        }
        return sum;
    }

    // Задача 10
    /**
     * Функция прибавляет параметр a к самому себе b раз, а затем проверяет, делится ли полученное число на параметр c
     * @param a параметр a
     * @param b параметр b
     * @param c параметр c
     * @return возвращает true или false в зависимости от полученных параметров
     */
    public static boolean abcMath(int a, int b, int c) {
        while (b > 0) {
            a = a + a;
            b--;
        }
        return (a % c == 0);
    }
}
