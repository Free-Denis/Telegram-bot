/**
 *  TRASH CLASS. WILL BE REMOVED
 */


package ru.bot.logic;

import ru.bot.platforms.telegram.TelegramBot;
import ru.bot.platforms.telegram.TelegramOutputWriter;

import java.util.Stack;


public class OutputWriterPoolImp<T> implements OutputWriterPool {
    private static final int START_NUMBER_OF_WRITERS = 5;
//    private final TelegramBot bot;

    private final Stack<T> stack = new Stack<>();

    public OutputWriterPoolImp() {
//        for (int i = 0; i < START_NUMBER_OF_WRITERS; i++) stack.push(this.createContents());
    }

    /**
     * releases output writer
     * @param writer output writer to release
     */
    @Override
    public void releaseWriter(TelegramOutputWriter writer) {
//        stack.push(writer);
    }

    /**
     * get output writer
     * @return output writer
     */
//    @Override
    public OutputWriter getWriter() {
        return new TelegramOutputWriter(new TelegramBot("as", "as"));
//        if (stack.isEmpty()) return (new TelegramOutputWriter());
//        else return stack.pop();
    }

    T createContents(Class<T> transitive_class) throws InstantiationException, IllegalAccessException {
        return transitive_class.newInstance();
    }
}
