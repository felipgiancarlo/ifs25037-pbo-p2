package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

public class GuestPresenter {
    
    public void printMainList(List<Guest> guests) {
        System.out.println("Daftar Tamu:");
        if (guests.isEmpty()) {
            System.out.println("- Data tamu belum tersedia!");
        } else {
            for (Guest g : guests) {
                System.out.println(g.getId() + " | " + g.getName() + " | " + g.getPurpose());
            }
        }
    }

    public void printSearchResult(String keyword, List<Guest> guests) {
        System.out.println("Hasil Pencarian: \"" + keyword + "\"");
        if (guests.isEmpty()) {
            System.out.println("- Tamu tidak ditemukan!");
        } else {
            for (Guest g : guests) {
                System.out.println(g.getId() + " | " + g.getName() + " | " + g.getPurpose());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Guest g) {
        System.out.println("Berhasil mendaftarkan tamu: " + g.getId() + " | " + g.getName() + " | " + g.getPurpose());
        System.out.println();
    }
}