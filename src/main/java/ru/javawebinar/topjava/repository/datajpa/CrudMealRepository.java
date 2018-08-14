package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Override
    @Transactional
    Meal save(Meal meal);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int user_id);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    Meal getOne(@Param("id") int id, int user_id);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id")
    List<Meal> getAllByUser_id(int userId);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id =: user_Id AND m.dateTime>= :startDate AND m.dateTime<= :endDate")
    List<Meal> getBeetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
