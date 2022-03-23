package com.example.util;

import java.util.Random;

public class Randomizer {
    public static int generateRandomNumber() {
        Random rng = new Random();
        return rng.nextInt() & Integer.MAX_VALUE;
    }
}
