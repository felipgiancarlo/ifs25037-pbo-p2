package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemRepository implements IItemRepository {
    private final List<Item> items = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Item save(String name, int quantity, String category) {
        Item item = new Item(nextId++, name, quantity, category);
        items.add(item);
        return item;
    }

    @Override
    public boolean updateQuantity(int id, int newQuantity) {
        Optional<Item> optItem = findById(id);
        if (optItem.isPresent()) {
            Item oldItem = optItem.get();
            int index = items.indexOf(oldItem);
            Item updatedItem = new Item(oldItem.getId(), oldItem.getName(), newQuantity, oldItem.getCategory());
            items.set(index, updatedItem);
            return true;
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public Optional<Item> findById(int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public boolean delete(int id) {
        Optional<Item> item = findById(id);
        if (item.isPresent()) {
            items.remove(item.get());
            return true;
        }
        return false;
    }
}