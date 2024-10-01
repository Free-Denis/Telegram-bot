/**
 * This class realizes handling and process user requests
 */

package ru.bot.logic;

public class RequestHangerImp implements ru.bot.logic.RequestHanger {

    /**
     * main method for handling user requests
     * @param request user request info
     * @param writer object to send response to user
     */
    public void handle(Request request, OutputWriter writer) {
        Response response = new Response("You typed: " + request.getMessage());
        writer.write(response);
    }
}
