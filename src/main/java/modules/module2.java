package modules;

// Модуль 2/6

import java.util.Arrays;

public class module2 {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(repeat("mice",5));
        System.out.println(repeat("hello",3));
        System.out.println(repeat("stop",1) + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[]{44, 32, 86, 19}) + "\n");

        // Задача 3
        System.out.println("Задача 3.");
        System.out.println(isAvgWhole(new int[]{1, 3}));
        System.out.println(isAvgWhole(new int[]{1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[]{1, 5, 6}));
        System.out.println(isAvgWhole(new int[]{1, 1, 1}));
        System.out.println(isAvgWhole(new int[]{9, 2, 2, 5}) + "\n");

        // Задача 4
        System.out.println("Задача 4.");
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{1, -2, 3})));
        System.out.println(Arrays.toString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})) + "\n");

        // Задача 5
        System.out.println("Задача 5.");
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1") + "\n");

        // Задача 6
        System.out.println("Задача 6.");
        System.out.println(Fibonacci(3));
        System.out.println(Fibonacci(7));
        System.out.println(Fibonacci(12) + "\n");

        // Задача 7
        System.out.println("Задача 7.");
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939") + "\n");

        // Задача 8
        System.out.println("Задача 8.");
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", "") + "\n");

        // Задача 9
        System.out.println("Задача 9.");
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy") + "\n");

        // Задача 10
        System.out.println("Задача 10.");
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
        System.out.println(boxSeq(3));
        System.out.println(boxSeq(4));
        System.out.println(boxSeq(5));
        System.out.println(boxSeq(6) + "\n");
    }

    // Задача 1
    public static String repeat(String str, int num) {
        String repStr = "";
        for (int i = 0; i < str.length(); i++) {    // Проходимся по всем сиволам строки
            for (int j = 1; j <= num; j++) {        // Добавляем символ по индексу i в пустую строку num раз
                repStr = repStr + str.charAt(i);    // str.charAt(i) получает символ по индексу i из строки str
            }
        }
        return repStr;
    }

    // Задача 2
    public static int differenceMaxMin(int[] array) {
        int max = -100000;
        int min = 100000;
        for (int i = 0; i < array.length; i++) { // Нахождение максимального и минимального элемента
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }
        return (max - min);
    }

    // Задача 3
    public static boolean isAvgWhole(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return (sum % array.length == 0);
    }

    // Задача 4
    public static int[] cumulativeSum(int[] array) {
        for (int i = 1; i < array.length; i ++) {   // Со второго элемента и до последнего
            array[i] = array[i] + array[i-1];       // Складываем старый элемент с предыдущим новым
        }
        return array;
    }

    // Задача 5
    public static int getDecimalPlaces(String str) {
        int dot_index = str.indexOf('.');               // Находим индекс точки в строке str
        if (dot_index == -1) return 0;                  // Если индекс не найден, вернём ноль (число целое)
        else return (str.length() - (dot_index + 1));   // Вычитаем из длины строки количество знаков до точки + 1
    }

    // Задача 6
    public static int Fibonacci(int n) {
        if (n == 0 || n == 1) return 1;
        else return Fibonacci(n-1) + Fibonacci(n-2);      // Чтобы дойти до большого значения,
                                                                // надо посчитать все изначальные
    }

    // Задача 7
    public static boolean isValid(String str) {
        if (str.length() != 5) return false;            // Если длина строки не равна 5, то false
        else {
            boolean flag = true;
            for (int i = 0; i < str.length(); i++) {    // Проходим во всем символам строки
                if (str.charAt(i) < 48 || str.charAt(i) > 57) { // Если символ не цифра, то false и завершаем цикл
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

    // Задача 8
    public static boolean isStrangePair(String str1, String str2) {
        if (str1.length() == 0 && str2.length() == 0) return true;
        else return (str1.charAt(0) == str2.charAt(str2.length()-1) && str1.charAt(str1.length()-1) == str2.charAt(0));
    }

    // Задача 9
    public static boolean isPrefix(String str1, String str2) {
        String prefix = str2.substring(0, str2.length() - 1); // Убираем последний символ строки
        int index = new StringBuilder(str1).indexOf(prefix); // Находим индекс вхождения префикса в строку
        return (index == 0);
    }

    public static boolean isSuffix(String str1, String str2) {
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();
        return (isPrefix(str1, str2));  // Реверсируем обе строки и вызываем прошлую функцию
    }

    //Задача 10
    public static long boxSeq(int num) {
        long sum;
        if (num % 2 == 1) sum = num + 2;
        else sum = num;
        return sum;
    }
    // 0 3 2 5 4 7 6 9 8 11 10 // число
    // 0 1 2 3 4 5 6 7 8 9  10 // шаг
}
