package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

public class ContactPresenter {

    public void printContactList(List<Contact> contacts) {
        System.out.println("Daftar Kontak:");
        if (contacts.isEmpty()) {
            System.out.println("- Data kontak belum tersedia!");
        } else {
            for (Contact contact : contacts) {
                System.out.printf("%d | %s | %s | %s\n",
                    contact.getId(),
                    contact.getName(),
                    contact.getPhone(),
                    contact.getEmail());
            }
        }
    }

    public void printSortedList(List<Contact> contacts) {
        System.out.println("Daftar Kontak (Terurut):");
        if (contacts.isEmpty()) {
            System.out.println("- Data kontak belum tersedia!");
        } else {
            for (Contact contact : contacts) {
                System.out.printf("%d | %s | %s | %s\n",
                    contact.getId(),
                    contact.getName(),
                    contact.getPhone(),
                    contact.getEmail());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String keyword, List<Contact> contacts) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", keyword);
        if (contacts.isEmpty()) {
            System.out.println("- Kontak tidak ditemukan!");
        } else {
            for (Contact contact : contacts) {
                System.out.printf("%d | %s | %s | %s\n",
                    contact.getId(),
                    contact.getName(),
                    contact.getPhone(),
                    contact.getEmail());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Contact contact) {
        System.out.printf("Berhasil menambah kontak: %d | %s | %s | %s\n\n",
            contact.getId(),
            contact.getName(),
            contact.getPhone(),
            contact.getEmail());
    }
}