package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Mainxml {
    public static void main(String[] args) {
//     Используем класс конфигурации AppConfig

        

//     Получаем объект Car
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean(Car.class);
        System.out.println(car);

//     Запись в файл
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("car_output1.txt"))) {
        writer.write(car.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }
