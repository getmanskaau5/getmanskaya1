package org.example;

import org.springframework.stereotype.Component;

@Component
public class Accumulator {
    @Override
    public String toString() {
        return "Accumulator { capacity=12V }";
    }
}