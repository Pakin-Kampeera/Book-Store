package exam.project.library.services;

import exam.project.library.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PublisherServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    PublisherService publisherService;

    Publisher publisher1, publisher2;

    @BeforeEach
    void setUp() {
        publisher1 = new Publisher();
        publisher1.setId(1L);
        publisher1.setName("London House");
        publisher1.setStreet("1234");
        publisher1.setCity("Manchester");
        publisher1.setZip("2456");

        publisher2 = new Publisher();
        publisher2.setId(1L);
        publisher2.setName("City Hall");
        publisher2.setStreet("7432");
        publisher2.setCity("Houston");
        publisher2.setZip("8589");
    }

    @Test
    void getAllPublisher() {
        Set<Publisher> publisherSet = new HashSet<>();
        publisherSet.add(publisher1);
        publisherSet.add(publisher2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(publisherSet);

        List<Publisher> publishers = publisherService.getAllPublisher();
        assertEquals(2, publishers.size());
    }

    @Test
    void getPublisherById() {
        Set<Publisher> publisherSet = new HashSet<>();
        publisherSet.add(publisher1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(publisherSet);

        List<Publisher> publishers = publisherService.getPublisherById(anyLong());
        assertEquals(1, publishers.size());
    }

    @Test
    void saveNewPublisher() {
//        when(publisherService.saveNewPublisher()).thenReturn();
    }

    @Test
    void updatePublisher() {
//        when(publisherService.updatePublisher()).thenReturn();
    }

    @Test
    void deletePublisher() {
//        when(publisherService.deletePublisher()).thenReturn();
    }
}