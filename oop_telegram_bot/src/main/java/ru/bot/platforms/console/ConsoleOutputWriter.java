/**
 *  Sends response to user using console
 */

package ru.bot.platforms.console;

import ru.bot.logic.OutputWriter;
import ru.bot.logic.Response;

public class ConsoleOutputWriter implements OutputWriter {
    /**
     * Send message to user
     * @param response response for user
     */
    public void write(Response response) {
        System.out.println(response.getMessage());
    }
}
