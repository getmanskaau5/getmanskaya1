package org.example;

public class Engine { private Starter starter;
    private SparkPlug sparkPlug;

    public Engine(Starter starter, SparkPlug sparkPlug) {
        this.starter = starter;
        this.sparkPlug = sparkPlug;
    }

    @Override
    public String toString() {
        return "Engine with " + starter.toString() + " and " + sparkPlug.toString();
    }
}
