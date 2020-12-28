package Labs.lab7;

import java.io.*;
import java.util.*;
import java.net.*;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";
    public static final FIFO URLPool = new FIFO(100);

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); //сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        in.close(); //закрываем поток ввода

        // Стартовые значения для пула ссылок
        URLPool.put(new URLDepthPair(URL, 0)); //добавляем полученную ссылку в список непросмотренных ссылок
        calculate(depth);
        outputResult();
    }

    /**
     * Функция ищет ссылки и добавляет их в пул, при нахождении
     * @param max_depth указанная глубина поиска
     * @throws IOException исключение, вызванное работой с сетевыми структурами
     */
    public static void calculate(int max_depth) throws IOException { //передаем максим глубину
        while(!URLPool.isEmpty()) { //пока есть непросмотренные ссылки
            // Временная переменная для хранения пары URLDepthPair
            URLDepthPair temp = URLPool.get(); //вытаскиваем первую непросмотренную ссылку

            // Опеределяем URL соединение
            URLConnection urlSocket = new URL(temp.getURL()).openConnection();
            urlSocket.setConnectTimeout(10_1000); //устанавливаем время, за которое будем получать данные

            // Работа с потоками данных URL-соединения
            InputStream stream_in = urlSocket.getInputStream(); //получаем из потока в строку наш файл html
            BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));

            // Получение страницы и её обработка, находим все ссылки на странице
            String str;
            while ((str = input.readLine()) != null) {
                // System.out.println(str); // Для отладки
                if (temp.getDepth() < max_depth) { //
                    while(str.length() > 0) { //пока длина строки больше 0, ищем BEFORE_URL
                        String newURL;
                        if (str.contains(BEFORE_URL + "\"" + HTTP)) {
                            newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                            newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                        }
                        else if (str.contains(BEFORE_URL + "\"" + HTTP_S)) {
                            newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP_S) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                            newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                        }
                        else break;

                        // Меняем строку
                        str = str.substring(str.indexOf(newURL) + newURL.length() + 1);

                        // Нашли новую ссылку
                        URLDepthPair foundURL = new URLDepthPair(newURL, temp.getDepth() + 1);
                        if (!URLPool.getCheckedItems().contains(foundURL)) {
                            URLPool.put(foundURL); // Добавили её в пул
                        }
                    }
                }
                else break;
            }

            // Закрываем потоки
            input.close();
            stream_in.close();
            urlSocket.getInputStream().close();

            // Добавляем просмотренную ссылку в список просмотренных
            if (temp.getDepth() < max_depth && !URLPool.getCheckedItems().contains(temp)) URLPool.putCheckedItems(temp);
        }
    }

    /**
     * Функция вывода списка найденных просмотренных ссылок
     */
    public static void outputResult() {
        for (URLDepthPair checked : URLPool.getCheckedItems()) {
            System.out.println(checked.toString());
        }
    }
}