package Labs.lab7;

import java.util.LinkedList;

/**
 * Класс для работы методом "First In, First Out" (реализация очереди)
 */
public class FIFO {

    private final int maxSize; // Максимальное количество элементов в буфере
    private final LinkedList<URLDepthPair> items; // Поле класса для хранения списка объектов URLDepthPair
    private final LinkedList<URLDepthPair> checkedItems; // Поле класса для хранения списка просмотренных URLDepthPair

    /**
     * Конструктор класса
     * @param maxSize максимальное количество элементов в буфере
     */
    public FIFO(int maxSize) { //задаем максимальный размер непроверенных ссылок
        this.maxSize = maxSize;
        this.items = new LinkedList<>();
        this.checkedItems = new LinkedList<>();
    }

    /**
     * Функция добавления в буфер обрабатываемого объекта
     * @param obj объект класса URLDepthPair
     * @return true, если объект был добавлен, false в противном случае
     */
    public boolean put(URLDepthPair obj) { //можем добавить в очередь, если элементов в непроверенном списке меньше max
        boolean flagAdded = false;
        if (items.size() < maxSize) {
            items.addLast(obj); //объект добавляется в конец списка
            flagAdded = true;
        }
        return flagAdded;
    }

    /**
     * Функция получения объекта из буфера
     * @return объект класса URLDepthPair из начала списка
     */
    public URLDepthPair get() { //забираем первый элемент из списка, удаляя его
        URLDepthPair item = null;
        if (items.size() > 0) item = items.removeFirst(); //получаем объект из начала списка, проверяя что он больше 0
        return item;
    }

    /**
     * Функция проверяет, пустой ли буфер
     * @return возвращает true, если буфер пустой, false в противном случае
     */
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Функция добавления в список просмотренной ссылки
     * @param obj прсмотренная ссылка
     */
    public void putCheckedItems(URLDepthPair obj) {
        checkedItems.add(obj); //добавляем объект в список просмотренных
    }

    /**
     * Функция получения всего списка просмотренных ссылок
     * @return список LinkedList<URLDepthPair> всех просмотренных ссылок
     */
    public LinkedList<URLDepthPair> getCheckedItems() { //возвращаем все просмотренные ссылки
        return this.checkedItems;
    }
}