package Labs.lab3;

import java.util.HashMap;

/**
 * Этот класс хранит базовое состояние, необходимое алгоритму A* для вычисления пути по карте.
 * Это состояние включает коллекцию "открытых путевых точек" и другую коллекцию "закрытых путевых точек"."
 * Кроме того, этот класс предоставляет основные операции,
 * необходимые алгоритму поиска пути A* для выполнения его обработки.
 **/
public class AStarState {
    /** Это ссылка на карту, по которой перемещается алгоритм A*. **/
    private final Map2D map;
    private final HashMap<Location, Waypoint> openWaypoints = new HashMap<>();  //списки хранят пары значений клеток
    private final HashMap<Location, Waypoint> closeWaypoints = new HashMap<>();

    /** Инициализирует новый объект состояния для использования алгоритма поиска пути A*. **/
    public AStarState(Map2D map) {
        if (map == null) throw new NullPointerException("map cannot be null");
        this.map = map;
    }

    /** Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap() {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * с минимальной общей стоимостью. Если открытых путевых точек нет,
     * этот метод возвращает <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
        final Waypoint[] MinOpenWaypoint = new Waypoint[1]; //создаем массив в котором 1 элемент
        final float[] j = new float[1];
        j[0] = Float.MAX_VALUE;                             //присваеваем максисальное значение
        openWaypoints.forEach((k,v) -> {                    //ищем минимальное значение
            if (v.getTotalCost() < j[0]) {
                MinOpenWaypoint[0] = v;
                j[0] = v.getTotalCost();
            }
        });
        return MinOpenWaypoint[0];
    }

    /**
     * Этот метод добавляет путевую точку в коллекцию "открытые путевые точки"
     * (или потенциально обновляет уже имеющуюся путевую точку).
     * Если в местоположении новой путевой точки еще нет открытой путевой точки,
     * то новая путевая точка просто добавляется в коллекцию.
     * Однако если в местоположении новой путевой точки уже есть путевая точка,
     * то новая путевая точка заменяет старую <em>только в том случае,
     * если</em> значение "предыдущей стоимости" новой путевой точки
     * меньше значения "предыдущей стоимости" текущей путевой точки.
     **/
    public boolean  addOpenWaypoint(Waypoint newWP) {
        if (openWaypoints.get(newWP.getLocation()) == null) openWaypoints.put(newWP.getLocation(), newWP);
        else if (newWP.getPreviousCost() < openWaypoints.get(newWP.getLocation()).getPreviousCost()) openWaypoints.replace(newWP.getLocation(), newWP);
        return false;
    }

    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints() {
        return openWaypoints.values().size();
    }

    /** Этот метод перемещает путевую точку в указанном месте из открытого списка в закрытый список. **/
    public void closeWaypoint(Location loc) {
        closeWaypoints.put(loc, openWaypoints.get(loc));
        openWaypoints.remove(loc);
    }

    /**
     * Возвращает true, если коллекция закрытых путевых точек
     * содержит путевую точку для указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc) {
        return closeWaypoints.containsKey(loc);
    }
}