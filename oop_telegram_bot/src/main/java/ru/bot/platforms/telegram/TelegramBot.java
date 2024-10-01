/**
 * Telegram bot
 */

package ru.bot.platforms.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bot.logic.Request;
import ru.bot.logic.RequestHangerImp;
import ru.bot.platforms.Bot;


public class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String bot_username;
    private final RequestHangerImp handler = new RequestHangerImp();
//    private final TelegramOutputWriter output_writer = new TelegramOutputWriter(this);
    private final TelegramOutputWriterPool pool = new TelegramOutputWriterPool(this);

    /**
     * Initializes a new instance
     * @param bot_token Api bot token for initialization bot in telegram
     */
    public TelegramBot(String bot_token, String bot_username) {
        super(bot_token);
        this.bot_username = bot_username;
    }

    /**
     * Starts bot's lifecycle
     */
    public void startBot() {
        try {
            TelegramBotsApi app = new TelegramBotsApi(DefaultBotSession.class);
            app.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * called by receiving a new user's request
     * @param update Contains information about user's request
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            Request request = new Request(message_text);

            long chat_id = update.getMessage().getChatId();
            TelegramOutputWriter output_writer = pool.getWriter();
            output_writer.set_context(chat_id);

            handler.handle(request, output_writer);
            pool.releaseWriter(output_writer);
        }
    }

    /**
     * Just returns bot's username
     * @return bot's username
     */
    @Override
    public String getBotUsername() {
        return this.bot_username;
    }
}
