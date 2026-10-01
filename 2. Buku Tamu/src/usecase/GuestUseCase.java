package usecase;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.List;
import java.util.stream.Collectors;

public class GuestUseCase {
    private final IGuestRepository repository;

    public GuestUseCase(IGuestRepository repository) {
        this.repository = repository;
    }

    public Guest registerGuest(String name, String purpose) {
        return repository.save(name, purpose);
    }

    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

    public List<Guest> searchByName(String keyword) {
        return repository.findAll().stream()
            .filter(g -> g.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteGuest(int id) {
        return repository.delete(id);
    }
}