package ru.bot.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum RandomImageGenerator {
    INSTANCE(10);
    private final List<String> images = new ArrayList<>();
    private final String imagePrefix = "random_";

    RandomImageGenerator(int number) {
        for (int i = 0; i <= number; i++) images.add(imagePrefix + i);
    }

    public String getRandomImage() {
        int randomIndex = new Random().nextInt(images.size());
        return images.get(randomIndex);
    }
}
