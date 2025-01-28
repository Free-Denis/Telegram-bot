/**
 *  Pool for reusable objects
 */

package ru.bot.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OutputWriterPoolImp<T extends OutputWriterPoolImp.Cleanable> implements OutputWriterPool<T> {
    private final List<T> list = new ArrayList<>();
    private final Supplier<T> objectCreator;

    /**
     * creates default number of output writers and stores it in stack
     * @param objectCreator lambda function to create new items in list
     */
    public OutputWriterPoolImp(Supplier<T> objectCreator) {
        this.objectCreator = objectCreator;
    }

    /**
     * releases output writer
     * @param item item to store in pool
     */
    @Override
    public void release(T item) {
        item.clean();
        list.add(item);
    }

    /**
     * get item from pool
     * @return item from pool
     */
    @Override
    public T get() {
        if (list.isEmpty()) return this.objectCreator.get();
        else return list.getLast();
    }

    public interface Cleanable {
        void clean();
    }
}
