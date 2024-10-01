/**
 *  Telegram's Pool for output writers.
 */

package ru.bot.platforms.telegram;

import ru.bot.logic.OutputWriterPool;
import java.util.Stack;

/* PROBLEM: Необходимо создать класс с пулом. Но проблема возникает на стадии проектирования:
 Если мы хотим сделать его универсальным для всех OutputWriter, то необходимо судя по всему пользоваться
 шаблонами. Там возникают свои проблемы, о которых лень писать. Но с другой стороны можно воспользоваться объявлением
 везде OutputWriter ведь это интерфейс. Но тогда невозможно воспользоваться методом clearContext либо же его придётся определять у всех
 + к этому ещё и в setContext видимо придётся добавлять объект класса бота, что не очень мне нравится
 */

public class TelegramOutputWriterPool implements OutputWriterPool {
    private static final int START_NUMBER_OF_WRITERS = 5;
    private final TelegramBot bot;
    private final Stack<TelegramOutputWriter> stack = new Stack<>();

    /**
     * creates default number of output writers and stores it in stack
     * @param bot Telegram bot instance used by output writers
     */
    public TelegramOutputWriterPool(TelegramBot bot) {
        this.bot = bot;
        for (int i = 0; i < START_NUMBER_OF_WRITERS; i++) stack.push(new TelegramOutputWriter(bot));
    }

    /**
     * releases output writer
     * @param writer output writer to release
     */
    // TODO: change TelegramOutputWriter
    @Override
    public void releaseWriter(TelegramOutputWriter writer) {
        writer.clear_context();
        stack.push(writer);
    }

    /**
     * get output writer
     * @return output writer
     */
    @Override
    public TelegramOutputWriter getWriter() {
        if (stack.isEmpty()) return (new TelegramOutputWriter(this.bot));
        else return stack.pop();
    }
}
