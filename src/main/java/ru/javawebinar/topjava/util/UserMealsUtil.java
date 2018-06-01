package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceedList = UserMealsUtil.getFilteredWithExceeded(
                mealList, LocalTime.of(7, 0),
                LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed mealWithExceed : userMealWithExceedList) {
            String stringExceed = mealWithExceed.isExceed() ? "не превышает сут. норму" : "превышает сут. норму";
            System.out.println(String.join(",", mealWithExceed.getDescription(), mealWithExceed.getDateTime().toString(), stringExceed));
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> countMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            countMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (a, b) -> a + b);
        }

        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                if (countMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay) {
                    userMealWithExceeds.add(new UserMealWithExceed(
                            meal.getDateTime(), meal.getDescription(),
                            meal.getCalories(), false));
                } else {
                    userMealWithExceeds.add(new UserMealWithExceed(
                            meal.getDateTime(), meal.getDescription(),
                            meal.getCalories(), true));
                }
            }
        }
        return userMealWithExceeds;
    }
}
