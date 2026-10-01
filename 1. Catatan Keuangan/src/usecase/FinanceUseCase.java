package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction addIncome(String description, long amount) {
        return repository.save(description, amount, TransactionType.INCOME);
    }

    public Transaction addExpense(String description, long amount) {
        return repository.save(description, amount, TransactionType.EXPENSE);
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public List<Transaction> getSortedTransactions(SortOption sortOption) {
        List<Transaction> transactions = repository.findAll();
        transactions.sort(sortOption.getComparator());
        return transactions;
    }

    public List<Transaction> searchByDescription(String keyword) {
        return repository.findAll().stream()
            .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteTransaction(int id) {
        return repository.delete(id);
    }

    public long getBalance() {
        return repository.findAll().stream()
            .mapToLong(t -> t.getType() == TransactionType.INCOME ? t.getAmount() : -t.getAmount())
            .sum();
    }
}