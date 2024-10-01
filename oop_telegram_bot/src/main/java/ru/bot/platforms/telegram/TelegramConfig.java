/**
 *  loads and contains config information for telegram bot
 */

package ru.bot.platforms.telegram;

import java.io.IOException;
import java.util.Properties;

public class TelegramConfig {
    private final String BotToken;
    private final String BotName;
    private static final String FILE_PATH = "config/TelegramBot.properties";

    /**
     *  reads data from file FILE_PATH and initializes variables
     */
    public TelegramConfig() {
        try {
            // reading file
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            Properties properties = new Properties();
            properties.load(classloader.getResourceAsStream(FILE_PATH));

            // get token and bot's name from file
            this.BotToken = properties.getProperty("token");
            this.BotName = properties.getProperty("name");
        } catch (IOException e) {
            throw new IllegalStateException("""
                    \n---------------
                    Solution
                    You should create file path/name: resources/%s and paste there two lines:
                    1) token={Bot' token}
                    2) name={Bot's name}""".formatted(FILE_PATH), e);
        }
    }

    /**
     * Getter for bot's token
     * @return bot's token
     */
    public String get_bot_token() {
        return this.BotToken;
    }

    /**
     * Getter for bot's name
     * @return bot's name
     */
    public String get_bot_name() {
        return this.BotName;
    }
}
