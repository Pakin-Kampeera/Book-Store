package exam.project.library.service.implementations;

import exam.project.library.model.Transaction;
import exam.project.library.repository.TransactionRepository;
import exam.project.library.service.TransactionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Cacheable(value = "transactions")
    public List<Transaction> getAllTransaction() {
        return transactionRepository.getAllTransaction();
    }

    @Override
    @Cacheable(value = "transaction", key = "#transactionId")
    public List<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.getTransactionById(transactionId);
    }
}