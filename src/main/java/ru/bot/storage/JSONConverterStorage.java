package ru.bot.storage;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class JSONConverterStorage implements ConverterStorage {
    private final HashMap<String, String> ids = new HashMap<>();
    private final String path;

    public JSONConverterStorage(String path, String[] requiredKeys) {
        this.path = path;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String data = new String(classLoader.getResourceAsStream(path).readAllBytes());

            JSONObject obj = new JSONObject(data);
            for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
                String key = it.next();
                ids.put(key, obj.getString(key));
            }

            // Проверка на существование всех обязательных картинок
            for (String command: requiredKeys) {
                if (!ids.containsKey(command)) {
                    throw new IllegalStateException("В %s не хватает %s".formatted(path, command));
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public String getById(String innerId) {
        return ids.get(innerId);
    }

    @Override
    public void updateById(String innerId, String newValue) {
        ids.put(innerId, newValue);

        JSONObject jsonObject = new JSONObject(ids);
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            FileWriter file = new FileWriter(classLoader.getResource(path).getFile());
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
