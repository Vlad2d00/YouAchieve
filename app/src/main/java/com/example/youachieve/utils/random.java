package com.example.youachieve.utils;

import java.util.concurrent.ThreadLocalRandom;

class Random {
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public static long randLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}