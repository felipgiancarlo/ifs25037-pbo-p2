package domain.repository;

import domain.entity.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactRepository {
    Contact save(String name, String phone, String email);
    boolean update(int id, String name, String phone, String email);
    List<Contact> findAll();
    Optional<Contact> findById(int id);
    boolean delete(int id);
}