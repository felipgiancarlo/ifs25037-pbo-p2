package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository implements IActivityRepository {
    private final List<Activity> activities = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Activity save(String title, String day, String time) {
        Activity activity = new Activity(nextId++, title, day, time);
        activities.add(activity);
        return activity;
    }

    @Override
    public boolean update(int id, String title, String day, String time) {
        Optional<Activity> optActivity = findById(id);
        if (optActivity.isPresent()) {
            Activity oldActivity = optActivity.get();
            int index = activities.indexOf(oldActivity);
            Activity updatedActivity = new Activity(oldActivity.getId(), title, day, time);
            activities.set(index, updatedActivity);
            return true;
        }
        return false;
    }

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(activities);
    }

    @Override
    public Optional<Activity> findById(int id) {
        return activities.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Activity> activity = findById(id);
        if (activity.isPresent()) {
            activities.remove(activity.get());
            return true;
        }
        return false;
    }
}