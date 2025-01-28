/**
 * Telegram bot
 */

package ru.bot.platforms.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bot.converters.Converter;
import ru.bot.logic.OutputWriterPoolImp;
import ru.bot.logic.Request;
import ru.bot.logic.components.TextComponent;
import ru.bot.logic.handlers.RequestHanger;
import ru.bot.logic.handlers.RequestHangerMain;
import ru.bot.platforms.Bot;


public class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String botUsername;
    private final RequestHanger handler = new RequestHangerMain();
    private final OutputWriterPoolImp<TelegramOutputWriter> pool = new OutputWriterPoolImp(() -> new TelegramOutputWriter(this));
    private final Converter converter;

    /**
     * Initializes a new instance
     * @param botToken Api bot token for initialization bot in telegram
     */
    public TelegramBot(String botToken, String botUsername, Converter converter) {
        super(botToken);
        this.botUsername = botUsername;
        this.converter = converter;
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
            Request request = new Request();
            request.setComponent(new TextComponent(message_text));

            long chat_id = update.getMessage().getChatId();
            TelegramOutputWriter outputWriter = pool.get();
            outputWriter.setContext(chat_id);

            handler.handle(request, outputWriter);
            pool.release(outputWriter);
        }
    }

    /**
     * Just returns bot's username
     * @return bot's username
     */
    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    public Converter getConverter() {
        return converter;
    }
}
