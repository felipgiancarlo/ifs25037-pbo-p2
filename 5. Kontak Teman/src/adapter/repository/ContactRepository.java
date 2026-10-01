package adapter.repository;

import domain.entity.Contact;
import domain.repository.IContactRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository implements IContactRepository {
    private final List<Contact> contacts = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Contact save(String name, String phone, String email) {
        Contact contact = new Contact(nextId++, name, phone, email);
        contacts.add(contact);
        return contact;
    }

    @Override
    public boolean update(int id, String name, String phone, String email) {
        Optional<Contact> optContact = findById(id);
        if (optContact.isPresent()) {
            Contact oldContact = optContact.get();
            int index = contacts.indexOf(oldContact);
            Contact updatedContact = new Contact(oldContact.getId(), name, phone, email);
            contacts.set(index, updatedContact);
            return true;
        }
        return false;
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts);
    }

    @Override
    public Optional<Contact> findById(int id) {
        return contacts.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Contact> contact = findById(id);
        if (contact.isPresent()) {
            contacts.remove(contact.get());
            return true;
        }
        return false;
    }
}