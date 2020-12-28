package Labs.lab7;

public class URLDepthPair {

    private String URL; // Поле класса со строкой URL, строка для ссылки
    private int depth;  // Поле класса с глубиной поиска, число для глубины

    /**
     * Конструктор класса с параметрами
     * @param URL строка URL
     * @param depth глубина поиска
     */
    public URLDepthPair(String URL, int depth) { //приравниваем к экземпляру класса
        this.URL = URL;
        this.depth = depth;
    }

    /**
     * Переопределение метода toString
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "URL = " + URL + ", depth = " + depth;
    }

    /**
     * Getter для строки URL
     * @return текущая строка URL объекта
     */
    public String getURL() { //возвращает текущее значение ссылки
        return URL;
    }

    /**
     * Getter для глубины поиска
     * @return текущая глубина поиска объекта
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter для строки URL
     * @param URL новая строка URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Setter для глубины поиска
     * @param depth новая глубина поиска
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
}