package ru.bot.logic;


import ru.bot.platforms.telegram.TelegramOutputWriter;

public interface OutputWriterPool {
    /**
     * releases output writer
     * @param writer output writer to release
     */
    void releaseWriter(TelegramOutputWriter writer);

    /**
     * get output writer
     * @return output writer
     */
    OutputWriter getWriter();
}
