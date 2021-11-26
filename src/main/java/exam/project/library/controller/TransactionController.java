package exam.project.library.controller;

import exam.project.library.model.Transaction;
import exam.project.library.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/transaction")
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public ResponseEntity<Transaction> getAllTransaction() {
        return new ResponseEntity(transactionService.getAllTransaction(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity getTransactionById(@PathVariable("transactionId") Long transactionId) {
        return new ResponseEntity(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }
}
