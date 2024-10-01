/**
 *  User's response wrapper
 */

package ru.bot.logic;

public class Response {
    private final String message;

    /**
     * @param message contains response for user
     */
    public Response(String message) {
        this.message = message;
    }

    /**
     * @return response text for user
     */
    public String getMessage() {
        return message;
    }
}

