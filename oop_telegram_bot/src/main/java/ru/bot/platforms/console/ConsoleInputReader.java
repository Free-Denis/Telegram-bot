/**
 *  A simple class for reading input data from console
 */

package ru.bot.platforms.console;

import ru.bot.logic.Request;
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
        return new Request(user_input);
    }
}
