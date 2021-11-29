package exam.project.library.service;

import exam.project.library.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransaction();

    List<Transaction> getTransactionById(Long transactionId);
}
