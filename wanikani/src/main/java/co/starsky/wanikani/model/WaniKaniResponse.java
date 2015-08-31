package co.starsky.wanikani.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author alliecurry
 */
public final class WaniKaniResponse<T> implements Serializable {
    private static final long serialVersionUID = -52167005595042619L;

    @SerializedName("requested_information") private T requestedInfo;
    @SerializedName("user_information") private User userInfo;
    private WaniKaniError error;

    public boolean isError() {
        return error != null;
    }

    public WaniKaniError getError() {
        return error;
    }

    public T getRequestedInfo() {
        return requestedInfo;
    }

    public User getUserInfo() {
        return userInfo;
    }
}
