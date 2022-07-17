package exam.project.library.service;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import exam.project.library.repository.TransactionRepository;
import exam.project.library.service.implementations.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

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
        given(transactionRepository.getAllTransaction()).willReturn(transactionList);

        // when
        List<Transaction> transactions = transactionService.getAllTransaction();

        // then
        assertEquals(2, transactions.size());
        verify(transactionRepository, times(1)).getAllTransaction();
    }

    @Test
    void getTransactionById() {
        // given
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        given(transactionRepository.getTransactionById(anyLong())).willReturn(transactionList);

        // when
        List<Transaction> transactions = transactionService.getTransactionById(anyLong());

        // then
        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).getTransactionById(anyLong());
    }
}