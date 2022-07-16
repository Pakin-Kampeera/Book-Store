//package exam.project.library.service;
//
//import exam.project.library.model.Book;
//import exam.project.library.model.Member;
//import exam.project.library.model.Transaction;
//import exam.project.library.repository.TransactionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.ResultSetExtractor;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//class TransactionServiceTest {
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//    TransactionRepository transactionRepository;
//
//    Transaction transaction;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.transactionRepository = new TransactionRepository(jdbcTemplate);
//
//        Member member = new Member()
//                .setMemberId(1L)
//                .setFirstName("Steve")
//                .setLastName("Jobs")
//                .setTelephone("9834757936");
//
//        Book book = new Book()
//                .setBookId(1L)
//                .setTitle("The matrix")
//                .setPrice(25.50)
//                .setISBN("0-7696-1930-4");
//
//        transaction = new Transaction()
//                .setMemberId(1)
//                .setBookId(1)
//                .setTransactionId(1L)
//                .setQuantity(1)
//                .setDate(LocalDateTime.now())
//                .setTotalPrice(30.00)
//                .setMember(member)
//                .setBook(book);
//    }
//
//    @Test
//    void getAllTransaction() {
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(transaction);
//        transactionList.add(transaction);
//
//        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(transactionList);
//
//        List<Transaction> transactions = transactionRepository.getAllTransaction();
//        assertEquals(2, transactions.size());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
//    }
//
//    @Test
//    void getTransactionById() {
//        List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(transaction);
//
//        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(transactionList);
//
//        List<Transaction> transactions = transactionRepository.getTransactionById(1L);
//        assertEquals(1, transactions.size());
//        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
//    }
//}