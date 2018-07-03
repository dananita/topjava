package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "mealServlet",urlPatterns = "/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<Meal> list= MealsUtil.init();
        List<MealWithExceed> mealsList = MealsUtil.getFilteredWithExceeded(list, LocalTime.MIN,LocalTime.MAX, 2000);
        request.setAttribute("meals", mealsList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
