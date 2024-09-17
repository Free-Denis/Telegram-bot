package ru.bot.platforms.console;

import ru.bot.logic.RequestHangerImpl;
import ru.bot.logic.Request;

public class ConsoleBot implements ru.bot.platforms.Bot {
    public void startBot() {
        InputReader input = new InputReader();
        OutputWriter output = new OutputWriter();
        RequestHangerImpl hanger = new RequestHangerImpl();

        System.out.println("Hello");
        while (true) {
            Request request = input.read();
            hanger.handle(request, output);

        }
    }
}
