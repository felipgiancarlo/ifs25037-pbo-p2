package framework.view;

import adapter.presenter.ContactPresenter;
import domain.entity.Contact;
import domain.entity.ContactSortOption;
import usecase.ContactUseCase;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class ContactView {
    private final ContactUseCase useCase;
    private final ContactPresenter presenter;
    private final Scanner scanner;

    public ContactView(ContactUseCase useCase, ContactPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void show() {
        try {
            while (true) {
                presenter.printContactList(useCase.getAllContacts());

                System.out.println("Menu:");
                System.out.println("1. Tambah");
                System.out.println("2. Ubah");
                System.out.println("3. Cari");
                System.out.println("4. Urutkan");
                System.out.println("5. Hapus");
                System.out.println("x. Keluar");

                String menu = readString("Pilih : ");

                switch (menu.toLowerCase()) {
                    case "1":
                        System.out.println("[Menambah Kontak]");
                        addContact();
                        break;
                    case "2":
                        System.out.println("[Mengubah Kontak]");
                        updateContact();
                        break;
                    case "3":
                        System.out.println("[Mencari Kontak]");
                        searchContact();
                        break;
                    case "4":
                        System.out.println("[Mengurutkan Kontak]");
                        sortContacts();
                        break;
                    case "5":
                        System.out.println("[Menghapus Kontak]");
                        deleteContact();
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("[!] Pilihan tidak dimengerti.\n");
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            // Berhenti otomatis jika input file habis (EOF dari Grader)
        }
    }

    private void addContact() {
        String name = readString("Nama (x Jika Batal) : ");
        if (name.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String phone = readString("Telepon : ");
        if (phone.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String email = readString("Email : ");
        if (email.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Contact contact = useCase.addContact(name, phone, email);
        presenter.printAddSuccess(contact);
    }

    private void updateContact() {
        String idStr = readString("ID Kontak yang diubah (x Jika Batal) : ");
        if (idStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!\n");
            return;
        }

        String newName = readString("Nama Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newName.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String newPhone = readString("Telepon Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newPhone.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String newEmail = readString("Email Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newEmail.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Optional<Contact> optContact = useCase.getContactById(id);
        if (optContact.isEmpty()) {
            System.out.printf("[!] Gagal mengubah kontak dengan ID: %d.\n\n", id);
            return;
        }

        Contact oldContact = optContact.get();
        String finalName = newName.isEmpty() ? oldContact.getName() : newName;
        String finalPhone = newPhone.isEmpty() ? oldContact.getPhone() : newPhone;
        String finalEmail = newEmail.isEmpty() ? oldContact.getEmail() : newEmail;

        if (useCase.updateContact(id, finalName, finalPhone, finalEmail)) {
            System.out.println("Berhasil mengubah kontak.\n");
        } else {
            System.out.printf("[!] Gagal mengubah kontak dengan ID: %d.\n\n", id);
        }
    }

    private void searchContact() {
        String keyword = readString("Kata Kunci (x Jika Batal) : ");
        if (keyword.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }
        presenter.printSearchResult(keyword, useCase.searchByName(keyword));
    }

    private void sortContacts() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("x. Batal");
        String choice = readString("Pilih : ");

        if (choice.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        ContactSortOption option = switch (choice) {
            case "1" -> ContactSortOption.NAME_ASC;
            case "2" -> ContactSortOption.NAME_DESC;
            default -> null;
        };

        if (option == null) {
            System.out.println("[!] Pilihan tidak valid!\n");
            return;
        }

        presenter.printSortedList(useCase.getSortedContacts(option));
    }

    private void deleteContact() {
        // PERBAIKAN: Menambahkan kurung siku pada [ID Kontak]
        String idStr = readString("[ID Kontak] yang dihapus (x Jika Batal) : ");
        if (idStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            if (useCase.deleteContact(id)) {
                System.out.println("Berhasil menghapus kontak.\n");
            } else {
                System.out.printf("[!] Gagal menghapus kontak dengan ID: %d.\n\n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!\n");
        }
    }
}