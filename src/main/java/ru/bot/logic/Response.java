/**
 *  User's response wrapper
 */

package ru.bot.logic;

import ru.bot.logic.components.Component;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();

    public void setComponent(Component comp) {
        components.put(comp.getClass(), comp);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return (T) components.get(componentClass);
    }
}

