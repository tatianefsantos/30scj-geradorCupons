package br.com.fiap.geradorCupons.utils;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandonUtils {

    private static Random random = new Random();

    public static int getRandomInt(int limit) {
        return random.nextInt(limit);
    }

    public static double getRandomDouble(int limit) {
        return ThreadLocalRandom.current().nextDouble(50, limit);
    }

    public static LocalDate getDataRandom() {
        LocalDate hoje = LocalDate.now();
        long minDay = LocalDate.of(hoje.getYear(), 1, 1).toEpochDay();
        long maxDay = LocalDate.of(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth()).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

}
