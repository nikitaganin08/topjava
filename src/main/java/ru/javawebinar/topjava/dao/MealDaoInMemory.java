package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealDaoInMemory implements MealDao {
    private List<Meal> meals;

    public MealDaoInMemory() {
        meals = new CopyOnWriteArrayList<>();
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        add(new Meal(LocalDateTime.of(2015, Month.JUNE, 29, 20, 0), "Ужин", 900));
    }

    @Override
    public void add(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getbyId(int id) {
        return null;
    }
}
