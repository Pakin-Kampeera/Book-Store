package exam.project.library.repository;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TransactionRepositoryTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    TransactionRepository transactionRepository;

    Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transactionRepository = new TransactionRepository(jdbcTemplate);

        Member member = new Member();
        member.setMemberId(1L);
        member.setFirstName("Steve");
        member.setLastName("Jobs");
        member.setTelephone("9834757936");

        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("The matrix");
        book.setPrice(25.50);
        book.setISBN("0-7696-1930-4");

        transaction = new Transaction();
        transaction.setMemberId(1);
        transaction.setBookId(1);
        transaction.setTransactionId(1L);
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

    @Test
    void saveNewTransaction() {
        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(1);
        transactionRepository.saveNewTransaction(transaction);
        verify(jdbcTemplate, times(1)).update(anyString(), anyInt(), anyInt(), anyInt());
    }
}