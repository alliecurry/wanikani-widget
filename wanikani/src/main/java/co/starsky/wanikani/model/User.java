package co.starsky.wanikani.model;

import java.io.Serializable;

/**
 * Represents WaniKani login info for a single account.
 * @author alliecurry
 */
public final class User implements Serializable {
    private static final long serialVersionUID = -7102422881478711640L;

    private String username;
    private String gravatar;
    private int level;
    private String title;
    private String about;
    private String website;
    private String twitter;
    private int topicsCount;
    private int postsCount;
    private long creationDate;
    private Long vacationDate;

    public String getUsername() {
        return username;
    }

    public String getGravatar() {
        return gravatar;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }

    public String getWebsite() {
        return website;
    }

    public String getTwitter() {
        return twitter;
    }

    public int getTopicsCount() {
        return topicsCount;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public Long getVacationDate() {
        return vacationDate;
    }
}
