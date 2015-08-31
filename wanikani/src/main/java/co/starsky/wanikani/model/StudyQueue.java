package co.starsky.wanikani.model;

import java.io.Serializable;

/**
 * @author alliecurry
 */
public final class StudyQueue implements Serializable {
    private static final long serialVersionUID = 7255531263483603546L;

    private int lessonsAvailable;
    private int reviewsAvailable;
    private int reviewsAvailableNextHour;
    private int reviewsAvailableNextDay;
    private Long nextReviewDate;

    public int getLessonsAvailable() {
        return lessonsAvailable;
    }

    public int getReviewsAvailable() {
        return reviewsAvailable;
    }

    public int getReviewsAvailableNextHour() {
        return reviewsAvailableNextHour;
    }

    public int getReviewsAvailableNextDay() {
        return reviewsAvailableNextDay;
    }

    public Long getNextReviewDate() {
        return nextReviewDate;
    }
}
