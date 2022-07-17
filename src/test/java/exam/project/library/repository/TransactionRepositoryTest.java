package exam.project.library.repository;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    @InjectMocks
    private TransactionRepository transactionRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        Member member = new Member()
                .setMemberId(1L)
                .setFirstName("Steve")
                .setLastName("Jobs")
                .setTelephone("9834757936");

        Book book = new Book()
                .setBookId(1L)
                .setTitle("The matrix")
                .setPrice(25.50)
                .setISBN("0-7696-1930-4");

        this.transaction = new Transaction()
                .setMemberId(1)
                .setBookId(1)
                .setTransactionId(1L)
                .setQuantity(1)
                .setDate(LocalDateTime.now())
                .setTotalPrice(30.00)
                .setMember(member)
                .setBook(book);
    }

    @Test
    void getAllTransaction() {
        // given
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        transactionList.add(transaction);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(transactionList);

        // when
        List<Transaction> transactions = transactionRepository.getAllTransaction();

        // then
        assertEquals(2, transactions.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getTransactionById() {
        // given
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(transactionList);

        // when
        List<Transaction> transactions = transactionRepository.getTransactionById(1L);

        // then
        assertEquals(1, transactions.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewTransaction() {
        // given
        given(jdbcTemplate.update(anyString(), anyInt(), anyInt(), anyInt())).willReturn(1);

        // when
        transactionRepository.saveNewTransaction(transaction);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyInt(), anyInt(), anyInt());
    }
}