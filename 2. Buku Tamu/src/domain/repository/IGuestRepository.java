package domain.repository;

import domain.entity.Guest;
import java.util.List;
import java.util.Optional;

public interface IGuestRepository {
    Guest save(String name, String purpose);
    List<Guest> findAll();
    Optional<Guest> findById(int id);
    boolean delete(int id);
}