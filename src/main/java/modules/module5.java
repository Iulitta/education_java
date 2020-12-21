package modules;

import java.util.Arrays;

public class module5 {

    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[] { 72, 33, -73, 84, -12, -3, 13, -13, -68 }));
        System.out.println(Arrays.toString(encrypt("Sunshine")) + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(canMove("Ладья", "A8", "H8"));
        System.out.println(canMove("Слон", "A7", "G1"));
        System.out.println(canMove("Ферзь", "C4", "D6") + "\n");

        // Задача 3
        System.out.println("Задача 3.");
        System.out.println(canComplete("butl","beautiful"));
        System.out.println(canComplete("butlz","beautiful"));
        System.out.println(canComplete("tulb","beautiful"));
        System.out.println(canComplete("bbutl","beautiful") + "\n");

        // Задача 4
        System.out.println("Задача 4.");
        System.out.println(sumDigProd(new int[]{16, 28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}) + "\n");

        // Задача 5
        System.out.println("Задача 5.");
        sameVowelGroup(new String[]{"toe", "ocelot", "maniac"});
        sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"});
        sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"});
        System.out.println();

        // Задача 6
        System.out.println("Задача 6.");
        System.out.println(validateCard("1234567890123456"));
        System.out.println(validateCard("1234567890123452") + "\n");

        // Задача 7
        System.out.println("Задача 7.");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909) + "\n");

        // Задача 8
        System.out.println("Задача 8.");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!") + "\n");

        // Задача 9
        System.out.println("Задача 9.");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN.") + "\n");

        // Задача 10
        System.out.println("Задача 10.");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21) + "\n");
        System.out.println(hexLattice(91));
    }

    // Задача 1
    // Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное
    // сообщение. Первая буква строки или первый элемент массива представляет собой символьный код этой буквы.
    // Следующие элементы-это различия между символами.
    public static int[] encrypt(String str) {
        int[] array = new int[str.length()];
        array[0] = str.charAt(0); //в нулевой элемент записываем по аски-коду
        for (int i = 1; i < array.length; i++) { //записываем разницу с прошлым элементом
            array[i] = str.charAt(i) - str.charAt(i - 1);
        }
        return array;
    }

    public static String decrypt(int[] array) {
        String result = "";
        int temp=array[0];
        result = result + (char)array[0]; //в результат копируем первый элемент и складываем
        for (int i = 1; i < array.length; i++) {
            temp+=array[i];
            result = result + (char)temp;
        }
        return result;
    }

    // Задача 2
    // Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
    // Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого
    // сделать. Возможные входные данные - "пешка", "конь", "слон", "ладья", "ферзь" и "король".
    public static boolean canMove(String name, String position, String way) {
        // Строки с путями в нижний регистр
        position = position.toLowerCase();
        way = way.toLowerCase();
        boolean flag = false;
        int path;

        // В зависимости от фигуры выполняем проверку движения фигуры
        switch (name) {
            case "Пешка":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1));
                if (path <= 2) flag = true;
            case "Конь":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) + Math.abs(position.charAt(0)) - Math.abs(way.charAt(0));
                if (path == 3 && position.charAt(0) != way.charAt(0) && (int)position.charAt(1) != (int)way.charAt(1)) flag = true;
            case "Слон":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) - Math.abs((int)position.charAt(0) - (int)way.charAt(0));
                if (path == 0) flag = true;
            case "Ладья":
                if ((int)position.charAt(0) == (int)way.charAt(0) || (int)position.charAt(1) == (int)way.charAt(1)) flag = true;
            case "Ферзь":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) - Math.abs((int)position.charAt(0) - (int)way.charAt(0));
                if (path == 0 || ((int)position.charAt(0) == (int)way.charAt(0) || (int)position.charAt(1) == (int)way.charAt(1))) flag = true;
            case "Король":
                if (Math.abs((int)position.charAt(0) - (int)way.charAt(0)) < 2 && Math.abs((int)position.charAt(1) - (int)way.charAt(1)) < 2) flag = true;
        }
        return flag;
    }

    // Задача 3
    // Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие буквы не
    // должны быть удалены, чтобы соответствовать слову. Кроме того, порядок букв во входной строке должен
    // быть таким же, как и порядок букв в последнем слове. Создайте функцию, которая, учитывая входную строку,
    // определяет, может ли слово быть завершено.
    public static boolean canComplete(String str1, String str2) {
        // Первый блок проверок
        boolean flag = false; // Временная переменная
        // Если вторая строка не содержит хотя бы один символ первой строки
        for (int i = 0; i < str1.length(); i++) {
            if (!str2.contains(String.valueOf(str1.charAt(i)))) {
                flag = true;
                break;
            }
        }
        // Если прошлый цикл нашёл символ, подходящий по условию и длина первой строки больше длины второй
        if (flag || str1.length() > str2.length()) return false;

        // Второй блок проверок
        String temp = ""; // Временная строка
        for (int i = 0; i < str1.length(); i++) {
            // Еcли последний символ первой строки, то копируем вторую до конца
            if (i == str1.length() - 1) {
                temp = temp + str2.substring(str2.indexOf(str1.charAt(i)));
                break;
            }
            else {
                // Если следующий символ не найден после первого во второй строке, то возвращаем false
                if (str2.indexOf(str1.charAt(i + 1), str2.indexOf(str1.charAt(i)) + 1) == -1) return false;
                // Копируем из второй строки подстроки с помощью символов из первой строки по этому правилу
                // после чего сравниваем временную строку со второй
                temp = temp + str2.substring(str2.indexOf(str1.charAt(i)), str2.indexOf(str1.charAt(i + 1), str2.indexOf(str1.charAt(i)) + 1));
            }
        }
        return str2.equals(temp);
    }

    // Задача 4
    // Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе и возвращает
    // произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру.
    public static int sumDigProd(int[] arr) {
        int sum = 0, temp;
        for (int i : arr) { // Сумма всех элементов массива через цикл
            sum = sum + i;
        }
        while (sum > 0) { // Цикл, который будет умножать цифры числа, пока результат не станет 1 символом
            if (sum < 10) break; // Выход из цикла при < 10
            temp = sum;
            sum = 1;
            while (temp > 0) { // Разложение числа на цифры
                sum = sum * (temp % 10); // Умножение
                temp = temp / 10;
            }
        }
        return sum;
    }

    // Задача 5
    // Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в любом порядке и/или
    // количестве), что и первое слово, включая первое слово.
    public static void sameVowelGroup(String[] arr) {
        String vowelList = "aeiouy", nowVowel = "";
        // Первая строка является "списком" всех гласных, вторая "списком" гласных первого слова

        // В первом слове находим все гласные
        for (int i = 0; i < vowelList.length(); i++) {
            if (arr[0].contains(String.valueOf(vowelList.charAt(i))) && // Если гласная есть в первом списке
                    !nowVowel.contains(String.valueOf(vowelList.charAt(i))))    // и если её пока нет во втором списке
                nowVowel = nowVowel + vowelList.charAt(i);              // добавляем её во второй список
        }
        System.out.print(arr[0] + " "); // Выводим первое слово массива строк

        // Проверяем остальные слова в списке
        for (int i = 1; i < arr.length; i++) {
            boolean flag = true; // Вспомогательная переменная-флаг
            for (int j = 0; j < vowelList.length(); j++) {
                if (arr[i].contains(String.valueOf(vowelList.charAt(j))) &&  // Гласная есть в первом списке
                        !nowVowel.contains(String.valueOf(vowelList.charAt(j)))) {   // и её нет во втором списке
                    flag = false;   // Обнуляем флаг
                    break;          // Прерываем цикл
                }
            }
            if (flag) System.out.print(arr[i] + " ");   // Если флаг не был обнулён, выводим слово
        }
        System.out.println();
    }

    // Задача 6
    // Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если это число является
    // действительным номером кредитной карты, а в противном случае-false.
    public static boolean validateCard(String input) {
        // Первая проверка
        if (input.length() < 14 || input.length() > 19) return false;       // Критерии по длине символов

        // Контрольное число в конце номера карты
        int checkNum = Integer.parseInt(String.valueOf(input.charAt(input.length() - 1)));

        // Реверснутая строка числа без последней цифры
        input = new StringBuilder(input.substring(0, input.length() - 1)).reverse().toString();
        int sum = 0;    // Переменная для суммы чисел

        // Через цикл получаем сумму чисел
        for (int i = 0; i < input.length(); i++) {
            int n = Integer.parseInt(String.valueOf(input.charAt(i)));      // Каждый символ строки обратно в число
            if (i % 2 == 0) {       // Каждое нечётное число (но так как нумерация с 0, то чётное)
                n = 2 * n;
                if (n >= 10) n = (n / 10) + (n % 10);
                sum = sum + n;
            }
            else {                  // Каждое чётное число (но так как нумерация с 0, то нечётное)
                sum = sum + n;
            }
        }

        int result = 10 - (sum % 10);       // Вычитаем псоледнюю цифры суммы из десяти

        // Тест Луна
        return (result == checkNum);
    }

    // Задача 7
    // Напишите функцию, которая принимает положительное целое число от 0 до 999 включительно и возвращает
    // строковое представление этого целого числа, написанное на английском языке.
    public static String numToEng(int num) {
        if (num > 999 || num < 0) return "Incorrect number!";   // Выбираем начальные условия
        String str = Integer.toString(num);                     // Конвертируем число в строку
        int length = str.length();                              // Длина строки
        String result = "";                                     // Строка для сохранения результата

        // Дополняем нулями при соотв. длине строки
        if (length == 1) str = "00" + str;
        else if (length == 2) str = "0" + str;

        // Цикл для получения строки
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(String.valueOf(str.charAt(i)));    // Текущее число для проверки
            if (i == 0) {           // Для сотен
                switch (n) {
                    case 0: continue;
                    case 1: result = result + "one hundred "; break;
                    case 2: result = result + "two hundred "; break;
                    case 3: result = result + "three hundred "; break;
                    case 4: result = result + "four hundred "; break;
                    case 5: result = result + "five hundred "; break;
                    case 6: result = result + "six hundred "; break;
                    case 7: result = result + "seven hundred "; break;
                    case 8: result = result + "eighth hundred "; break;
                    case 9: result = result + "nine hundred "; break;
                }
            }
            else if (i == 1) {      // Для десятков
                switch (n) {
                    case 0: continue;
                    case 1: {
                        num = num % 100;
                        switch (num) {
                            case 10: result = result + "ten"; break;
                            case 11: result = result + "eleven"; break;
                            case 12: result = result + "twelve"; break;
                            case 13: result = result + "thirteen"; break;
                            case 14: result = result + "fourteen"; break;
                            case 15: result = result + "fifteen"; break;
                            case 16: result = result + "sixteen"; break;
                            case 17: result = result + "seventeen"; break;
                            case 18: result = result + "eighteen"; break;
                            case 19: result = result + "nineteen"; break;
                        }
                        return result;
                    }
                    case 2: result = result + "twenty "; break;
                    case 3: result = result + "thirty "; break;
                    case 4: result = result + "forty "; break;
                    case 5: result = result + "fifty "; break;
                    case 6: result = result + "sixty "; break;
                    case 7: result = result + "seventy "; break;
                    case 8: result = result + "eighty "; break;
                    case 9: result = result + "ninety "; break;
                }
            }
            else {                  // Для единиц
                switch (n) {
                    case 0: result = result + "zero"; break;
                    case 1: result = result + "one"; break;
                    case 2: result = result + "two"; break;
                    case 3: result = result + "three"; break;
                    case 4: result = result + "four"; break;
                    case 5: result = result + "five"; break;
                    case 6: result = result + "six"; break;
                    case 7: result = result + "seven"; break;
                    case 8: result = result + "eighth"; break;
                    case 9: result = result + "nine"; break;
                }
            }
        }
        return result;
    }

    // Русский вариант
    public static String numToRus(int num) {
        if (num > 999 || num < 0) return "Неправильное число!"; // Выбираем начальные условия
        String str = Integer.toString(num);                     // Конвертируем число в строку
        int length = str.length();                              // Длина строки
        String result = "";                                     // Строка для сохранения результата

        // Дополняем нулями при соотв. длине строки
        if (length == 1) str = "00" + str;
        else if (length == 2) str = "0" + str;

        // Цикл для получения строки
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(String.valueOf(str.charAt(i)));    // Текущее число для проверки
            if (i == 0) {           // Для сотен
                switch (n) {
                    case 0: continue;
                    case 1: result = result + "сто "; break;
                    case 2: result = result + "двести "; break;
                    case 3: result = result + "триста "; break;
                    case 4: result = result + "четыреста "; break;
                    case 5: result = result + "пятьсот "; break;
                    case 6: result = result + "шестьсот "; break;
                    case 7: result = result + "семьсот "; break;
                    case 8: result = result + "восемьсот "; break;
                    case 9: result = result + "двеятьсот "; break;
                }
            }
            else if (i == 1) {      // Для десятков
                switch (n) {
                    case 0: continue;
                    case 1: {
                        switch (num) {
                            case 10: result = result + "десять"; break;
                            case 11: result = result + "одиннадцать"; break;
                            case 12: result = result + "двенадцать"; break;
                            case 13: result = result + "тринадцать"; break;
                            case 14: result = result + "четырнадцать"; break;
                            case 15: result = result + "пятнадцать"; break;
                            case 16: result = result + "шестнадцать"; break;
                            case 17: result = result + "семнадцать"; break;
                            case 18: result = result + "восемнадцать"; break;
                            case 19: result = result + "девятнадцать"; break;
                        }
                        return result;
                    }
                    case 2: result = result + "двадцать "; break;
                    case 3: result = result + "тридцать "; break;
                    case 4: result = result + "сорок "; break;
                    case 5: result = result + "пятьдесят "; break;
                    case 6: result = result + "шестьдесят "; break;
                    case 7: result = result + "семьдесят "; break;
                    case 8: result = result + "восемьдесять "; break;
                    case 9: result = result + "девяносто "; break;
                }
            }
            else {                  // Для единиц
                switch (n) {
                    case 0: result = result + "ноль"; break;
                    case 1: result = result + "один"; break;
                    case 2: result = result + "два"; break;
                    case 3: result = result + "три"; break;
                    case 4: result = result + "четыре"; break;
                    case 5: result = result + "пять"; break;
                    case 6: result = result + "шесть"; break;
                    case 7: result = result + "семь"; break;
                    case 8: result = result + "восемь"; break;
                    case 9: result = result + "девять"; break;
                }
            }
        }
        return result;
    }

    // Задача 8
    // Хеш-алгоритмы легко сделать одним способом, но по существу невозможно сделать наоборот. Например, если
    // вы хешируете что-то простое, например, password123, это даст вам длинный код, уникальный для этого слова
    // или фразы. В идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и вернуться к
    // слову или фразе, с которых вы начали. Создайте функцию, которая возвращает безопасный хеш SHA-256 для
    // данной строки. Хеш должен быть отформатирован в виде шестнадцатеричной цифры.
    public static String getSha256Hash(String str) {
        // Использование библиотеки Apache Common Codec
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
    }

    // Задача 9
    // Напишите функцию, которая принимает строку и возвращает строку с правильным регистром для заголовков символов
    // в серии "Игра престолов". Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь
    // первый символ в верхнем регистре, а остальные - в нижнем.
    public static String correctTitle(String str) {
        str = str.toLowerCase();        // Все символы в нижний регистр
        String temp = "";               // Временная строка

        // Цикл для прохождения по строке
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) temp = temp + Character.toUpperCase(str.charAt(i));   // Если первый символ
            if (str.charAt(i) == ' ') {
                // Обрезаем слово до след. пробела
                int endIndex = str.indexOf(' ', i + 1);     // Индекс след. пробела
                String substr = "";                                      // Слово (инициализируем пустой строкой)
                if (endIndex != -1) substr = str.substring(i + 1, endIndex);    // Если пробел был найден
                else substr = str.substring(i + 1);                             // Иначе обрезаем до конца

                // Если это слово не является определёнными словами
                if (!substr.equals("and") && !substr.equals("the") && !substr.equals("of") && !substr.equals("in")) {
                    // Буква после пробела в верхний регистр
                    temp = temp + ' ' + Character.toUpperCase(str.charAt(i + 1));
                    i = i + 1;      // Инкрементируем указатель на символ, чтобы цикл пропустил этот символ
                    // и взял анализировать следующий после него символ
                }
                else temp = temp + str.charAt(i);
            }
            else {
                // Если не первая буква, то копируем во временную строку
                if (i != 0) temp = temp + str.charAt(i);
            }
        }

        // Возвращаем полученную строку
        return temp;
    }

    // Задача 10
    // Как указано в онлайн-энциклопедии целочисленных последовательностей:
    // Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка имеет 6 соседей.
    // Центрированное шестиугольное число - это центрированное фигурное число, представляющее шестиугольник
    // с точкой в центре и всеми другими точками, окружающими центральную точку в шестиугольной решетке.
    // Больше информации по алгоритму тут https://wikichi.ru/wiki/Centered_hexagonal_number
    public static String hexLattice(int n) {

        int count = 0;
        String result = "";

        // Проверяем, подходит ли n по критериям центрированного шестиугольного числа
        for (int i = 1; i <= n; i++) {
            int num = 3 * i * (i - 1) + 1;    // Рекуррентная формула для этих чисел
            // Если num становится равным введёному, то выходим из цикла и сохраняем i
            if (num == n) {
                count = i;
                break;
            }
            if (num > n) return "Incorrect input!";     // Если num становится больше, возвращаем ошибку
        }

        // После выхода из цикла выводим строку
        int strNum = (count * 2) - 1;       // Количество строк
        int mediumStr = (strNum / 2);       // Номер строки в середине

        // Цикл формирует строку
        for (int i = 0; i < strNum; i++) {
            if (i > mediumStr) {
                int diff = i - mediumStr;
                // Цикл для количества пробелов перед каждой строкой
                for (int j = 0; j < (count + 2*diff - i); j++) {    // Добавляем разницу
                    result = result + " ";
                }

                // Цикл для вывода знаков
                for (int j = 0; j < (count - 2*diff + i); j++) {    // Вычитаем разницу
                    result = result + "o ";
                }
            }
            else {
                // Цикл для количества пробелов перед каждой строкой
                for (int j = 0; j < (count - i); j++) {     // Вычитаем номер строки
                    result = result + " ";
                }

                // Цикл для вывода знаков
                for (int j = 0; j < (count + i); j++) {     // Добавляем номер строки
                    result = result + "o ";
                }
            }
            result = result + "\n";
        }

        // Выводим результат
        return result;
    }

}
