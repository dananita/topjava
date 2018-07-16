package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

public class Meal extends AbstractBaseEntity implements Comparable<Meal> {

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private int userId;

    public Meal(LocalDateTime dateTime, String description, int calories,int userId) {
        this(null, dateTime, description, calories,userId);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories,int userId) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }


    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    @Override
    public int compareTo(Meal m) {
        return m.getDate().compareTo(this.getDate()) == 0 ?
               m.getTime().compareTo(this.getTime()) :
               m.getDate().compareTo(this.getDate());
    }
}
