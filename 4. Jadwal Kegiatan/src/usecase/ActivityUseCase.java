package usecase;

import domain.entity.Activity;
import domain.entity.ActivitySortOption;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityUseCase {
    private final IActivityRepository repository;

    public ActivityUseCase(IActivityRepository repository) {
        this.repository = repository;
    }

    public Activity addActivity(String title, String day, String time) {
        return repository.save(title, day, time);
    }

    public boolean updateActivity(int id, String title, String day, String time) {
        return repository.update(id, title, day, time);
    }

    public Optional<Activity> getActivityById(int id) {
        return repository.findById(id);
    }

    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    public List<Activity> getSortedActivities(ActivitySortOption sortOption) {
        List<Activity> activities = repository.findAll();
        activities.sort(sortOption.getComparator());
        return activities;
    }

    public List<Activity> searchByTitle(String keyword) {
        return repository.findAll().stream()
            .filter(activity -> activity.getTitle().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteActivity(int id) {
        return repository.delete(id);
    }
}