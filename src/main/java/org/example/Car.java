package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private Wheel wheel;
    private Engine engine;
    private Accumulator accumulator;
    private Suspension suspension;

    // Конструктор для внедрения зависимостей
    @Autowired
    public Car( Wheel wheel, Engine engine, Accumulator accumulator, Suspension suspension) {
        this.wheel = wheel;
        this.engine = engine;
        this.accumulator = accumulator;
        this.suspension = suspension;
    }

    @Override
    public String toString() {
        return "Car {" +
                "wheel=" + wheel +
                ", engine=" + engine +
                ", accumulator=" + accumulator +
                ", suspension=" + suspension +
                '}';
    }

}
