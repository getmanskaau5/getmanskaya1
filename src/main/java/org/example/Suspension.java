package org.example;

public class Suspension {private Hinge hinge;
    private Differential differential;

    public Suspension(Hinge hinge, Differential differential) {
        this.hinge = hinge;
        this.differential = differential;
    }

    @Override
    public String toString() {
        return "Suspension with " + hinge.toString() + " and " + differential.toString();
    }
}
