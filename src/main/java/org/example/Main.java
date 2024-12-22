package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);


//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Car car = context.getBean(Car.class);
//        System.out.println(car);



//            Wheel wheel = new Wheel();
//            Starter starter = new Starter();
//            SparkPlug sparkPlug = new SparkPlug();
//            Engine engine = new Engine(starter, sparkPlug);
//            Accumulator accumulator = new Accumulator();
//            Hinge hinge = new Hinge();
//            Differential differential = new Differential();
//            Suspension suspension = new Suspension(hinge, differential);
//
//            Car car = new Car(wheel, engine, accumulator, suspension);
//            System.out.println(car);
//


        // Запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("car_output.txt"))) {
            writer.write(car.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





