package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int ID = START_SEQ;

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100002,LocalDateTime.of(2017, Month.JULY, 25, 0, 0), "Lunch", 510),
            new Meal(100003,LocalDateTime.of(2017, Month.JULY, 25, 0, 0), "lunchtime", 675),
            new Meal(100004,LocalDateTime.of(2017, Month.JULY, 25, 0, 0), "dinner", 700),
            new Meal(100005,LocalDateTime.of(2018, Month.JULY, 30, 10, 0), "Завтрак", 500),
            new Meal(100006,LocalDateTime.of(2018, Month.JULY, 30, 13, 0), "Обед", 1000),
            new Meal(100007,LocalDateTime.of(2018, Month.JULY, 30, 20, 0), "Ужин", 500),
            new Meal(100008,LocalDateTime.of(2018, Month.JULY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100009,LocalDateTime.of(2018, Month.JULY, 31, 13, 0), "Обед", 500),
            new Meal(100010,LocalDateTime.of(2018, Month.JULY, 31, 20, 0), "Ужин", 510)
            );

}