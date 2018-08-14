package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal Meal, int userId) {
        if (!Meal.isNew() && get(Meal.getId(), userId) == null) {
            return null;
        }
        Meal.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(Meal);
    }

    @Override
    public boolean delete(int id, int userId) {

        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {

        return crudRepository.getOne(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {

        return crudRepository.getAllByUser_id(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return crudRepository.getBeetween(startDate, endDate, userId);
    }
}
