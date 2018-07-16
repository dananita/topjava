package ru.javawebinar.topjava.web.meal;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        return service.create(meal, userId);
    }


    public void delete(int id) {
        log.debug("delete {} for User {} ");
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
    }

    public Meal get(int id) {
        log.debug("get {} for User {} ");
        int userId = SecurityUtil.authUserId();
        return service.get(id, userId);
    }

    public Collection<MealWithExceed> getAll() {
        int userId = SecurityUtil.authUserId();
        int userCaloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
        return MealsUtil.getWithExceeded(service.getAll(userId),userCaloriesPerDay);
    }
   /*
    public void update(Meal meal,int userId) {
        service.save(meal, userId);
    }*/

}