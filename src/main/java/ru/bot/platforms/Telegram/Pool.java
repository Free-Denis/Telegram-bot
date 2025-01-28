package ru.bot.platforms.Telegram;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Pool<T extends Pool.Cleanable> {
    private final List<T> pool = new ArrayList<>();
    private final Supplier<T> objectCreator;

    public Pool(Supplier<T> objectCreator) {
        this.objectCreator = objectCreator;
    }

    public void free(T obj) {
        obj.clean();
        pool.add(obj);
    }

    public T pool() {
        if (pool.isEmpty()) {
            return objectCreator.get();
        }
        return pool.removeLast();
    }

    public interface Cleanable {
        void clean();
    }
}
