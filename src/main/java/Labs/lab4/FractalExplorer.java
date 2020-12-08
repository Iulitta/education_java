package Labs.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/** Класс пользовательского интерфейса для отображения фракталов **/
public class FractalExplorer {

    // Приватные поля класса
    private final Rectangle2D.Double aDouble;
    private final FractalGenerator fractalGenerator;
    private JImageDisplay imageDisplay;
    private final int size;

    /**
     * Конструктор класса
     * @param size размер окна
     **/
    public FractalExplorer (int size){  //передаем размер окна
        if (size <= 0) throw new IllegalArgumentException("Size must be better than 0; got " + size); //если<0 - ошибка
        else this.size = size;
        aDouble = new Rectangle2D.Double();
        new Mandelbrot().getInitialRange(aDouble);  //инициализируем объекты
        fractalGenerator = new Mandelbrot();  //экземпляр класса мальдеброт
    }

    /** Метод для создания пользовательского интерфейса **/
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");        //создаем окно с названием
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //выход из окна
        Container contentPane = frame.getContentPane();           //контейнер
        fractalGenerator.getInitialRange(aDouble);

        contentPane.setLayout(new BorderLayout());                //слои контейнеров
        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MyMouseListener().mouseListener); //проверяет нажатие мыши на объект
        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset Display"); //создаем кнопку с текстом
        resetButton.addActionListener(e -> { //задаем кнопке действие
                    imageDisplay.clearImage(); //очищаем изображение
                    fractalGenerator.getInitialRange(aDouble); //вызываем первоначальный размер фрактала
                    drawFractal(); //отрисовывается фрактал
                }
        );
        contentPane.add(resetButton, BorderLayout.SOUTH); //нижняя граница для кнопки

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /** Вспомогательный метод для вывода фрактала на экран, функция рисования фрактала **/
    private void drawFractal() {
        double xCoord;
        double yCoord;
        int numIters;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                xCoord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, i);
                yCoord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, j);
                numIters = fractalGenerator.numIterations(xCoord, yCoord);
                if (numIters == -1) imageDisplay.drawPixel(i, j, 0);
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(i, j, rgbColor);
                }
            }
        }
        imageDisplay.repaint(); //обновляет дисплей, показывает картинку
    }

    /** Класс для обработки нажатий мыши по дисплею **/
    private class MyMouseListener extends MouseAdapter {

        public MouseListener mouseListener = new MouseListener() {

            /**
             * Переопределение события при нажатии левой кнопки мышки по фракталу
             * @param e MouseEvent, стандартный элемент управления
             **/
            @Override
            public void mouseClicked(MouseEvent e) {
                if (size >= 0) {
                    double xCord;
                    double yCord;
                    xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, e.getX()); //функция увеличения координаты фрактала
                    yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, e.getY());
                    FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);
                    drawFractal();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        };

    }

    /**
     * Главный метод (точка входа)
     * @param args аргументы командной строки (не используются)
     **/
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(600); //размер окна
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal(); //для нарисования фрактала
    }
}