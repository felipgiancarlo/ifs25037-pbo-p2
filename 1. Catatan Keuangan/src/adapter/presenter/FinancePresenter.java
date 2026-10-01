package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class FinancePresenter {
    
    public void printMainList(List<Transaction> transactions, long balance) {
        System.out.println("Daftar Transaksi:");
        if (transactions.isEmpty()) {
            System.out.println("- Belum ada transaksi!");
        } else {
            for (Transaction t : transactions) {
                System.out.printf("%d | %s | Rp %d | %s\n", 
                    t.getId(), 
                    t.getDescription(), 
                    t.getAmount(), 
                    (t.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran"));
            }
        }
        System.out.printf("Saldo: Rp %d\n", balance);
    }

    public void printSortedList(List<Transaction> transactions) {
        System.out.println("Daftar Transaksi (Terurut):");
        if (transactions.isEmpty()) {
            System.out.println("- Belum ada transaksi!");
        } else {
            for (Transaction t : transactions) {
                System.out.printf("%d | %s | Rp %d | %s\n", 
                    t.getId(), 
                    t.getDescription(), 
                    t.getAmount(), 
                    (t.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran"));
            }
        }
        System.out.println();
    }

    public void printSearchResult(String keyword, List<Transaction> transactions) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", keyword);
        if (transactions.isEmpty()) {
            // PERBAIKAN: Menambahkan "- " di awal kalimat sesuai Expect Output
            System.out.println("- Transaksi tidak ditemukan!"); 
        } else {
            for (Transaction t : transactions) {
                System.out.printf("%d | %s | Rp %d | %s\n", 
                    t.getId(), 
                    t.getDescription(), 
                    t.getAmount(), 
                    (t.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran"));
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Transaction t) {
        System.out.printf("Berhasil menambah transaksi: %d | %s | Rp %d | %s\n\n",
            t.getId(),
            t.getDescription(),
            t.getAmount(),
            (t.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran"));
    }

    public void printBalance(long balance) {
        System.out.printf("Saldo saat ini: Rp %d\n\n", balance);
    }
}