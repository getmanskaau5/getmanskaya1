import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Необходимо передать два аргумента: путь к файлу с эллипсом и путь к файлу с точками.");
            return;
        }

        String ellipseFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            // Считываем данные из файла с эллипсом
            Scanner ellipseScanner = new Scanner(new File(ellipseFilePath));
            double xCenter = ellipseScanner.nextDouble();
            double yCenter = ellipseScanner.nextDouble();
            double a = ellipseScanner.nextDouble();
            double b = ellipseScanner.nextDouble();
            ellipseScanner.close();

            // Считываем данные из файла с точками
            Scanner pointsScanner = new Scanner(new File(pointsFilePath));
            while (pointsScanner.hasNext()) {
                double x = pointsScanner.nextDouble();
                double y = pointsScanner.nextDouble();

                // Рассчитываем положение точки относительно эллипса
                double value = ((x - xCenter) * (x - xCenter)) / (a * a) + ((y - yCenter) * (y - yCenter)) / (b * b);

                if (Math.abs(value - 1) < 1e-9) {
                    System.out.println("0"); // Точка на эллипсе
                } else if (value < 1) {
                    System.out.println("1"); // Точка внутри эллипса
                } else {
                    System.out.println("2"); // Точка снаружи эллипса
                }
            }
            pointsScanner.close();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }
}