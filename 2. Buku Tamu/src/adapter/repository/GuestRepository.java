package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuestRepository implements IGuestRepository {
    private final List<Guest> guests = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Guest save(String name, String purpose) {
        Guest guest = new Guest(nextId++, name, purpose);
        guests.add(guest);
        return guest;
    }

    @Override
    public List<Guest> findAll() {
        return new ArrayList<>(guests);
    }

    @Override
    public Optional<Guest> findById(int id) {
        return guests.stream().filter(g -> g.getId() == id).findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Guest> guest = findById(id);
        if (guest.isPresent()) {
            guests.remove(guest.get());
            return true;
        }
        return false;
    }
}