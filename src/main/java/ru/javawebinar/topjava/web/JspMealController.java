package ru.javawebinar.topjava.web;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.AbstractMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.*;


@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealRestController {

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String meals(Model model){
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/update/{id}")
    public String updateMeal(Model model, @PathVariable int id){
        Meal meal = get(id);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/create")
    public String createMeal(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable int id){
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping("/filter")
    public String filterMeals(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals",getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @PostMapping
    public String setMeal(HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if(request.getParameter("id").isEmpty()) {
            create(meal);
        }
        else {
            update(meal, Integer.parseInt(request.getParameter("id")));
        }
        return "redirect:/meals";
    }
}
