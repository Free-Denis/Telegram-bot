package ru.bot.platforms.console;

import ru.bot.logic.Request;

import java.util.Scanner;

public class InputReader implements ru.bot.platforms.InputReader {
    private final Scanner scanner = new Scanner(System.in);

    public Request read() {
        String user_input = scanner.nextLine();
        return new Request(user_input);
    }
}
