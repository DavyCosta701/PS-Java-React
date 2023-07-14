package br.com.banco.exception;

import br.com.banco.APIErrors;

public final class ExceptionUtil {

    public static APIErrors lidarException(Exception exception) {
        String message = exception.getMessage();
        return new APIErrors(message);
    }

}
