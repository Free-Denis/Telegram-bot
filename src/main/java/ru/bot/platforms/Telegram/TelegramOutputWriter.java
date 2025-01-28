/**
 * Output writer through Telegram API
 */

package ru.bot.platforms.Telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.converters.Converter;
import ru.bot.logic.*;
import ru.bot.logic.components.AnimationComponent;
import ru.bot.logic.components.PhotoComponent;
import ru.bot.logic.components.TextComponent;

public class TelegramOutputWriter implements OutputWriter, OutputWriterPoolImp.Cleanable {
    /**
     *  Telegram bot instance used to send messages
     */
    private final TelegramBot telegramBot;
    private long chatId;

    /**
     *  Classic constructor
     * @param telegramBot Telegram bot instance used to send messages
     */
    public TelegramOutputWriter(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * send message to user through Telegram API
     * @param response user's response
     */
    public void write(Response response) {

        TextComponent textComponent = response.getComponent(TextComponent.class);
        if (textComponent != null) sendTextComponent(textComponent);

        AnimationComponent animationComponent = response.getComponent(AnimationComponent.class);
        if (animationComponent != null) sendAnimationComponent(animationComponent);

        PhotoComponent photoComponent = response.getComponent(PhotoComponent.class);
        if (photoComponent != null) sendPhotoComponent(photoComponent);
    }

    private void sendTextComponent(TextComponent component) {
        SendMessage message = new SendMessage();

        message.setChatId(this.chatId);
        message.setText(component.message());

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhotoComponent(PhotoComponent photoComponent) {
        try {
            InputFile inputFile;
            String innerId = photoComponent.photoId();
            Converter converter = telegramBot.getConverter();
            String photoId = converter.convertId(innerId);

            Boolean updateRequired = false;
            if (photoId == null) {
                inputFile = new InputFile(converter.getAsFile(innerId));
                updateRequired = true;
            }
            else inputFile = new InputFile(photoId);

            SendPhoto message = new SendPhoto();
            message.setChatId(this.chatId);
            message.setPhoto(inputFile);
            Message result = telegramBot.execute(message);

            if (updateRequired) {
                converter.updateId(innerId, result.getPhoto().getFirst().getFileId());
            }
        } catch (TelegramApiException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void sendAnimationComponent(AnimationComponent component) {
        try {
            InputFile inputFile;
            String innerId = component.fileInnerId();
            Converter converter = telegramBot.getConverter();
            String animation_id = converter.convertId(innerId);

            Boolean updateRequired = false;
            if (animation_id == null) {
                inputFile = new InputFile(converter.getAsFile(innerId));
                updateRequired = true;
            }
            else inputFile = new InputFile(animation_id);

            SendAnimation message = new SendAnimation();
            message.setChatId(this.chatId);
            message.setAnimation(inputFile);
            Message result = telegramBot.execute(message);

            if (updateRequired) {
                converter.updateId(innerId, result.getAnimation().getFileId());
            }
        } catch (TelegramApiException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets context of using
     * @param chatId id of the chat to send user's response
     */
    public void setContext(long chatId) {
        this.chatId = chatId;
    }

    /**
     * clears all data from previous task
     */
    public void clean() {
        this.chatId = 0;
    }
}
