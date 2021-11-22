package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Publisher;
import exam.project.library.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PublisherService publisherService;

    String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Publisher publisher = new Publisher();
        publisher.setName("Wall Street");
        publisher.setStreet("3526 Morris Street");
        publisher.setCity("San Antonio");
        publisher.setZip("78218");
        this.body = objectMapper.writeValueAsString(publisher);
    }

    @Test
    void getAllPublisher() throws Exception {
        mockMvc.perform(get("/api/v1/publisher"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getMemberById() throws Exception {
        mockMvc.perform(get("/api/v1/publisher/{publisherId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void addPublisher() throws Exception {
        mockMvc.perform(post("/api/v1/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updatePublisher() throws Exception {
        mockMvc.perform(put("/api/v1/publisher/{publisherId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deletePublisher() throws Exception {
        mockMvc.perform(delete("/api/v1/publisher/{publisherId}", 1))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}