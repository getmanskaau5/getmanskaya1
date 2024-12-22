package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Suspension {
    private Hinge hinge;
    private Differential differential;
    @Autowired
    public Suspension(Hinge hinge, Differential differential) {
        this.hinge = hinge;
        this.differential = differential;
    }

    @Override
    public String toString() {
        return "Suspension {" +
                "hinge=" + hinge +
                ", differential=" + differential +
                '}';
    }
}