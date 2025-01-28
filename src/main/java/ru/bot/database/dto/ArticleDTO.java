package ru.bot.database.dto;

public class ArticleDTO {
    private int id;
    private String title;
    private String author;
    private String url;
    private String published_date;

    private int symbols_count;

    public ArticleDTO(int id, String title, String author, String url, String published_date, int symbols_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.published_date = published_date;
        this.symbols_count = symbols_count;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getPublished_date() {
        return published_date;
    }

    public int getSymbols_count() {
        return symbols_count;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", published_date='" + published_date + '\'' +
                ", symbols_count=" + symbols_count +
                '}';
    }
}
