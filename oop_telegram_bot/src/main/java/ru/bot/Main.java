/**
 *  Main class to create all bots
 */

package ru.bot;

import ru.bot.platforms.telegram.TelegramBot;
import ru.bot.platforms.telegram.TelegramConfig;
import ru.bot.platforms.console.ConsoleBot;

public class Main {
    /**
     * start point of project
     * @param args console args
     */
    public static void main(String[] args) {
        // Initialize telegram bot
        TelegramConfig tg_config = new TelegramConfig();
        TelegramBot telegramBot = new TelegramBot(tg_config.get_bot_token(), tg_config.get_bot_name());
        // start telegram bot
        telegramBot.startBot();

        // initialize console bot
        ConsoleBot bot = new ConsoleBot();
        // start console bot's lifecycle
        bot.startBot();
    }
}