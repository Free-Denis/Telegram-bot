package ru.bot.storage;

public interface ConverterStorage {
    String getById(String innerId);

    void updateById(String innerId, String newValue);
}
