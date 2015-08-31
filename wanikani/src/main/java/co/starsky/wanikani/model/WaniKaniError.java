package co.starsky.wanikani.model;

import java.io.Serializable;

/**
 * @author alliecurry
 */
public final class WaniKaniError implements Serializable {
    private static final long serialVersionUID = 1470935440327064501L;

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    protected String getLogOutput() {
        return String.format("WaniKani Error | Code: %s | Message: %s", code, message);
    }
}
