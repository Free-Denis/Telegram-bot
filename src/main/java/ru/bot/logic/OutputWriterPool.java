package ru.bot.logic;


import ru.bot.platforms.Telegram.TelegramOutputWriter;

public interface OutputWriterPool<T> {
    /**
     * releases output writer
     */
    void release(T item);

    /**
     * get output writer
     * @return output writer
     */
    T get();
}
