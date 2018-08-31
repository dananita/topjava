package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


@RequestMapping(value = "/meals")
@Controller
public class JspMealController extends AbstractMealController{

    @Autowired
    public JspMealController(MealService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String meals(Model model) {
        model.addAttribute("meals", super.getAll());
        return "/meals";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request){
        super.delete(getId(request));
        return "redirect:/meals";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, Model model){
        model.addAttribute("meal",super.get(getId(request)));
        return "mealForm";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("meal",new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @PostMapping
    public String UpdateOrCreate(HttpServletRequest request){
        Meal meal= new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()){
            super.create(meal);
        } else {
            super.update(meal,getId(request));
        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

/*
    public class MealServlet extends HttpServlet {

        private MealRestController mealController;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            mealController = springContext.getBean(MealRestController.class);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if (action == null) {
                Meal meal = new Meal(
                        LocalDateTime.parse(request.getParameter("dateTime")),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("calories")));

                if (request.getParameter("id").isEmpty()) {
                    mealController.create(meal);
                } else {
                    mealController.update(meal, getId(request));
                }
                response.sendRedirect("meals");

            } else if ("filter".equals(action)) {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
                request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");

            switch (action == null ? "all" : action) {
                case "delete":
                    int id = getId(request);
                    mealController.delete(id);
                    response.sendRedirect("meals");
                    break;
                case "create":
                case "update":
                    final Meal meal = "create".equals(action) ?
                            new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                            mealController.get(getId(request));
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                    break;
                case "all":
                default:
                    request.setAttribute("meals", mealController.getAll());
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                    break;
            }
        }

        private int getId(HttpServletRequest request) {
            String paramId = Objects.requireNonNull(request.getParameter("id"));
            return Integer.parseInt(paramId);
        }
    }
*/
