package ua.mycompany.exception;

import org.apache.log4j.Logger;

public class AbstractLoggerRuntimeException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(AbstractLoggerRuntimeException.class);
    public AbstractLoggerRuntimeException(String message) {
        System.out.println(message);
        logger.error(message);
    }
}
