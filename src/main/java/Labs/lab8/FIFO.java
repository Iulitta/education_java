package Labs.lab8;

import java.util.LinkedList;

/**
 * Класс для работы методом "First In, First Out" (реализация очереди)
 */
public class FIFO {

    private final int maxDepth; // Максимальное количество элементов в буфере
    private final LinkedList<URLDepthPair> items; // Поле класса для хранения списка объектов URLDepthPair
    private final LinkedList<URLDepthPair> checkedItems; // Поле класса для хранения списка просмотренных URLDepthPair
    private int waitingThreads; // Поле класса для количества ожидающих потоков

    /**
     * Конструктор класса
     * @param maxSize максимальная глубина
     */
    public FIFO(int maxSize) {
        this.maxDepth = maxSize;
        this.items = new LinkedList<>();
        this.checkedItems = new LinkedList<>();
        this.waitingThreads = 0;
    }

    /**
     * Функция добавления в буфер обрабатываемого объекта
     * @param obj объект класса URLDepthPair
     * @return true, если объект был добавлен, false в противном случае
     */
    public synchronized boolean put(URLDepthPair obj) {
        // synchronized - потокобезопасный метод класса
        boolean flagAdded = false;
        if (obj.getDepth() < maxDepth && !checkedItems.contains(obj)) { //если глубина объекта меньше макс, то добавляем в список непросмотренным
            items.addLast(obj);     // Объект добавляется в конец списка
            flagAdded = true;
            // Если ожидающих потоков больше одного и получена непросмотренная ссылка
            if (waitingThreads > 0) waitingThreads--;
            this.notify();          //сообщение потокам, что появилась непросмотренная ссылка
        }
        return flagAdded;
    }

    /**
     * Функция получения объекта из буфера
     * @return объект класса URLDepthPair из начала списка
     */
    public synchronized URLDepthPair get() {
        URLDepthPair item;
        // Если нет непросмотренных ссылок
        if (items.size() == 0) {
            waitingThreads++;                       //увеличиваем количество ожидающих потоков
            try {
                this.wait();                        //заставляем поток ждать, если нет ссылок
            } catch (InterruptedException e) {      //исключение
                System.out.println("InterruptedException:" + e);
            }
        }

        item = items.removeFirst();     //получаем объект из начала списка
        this.putCheckedItems(item);     //добавляем объект в список просмотренных
        return item;
    }

    /**
     * Функция добавления в список просмотренной ссылки
     * @param obj прсмотренная ссылка
     */
    public void putCheckedItems(URLDepthPair obj) {
        // Синхронизация потоков (потокобезопасный код)
        synchronized (checkedItems) {
            // Если ссылки нет в списке просмотренных
            if (!checkedItems.contains(obj)) checkedItems.add(obj);  // Добавляем объект в список просмотренных
        }
    }

    /**
     * Функция получения всего списка просмотренных ссылок
     * @return список LinkedList<URLDepthPair> всех просмотренных ссылок
     */
    public synchronized LinkedList<URLDepthPair> getCheckedItems() {
        return this.checkedItems;
    }

    /**
     * Getter для количества ожидающих потоков
     * @return количество ожидающих потоков
     */
    public int getWaitCount() {
        return this.waitingThreads; //может вернуть количество ожидающих потоков
    }
}
