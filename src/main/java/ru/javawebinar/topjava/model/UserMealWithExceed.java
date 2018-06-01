package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private final LocalDateTime dateTime;

    public String getDescription() {
        return description;
    }

    private final String description;

    private final int calories;

    public boolean isExceed() {
        return exceed;
    }

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
}
