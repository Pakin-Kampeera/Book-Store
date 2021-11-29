package exam.project.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Transaction;
import exam.project.library.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
    }

//    @Test
//    void getAllTransaction() {
//    }
//
//    @Test
//    void getTransactionById() {
//    }
}