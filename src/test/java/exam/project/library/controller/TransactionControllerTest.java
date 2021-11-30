package exam.project.library.controller;

import exam.project.library.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionService transactionService;

    @Test
    void getAllTransaction() throws Exception {
        mockMvc.perform(get("/api/v1/transaction"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getTransactionById() throws Exception {
        mockMvc.perform(get("/api/v1/transaction/{transactionId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }
}