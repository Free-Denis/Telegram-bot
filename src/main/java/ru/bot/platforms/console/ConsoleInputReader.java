/**
 *  A simple class for reading input data from console
 */

package ru.bot.platforms.console;

import ru.bot.logic.Request;
import ru.bot.logic.components.TextComponent;
import ru.bot.platforms.InputReader;
import java.util.Scanner;

class ConsoleInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * main method for reading
     * @return wrapper for user request
     */
    @Override
    public Request read() {
        String user_input = scanner.nextLine();
        Request request = new Request();
        request.setComponent(new TextComponent(user_input));

        return request;
    }
}
