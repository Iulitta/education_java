package Labs.lab1;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if (isPalindrome(s)) {
            System.out.println("Слово " + s + " является палиндромом.");
        }
        else {
            System.out.println("Слово " + s + " не является палиндромом.");
        }
    }

    /**
     * Функция возвращает строку в реверсированном виде
     * @param str строка
     * @return реверсированная строка
     */
    public static String reverseString(String str) {
        String temp = "";
        int l = str.length() - 1;
        while (l >= 0) {
            temp = temp + str.charAt(l);
            l--;
        }
        return temp;
    }

    /**
     * Функция проверяет, является ли строка палиндромом
     * @param s строка
     * @return true, если строка - палиндром, или false, если нет
     */
    public static boolean isPalindrome(String s) {

        return s.equals(reverseString(s));
    }
}
