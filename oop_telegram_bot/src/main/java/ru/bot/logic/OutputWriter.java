/**
 * Interface for sending messages to user
 */

package ru.bot.logic;

public interface OutputWriter {
    /**
     * send message to user
     * @param response user's response
     */
    void write(Response response);
}
