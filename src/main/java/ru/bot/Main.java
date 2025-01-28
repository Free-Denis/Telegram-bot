/**
 *  Main class to create all bots
 */

package ru.bot;

import ru.bot.converters.ConverterAssets;
import ru.bot.platforms.Telegram.TelegramBot;
import ru.bot.platforms.Telegram.TelegramConfig;
import ru.bot.platforms.console.ConsoleBot;
import ru.bot.storage.JSONConverterStorage;

public class Main {
    private static final String TELEGRAM_CONVERTER_STORAGE_PATH = "converter_storages/telegram.json";
    private static final String LOCAL_CONVERTER_STORAGE_PATH = "converter_storages/base.json";
    private static final String LOCAL_ASSETS_PREFIX = "images/";
    private static final String[] REQUIRED_LOCAL_IMAGES = {"/start"};

    /**
     * start point of project
     * @param args console args
     */
    public static void main(String[] args) {
        // start telegram bot
        TelegramBot telegramBot = getTelegramBot();
        telegramBot.startBot();

        // initialize console bot
        ConsoleBot bot = getConsoleBot();
        // start console bot's lifecycle
        bot.startBot();
    }

    private static TelegramBot getTelegramBot() {
        // initialize converters
        JSONConverterStorage telegramStorage = new JSONConverterStorage(
                TELEGRAM_CONVERTER_STORAGE_PATH,
                new String[0]
        );

        JSONConverterStorage localStorage = new JSONConverterStorage(
                LOCAL_CONVERTER_STORAGE_PATH,
                REQUIRED_LOCAL_IMAGES
        );

        ConverterAssets telegramConverter = new ConverterAssets(telegramStorage, localStorage, LOCAL_ASSETS_PREFIX);

        // Initialize telegram bot
        TelegramConfig tg_config = TelegramConfig.getTelegramConfig();
        return new TelegramBot(tg_config.getBotToken(), tg_config.getBotName(), telegramConverter);
    }

    private static ConsoleBot getConsoleBot() {
        return new ConsoleBot();
    }
}