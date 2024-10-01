/**
 *  Interface for classes that get input data from user
 */

package ru.bot.platforms;
import ru.bot.logic.Request;

public interface InputReader {
    /**
     * get user request
     * @return wrapper for user request
     */
    Request read();
}
