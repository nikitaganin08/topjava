package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemory implements MealDao {
    public static final Logger log = LoggerFactory.getLogger(MealDaoInMemory.class);

    private AtomicInteger count;
    private Map<Integer, Meal> meals;

    public MealDaoInMemory() {
        meals = new ConcurrentHashMap<>();
        count = new AtomicInteger(0);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 29, 20, 0), "Ужин", 900));
    }


    @Override
    public void delete(int id) {
        if(meals.containsKey(id)) {
            meals.remove(id);
        }
        log.info("Meal deleted successfully, Meal Id=" + id);
    }

    @Override
    public Meal save(Meal meal) {

        if(meal.getId() == null) {
            meal.setId(count.incrementAndGet());
            meals.put(meal.getId(), meal);
            log.info("Meal saved successfully, Meal Details=" + meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getById(int id) {
        return meals.getOrDefault(meals.get(id), null);
    }
}
