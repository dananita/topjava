package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    {
        MealsUtil.MEALS.forEach(this::save);
    }

    public static void main(String[] args) { //test
        InMemoryMealRepositoryImpl repository2 = new InMemoryMealRepositoryImpl();
        System.out.println(repository2.getAll(1));
        System.out.println(repository2.get(3,1));
        System.out.println(repository2.delete(20,1));
        System.out.println(repository2.delete(2,1));
       // repository2.save(new User(null, "userName3", "email1@mail.ru", "password", Role.ROLE_USER));
        System.out.println(repository2.getAll(1));
    }
    private void save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
        } else {
            // treat case: update, but absent in storage
            repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        Meal result=null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            result=meal;
        }
        if (meal.getUserId()==userId) {
            // treat case: update, but absent in storage
            result = repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return result;
    }

    @Override
    public boolean delete(int id,int userId) {
        log.info("delete {}", id);
        if(repository.get(id).getUserId()==userId) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id,int userId) {
        log.info("get {}", id);
        if(repository.get(id).getUserId()==userId) {
            return repository.get(id);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream()
                .filter(m->m.getUserId()==userId)
                .sorted().collect(Collectors.toCollection(LinkedList::new));
    }

}

