package domain.repository;

import domain.entity.Activity;
import java.util.List;
import java.util.Optional;

public interface IActivityRepository {
    Activity save(String title, String day, String time);
    boolean update(int id, String title, String day, String time);
    List<Activity> findAll();
    Optional<Activity> findById(int id);
    boolean delete(int id);
}