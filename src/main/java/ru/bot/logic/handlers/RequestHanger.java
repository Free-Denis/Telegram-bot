/**
 * Interface for classes. Implementation classes realize handling and process user requests
 */

package ru.bot.logic.handlers;

import ru.bot.logic.OutputWriter;
import ru.bot.logic.Request;

public interface RequestHanger {
    /**
     * main method for handling user requests
     * @param request user request info
     * @param writer object to send response to user
     */
    void handle(Request request, OutputWriter writer);
}
