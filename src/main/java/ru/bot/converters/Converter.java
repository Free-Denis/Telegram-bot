package ru.bot.converters;

import java.io.File;

public interface Converter {
    String convertId(String innerId);

    void updateId(String innerId, String newId);

    File getAsFile(String innerId);
}
