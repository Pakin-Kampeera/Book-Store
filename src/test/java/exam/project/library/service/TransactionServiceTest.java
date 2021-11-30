package exam.project.library.service;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import exam.project.library.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TransactionServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    TransactionRepository transactionRepository;

    Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transactionRepository = new TransactionRepository(jdbcTemplate);

        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Steve");
        member.setLastName("Jobs");
        member.setTelephone("9834757936");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("The matrix");
        book.setPrice(25.50);
        book.setISBN("0-7696-1930-4");

        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setQuantity(1);
        transaction.setDate(LocalDateTime.now());
        transaction.setTotalPrice(30.00);
        transaction.setMember(member);
        transaction.setBook(book);
    }

    @Test
    void getAllTransaction() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        transactionList.add(transaction);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(transactionList);

        List<Transaction> transactions = transactionRepository.getAllTransaction();
        assertEquals(2, transactions.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getTransactionById() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(transactionList);

        List<Transaction> transactions = transactionRepository.getTransactionById(1L);
        assertEquals(1, transactions.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }
}