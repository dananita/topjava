package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;


import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring-app_test.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "spring/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2018,07,25,16,00),"myLunch",510);
        Meal createdMeal=mealService.create(newMeal,100001);
        newMeal.setId(createdMeal.getId());
        
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }
}