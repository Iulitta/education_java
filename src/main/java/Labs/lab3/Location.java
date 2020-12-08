package Labs.lab3;

import java.util.Objects;

/** Этот класс представляет определенное местоположение на 2D-карте. Координаты-это целочисленные значения. **/
public class Location {
    /** X-координата этого местоположения. **/
    public int xCoord;

    /** Y-координата этого местоположения. **/
    public int yCoord;

    /** Создает новое местоположение с заданными целочисленными координатами. **/
    public Location(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    /** Создает новое местоположение с координатами (0, 0). **/
    public Location() {
        this(0, 0);
    }

    /** Функция равенства. **/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                                        //если совпадают по байтово
        if (obj == null || this.getClass() != obj.getClass()) return false;  //если объект пустой или его класс не совпадает с присланным объектом
        Location location = (Location) obj;                                  //конвертируем объект в location
        return (xCoord == location.xCoord && yCoord == location.yCoord);     //сравниваем текущие координаты с координатой location
    }

    /** Функция Хэш-Кода. **/
    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);  //возвращает значения хэш
    }
}
