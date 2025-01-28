package ru.bot.logic.handlers;

import ru.bot.database.ArticleDAOImpl;
import ru.bot.database.dto.ArticleDTO;
import ru.bot.logic.OutputWriter;
import ru.bot.logic.Request;
import ru.bot.logic.Response;
import ru.bot.logic.components.AnimationComponent;
import ru.bot.logic.components.PhotoComponent;
import ru.bot.logic.components.TextComponent;
import ru.bot.random.RandomImageGenerator;

public class RequestHangerMain implements RequestHanger {
//    private static final String START_COMMAND = "/start";
//    private static final String TEST_COMMAND = "/test";

    @Override
    public void handle(Request request, OutputWriter writer) {
        TextComponent message = request.getComponent(TextComponent.class);
        if (message != null) {
            Response response = switch (message.message()) {
                case "/start" -> hello();
                case "/test" -> testMessage();
                case "/random" -> randomImage();
                default -> defaultMessage();
            };
            writer.write(response);
        }
    }

    private Response hello() {
        String helloMessage = """
                Привет, меня зовут Бёрнис!
                Я могу помочь тебе с поиском текста для тысяч. Только попроси, мы с тобой что-нибудь поищем!
                """;
        Response response = new Response();
        response.setComponent(new TextComponent(helloMessage));
        response.setComponent(new AnimationComponent("/start"));
        return response;
    }

    private Response defaultMessage() {
        Response response = new Response();
        response.setComponent(new TextComponent("Что за команда такая, не знаю такую!"));

        return response;
    }

    private Response randomImage() {
        Response response = new Response();
        response.setComponent(new TextComponent("Сегодня ты выглядишь как: "));

        String imageId = RandomImageGenerator.INSTANCE.getRandomImage();
        response.setComponent(new PhotoComponent(imageId));

        return response;
    }

    private Response testMessage() {
        ArticleDAOImpl article_db = new ArticleDAOImpl();
        ArticleDTO article = article_db.get(1);
        String message = """
               Нашла для тебя такую статью!
               Название: %s
               Автор: %s
               Кол-во символов: %s
               ссылка: %s
               """.formatted(article.getTitle(), article.getAuthor(), article.getSymbols_count(), article.getUrl());
        Response response = new Response();
        response.setComponent(new TextComponent(message));
        return response;
    }
}
