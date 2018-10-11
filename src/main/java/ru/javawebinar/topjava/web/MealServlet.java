package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class MealServlet extends HttpServlet {

    private MealDao mealDao;

    public MealServlet() {
        this.mealDao = new MealDaoInMemory();
    }

    public static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("mealList",
                    MealsUtil.getFilteredWithExceeded(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
        else if(action.equalsIgnoreCase("delete")) {
            int mealId = Integer.valueOf(Objects.requireNonNull(request.getParameter("id")));
            mealDao.delete(mealId);
            response.sendRedirect("meals");
        }
        else {
            int mealId = Integer.parseInt(request.getParameter("id"));
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now(), "", 1000) :
                    mealDao.getById(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("datetime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        mealDao.save(meal);
        response.sendRedirect("meals");
    }
}
