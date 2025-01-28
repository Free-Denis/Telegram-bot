package ru.bot.converters;

import ru.bot.storage.ConverterStorage;

import java.io.File;

public class ConverterAssets implements Converter {
    private final ConverterStorage mainStorage;
    private final ConverterStorage secondStorage;
    private final String assetsPrefix;

    public ConverterAssets(ConverterStorage mainStorage, ConverterStorage secondStorage, String assetsPrefix) {
        this.mainStorage = mainStorage;
        this.secondStorage = secondStorage;
        this.assetsPrefix = assetsPrefix;
    }

    @Override
    public String convertId(String innerId) {
        return mainStorage.getById(innerId);
    }

    @Override
    public void updateId(String innerId, String newId) {
        mainStorage.updateById(innerId, newId);
    }

    public File getAsFile(String inner_id) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(assetsPrefix + secondStorage.getById(inner_id)).getFile());
    }
}
