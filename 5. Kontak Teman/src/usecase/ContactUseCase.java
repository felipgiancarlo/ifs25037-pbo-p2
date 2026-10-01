package usecase;

import domain.entity.Contact;
import domain.entity.ContactSortOption;
import domain.repository.IContactRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContactUseCase {
    private final IContactRepository repository;

    public ContactUseCase(IContactRepository repository) {
        this.repository = repository;
    }

    public Contact addContact(String name, String phone, String email) {
        return repository.save(name, phone, email);
    }

    public boolean updateContact(int id, String name, String phone, String email) {
        return repository.update(id, name, phone, email);
    }

    public Optional<Contact> getContactById(int id) {
        return repository.findById(id);
    }

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public List<Contact> getSortedContacts(ContactSortOption sortOption) {
        List<Contact> contacts = repository.findAll();
        contacts.sort(sortOption.getComparator());
        return contacts;
    }

    public List<Contact> searchByName(String keyword) {
        return repository.findAll().stream()
            .filter(contact -> contact.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteContact(int id) {
        return repository.delete(id);
    }
}