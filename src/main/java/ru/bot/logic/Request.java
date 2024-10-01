/**
 *  User request wrapper
 */

package ru.bot.logic;

public class Request {
    private final String message;

    /**
     * Constructor
     * @param message contains user request message
     */
    public Request(String message) {
        this.message = message;
    }

    /**
     * @return user request message
     */
    public String getMessage() {
        return message;
    }
}

