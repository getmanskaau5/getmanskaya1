package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Внедрение зависимостей через XML + Java аннотации
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    @Bean
    public Wheel wheel() {
        return new Wheel();
    }

    @Bean
    public Starter starter() {
        return new Starter();
    }

    @Bean
    public SparkPlug sparkPlug() {
        return new SparkPlug();
    }

    @Bean
    public Engine engine() {
        return new Engine(starter(), sparkPlug());
    }

    @Bean
    public Accumulator accumulator() {
        return new Accumulator();
    }

    @Bean
    public Hinge hinge() {
        return new Hinge();
    }

    @Bean
    public Differential differential() {
        return new Differential();
    }

    @Bean
    public Suspension suspension() {
        return new Suspension(hinge(), differential());
    }

    @Bean
    public Car car() {
        return new Car(wheel(), engine(), accumulator(), suspension());
    }
}