package br.com.lucashilles.keystone.utils;

/**
 * @author Eduardo Folly
 */
public class ApplicationError {

    private final String message;
    private final int status;

    public ApplicationError(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
