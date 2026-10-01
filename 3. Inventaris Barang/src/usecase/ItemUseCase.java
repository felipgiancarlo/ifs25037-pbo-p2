package usecase;

import domain.entity.Item;
import domain.entity.ItemSortOption;
import domain.repository.IItemRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemUseCase {
    private final IItemRepository repository;

    public ItemUseCase(IItemRepository repository) {
        this.repository = repository;
    }

    public Item addItem(String name, int quantity, String category) {
        return repository.save(name, quantity, category);
    }

    public boolean updateStock(int id, int newQuantity) {
        return repository.updateQuantity(id, newQuantity);
    }

    public Optional<Item> getItemById(int id) {
        return repository.findById(id);
    }

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public List<Item> getSortedItems(ItemSortOption sortOption) {
        List<Item> items = repository.findAll();
        items.sort(sortOption.getComparator());
        return items;
    }

    public List<Item> searchByName(String keyword) {
        return repository.findAll().stream()
            .filter(item -> item.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteItem(int id) {
        return repository.delete(id);
    }
}