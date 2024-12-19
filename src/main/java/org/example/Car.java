package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Car { private Wheel wheel;
    private Engine engine;
    private Accumulator accumulator;
    private Suspension suspension;
@Autowired
    public Car(Wheel wheel, Engine engine, Accumulator accumulator, Suspension suspension) {
        this.wheel = wheel;
        this.engine = engine;
        this.accumulator = accumulator;
        this.suspension = suspension;
    }

    @Override
    public String toString() {
        return "Car with:\n" +
                wheel.toString() + "\n" +
                engine.toString() + "\n" +
                accumulator.toString() + "\n" +
                suspension.toString();
    }
}
