/**
 * This class realizes handling and process user requests
 */

package ru.bot.logic.handlers;

import ru.bot.logic.OutputWriter;
import ru.bot.logic.Request;
import ru.bot.logic.Response;
import ru.bot.logic.components.TextComponent;

public class RequestHangerImp implements RequestHanger {

    /**
     * main method for handling user requests
     * @param request user request info
     * @param writer object to send response to user
     */
    public void handle(Request request, OutputWriter writer) {
        Response response = new Response();

        String message = "You typed: " + request.getComponent(TextComponent.class).message();
        TextComponent textComponent = new TextComponent(message);
        response.setComponent(textComponent);

        writer.write(response);
    }
}
