package exam.project.library.services;

import exam.project.library.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PublisherServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    PublisherService publisherService;

    Publisher publisher1, publisher2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        assertEquals(1, publisherService.saveNewPublisher(publisher1));
    }

    @Test
    void updatePublisher() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void deletePublisher() {
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString());
    }
}