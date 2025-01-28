/**
 * Bot working through console
 */

package ru.bot.platforms.console;

import ru.bot.logic.handlers.RequestHangerImp;
import ru.bot.logic.Request;
import ru.bot.logic.Response;
import ru.bot.logic.components.TextComponent;
import ru.bot.platforms.Bot;

public class ConsoleBot implements Bot {

    /**
     * starts bot's lifecycle
     */
    @Override
    public void startBot() {
        // basic initialization
        ConsoleInputReader input = new ConsoleInputReader();
        ConsoleOutputWriter output = new ConsoleOutputWriter();
        RequestHangerImp hanger = new RequestHangerImp();

        // Hello message
        Response response = new Response();
        response.setComponent(new TextComponent("Hello, my friend!"));
        output.write(response);
        // start lifecycle
        while (true) {
            Request request = input.read();
            hanger.handle(request, output);
        }
    }
}
