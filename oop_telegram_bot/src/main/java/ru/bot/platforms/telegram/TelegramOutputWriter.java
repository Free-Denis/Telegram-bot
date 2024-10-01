/**
 * Output writer through Telegram API
 */

package ru.bot.platforms.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.logic.OutputWriter;
import ru.bot.logic.Response;

public class TelegramOutputWriter implements OutputWriter {
    /**
     *  Telegram bot instance used to send messages
     */
    private final TelegramBot telegram_bot;
    private long chat_id;

    /**
     *  Classic constructor
     * @param telegramBot Telegram bot instance used to send messages
     */
    public TelegramOutputWriter(TelegramBot telegramBot) {
        this.telegram_bot = telegramBot;
    }

    /**
     * send message to user through Telegram API
     * @param response user's response
     */
    public void write(Response response) {
        SendMessage message = new SendMessage();

        message.setChatId(this.chat_id);
        message.setText(response.getMessage());

        try {
            telegram_bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets context of using
     * @param chat_id id of the chat to send user's response
     */
    public void set_context(long chat_id) {
        this.chat_id = chat_id;
    }

    /**
     * clears all data from previous task
     */
    public void clear_context() {
        this.chat_id = 0;
    }
}
