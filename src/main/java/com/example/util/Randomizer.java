package com.example.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component("randomizer")
public class Randomizer {
    public int generateRandomNumber() {
        Random rng = new Random();
        return rng.nextInt() & Integer.MAX_VALUE;
    }
}
