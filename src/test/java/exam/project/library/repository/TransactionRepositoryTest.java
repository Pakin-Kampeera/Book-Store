package exam.project.library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
class TransactionRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllTransaction() {
        MockitoAnnotations.openMocks(this);
        transactionRepository = new TransactionRepository(jdbcTemplate);
    }

    @Test
    void getTransactionById() {
    }

    @Test
    void saveNewTransaction() {
    }
}