package ru.bot.logic;

public class RequestHangerImpl implements RequestHanger {
    public void handle(Request request, OutputWriter writer) {
        Response response = new Response("You typed: " + request.getMessage());
        writer.write(response);
    }
}
