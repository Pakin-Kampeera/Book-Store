package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Publisher;
import exam.project.library.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherService publisherService;

    private MockMvc mockMvc;
    private String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();

        Publisher publisher = new Publisher()
                .setName("Wall Street")
                .setStreet("3526 Morris Street")
                .setCity("San Antonio")
                .setZip("78218");

        ObjectMapper objectMapper = new ObjectMapper();
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