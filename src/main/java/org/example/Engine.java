package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Engine { private Starter starter;
    private SparkPlug sparkPlug;
@Autowired
    public Engine(Starter starter, SparkPlug sparkPlug) {
        this.starter = starter;
        this.sparkPlug = sparkPlug;
    }

    @Override
    public String toString() {
        return "Engine with " + starter.toString() + " and " + sparkPlug.toString();
    }
}
