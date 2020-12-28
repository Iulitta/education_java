package Labs.lab8;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";
    public static final int MAXDepth = 4; //максимальная глубина
    public static final int MAXThreads = 32; //максимальное количество потоков
    public static FIFO URLPool;

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        System.out.print("Input a num of threads: "); //количество потоков
        int numThreads = in.nextInt();
        in.close();     // закрываем поток ввода

        // Проверяем значения переменных, выбрасываем исключения если не подходит по условию
        if (depth > MAXDepth) throw new IllegalArgumentException("Depth must be <" + MAXDepth);
        if (numThreads > MAXThreads) throw new IllegalArgumentException("Num of threads must be < " + MAXThreads);
        if (depth <= 0) throw new IllegalArgumentException("Incorrect input data: depth must be > 0");
        if (numThreads <= 0) throw new IllegalArgumentException("Incorrect input data: num of threads must be > 0");

        // Стартовые значения для пула ссылок
        URLPool = new FIFO(depth); //не максимальное количество ссылок в непросмотренном списке, а максимальная глубина
        URLPool.put(new URLDepthPair(URL, 0));

        // Начальное число активных потоков
        int initialActive = Thread.activeCount();

        // Главный поток следит за состоянием пула адресов
        while (URLPool.getWaitCount() != numThreads) { //пока ожидающее число не равно введенному нами числу потоков
            // Создаём потоки
            if (Thread.activeCount() - initialActive < numThreads) { //пока кол-во активных потоков не станет равно numThreads
                new Thread(new CrawlerTask(URLPool)).start(); //запускаем столько потоков, сколько задано в numThreads
            }
            else {
                try {
                    Thread.sleep(100);              // "Успыляем" главный поток на 0.1 секунду
                } catch (InterruptedException ignore) {
                    System.out.println("InterruptedException, ignoring...");
                }
            }
        }
        outputResult(URLPool);    // Выводим результат работы программы
        System.exit(1);     // Принудительно завершаем потоки и выходим из программы
    }

    /**
     * Функция ищет ссылки и добавляет их в пул, при нахождении
     */
    public static ArrayList<String> calculate(URLDepthPair temp) throws IOException {
        ArrayList<String> URLs = new ArrayList<>();     // Список строк для найденных ссылок

        // Устанавливаем URL-соединение
        URLConnection urlSocket = null;
        try {
            urlSocket = new URL(temp.getURL()).openConnection();
            urlSocket.setConnectTimeout(10_1000);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e.getMessage());
        }
        assert urlSocket != null;

        // Работа с потоками данных URL-соединения
        InputStream stream_in;
        try {
            stream_in = urlSocket.getInputStream();
        } catch (IOException ignored) {     // Игнорируем исключение
            return URLs;                    // Возвращаем список найденных ссылок
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));

        // Получение страницы и её обработка
        while (true) {
            String str;     // Переменная-строка для получения html-файла страницы

            try {
                str = input.readLine();     // Получаем страницу в виде строки из потока
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                return URLs;
            }

            if (str == null) return URLs;   // Если поток был null, возвращаем список ссылок

            // Основной цикл для получения ссылок
            while (str.length() > 0) {
                String newURL;                                           // Переменная-строка для новой строки
                if (str.contains(BEFORE_URL + "\"" + HTTP)) {            // Если ссылка содержит http
                    newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                    newURL = newURL.substring(0, newURL.indexOf("\""));  // Обрезаем адрес справа
                } else if (str.contains(BEFORE_URL + "\"" + HTTP_S)) {   // Если ссылка содержит https
                    newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP_S) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                    newURL = newURL.substring(0, newURL.indexOf("\""));  // Обрезаем адрес справа
                } else break; //

                // Меняем строку
                str = str.substring(str.indexOf(newURL) + newURL.length() + 1);

                // Нашли новую ссылку и добавили в список ссылок
                URLs.add(newURL);
            }
        }
    }

    /**
     * Функция вывода списка найденных просмотренных ссылок
     * @param pool пул ссылок
     */
    public static void outputResult(FIFO pool) {
        for (Object checked : pool.getCheckedItems()) {
            System.out.println(checked.toString());
        }
    }
}