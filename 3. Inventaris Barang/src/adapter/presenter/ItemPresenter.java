package adapter.presenter;

import domain.entity.Item;
import java.util.List;

public class ItemPresenter {

    public void printItemList(List<Item> items) {
        System.out.println("Daftar Barang:");
        if (items.isEmpty()) {
            System.out.println("- Data barang belum tersedia!");
        } else {
            for (Item item : items) {
                System.out.printf("%d | %s | %d | %s\n",
                    item.getId(),
                    item.getName(),
                    item.getQuantity(),
                    item.getCategory());
            }
        }
    }

    public void printSortedList(List<Item> items) {
        System.out.println("Daftar Barang (Terurut):");
        if (items.isEmpty()) {
            System.out.println("- Data barang belum tersedia!");
        } else {
            for (Item item : items) {
                System.out.printf("%d | %s | %d | %s\n",
                    item.getId(),
                    item.getName(),
                    item.getQuantity(),
                    item.getCategory());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String keyword, List<Item> items) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", keyword);
        if (items.isEmpty()) {
            System.out.println("- Barang tidak ditemukan!");
        } else {
            for (Item item : items) {
                System.out.printf("%d | %s | %d | %s\n",
                    item.getId(),
                    item.getName(),
                    item.getQuantity(),
                    item.getCategory());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Item item) {
        System.out.printf("Berhasil menambah barang: %d | %s | %d | %s\n\n",
            item.getId(),
            item.getName(),
            item.getQuantity(),
            item.getCategory());
    }
}