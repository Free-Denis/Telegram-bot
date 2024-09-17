package ru.bot.platforms.console;

import ru.bot.logic.Response;

class OutputWriter implements ru.bot.logic.OutputWriter {
    public void write(Response response) {
        System.out.println(response.getMessage());
    }
}
