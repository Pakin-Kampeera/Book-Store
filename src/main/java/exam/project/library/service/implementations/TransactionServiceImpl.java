package exam.project.library.service.implementations;

import exam.project.library.model.Transaction;
import exam.project.library.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<Transaction> getAllTransaction() {
        return null;
    }

    @Override
    public List<Transaction> getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public void saveNewTransaction(Transaction transaction) {

    }
}