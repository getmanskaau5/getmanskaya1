package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class mainAn { public static void main(String[] args) {
    ApplicationContext contextAnnotation = new AnnotationConfigApplicationContext(AppConfig.class);
    Car carFromAnnotation = contextAnnotation.getBean(Car.class);
    System.out.println(carFromAnnotation);
    writeToFile(carFromAnnotation.toString(), "car_output_annotation.txt");
}

    private static void writeToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

