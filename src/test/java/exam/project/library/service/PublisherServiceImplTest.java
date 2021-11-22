package exam.project.library.service;

import exam.project.library.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PublisherServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    PublisherService publisherService;

    Publisher publisher1, publisher2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        publisherService = new PublisherServiceImpl(jdbcTemplate);

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
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        publisherSet.add(publisher2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(publisherSet);

        List<Publisher> publishers = publisherService.getAllPublisher();
        assertEquals(2, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getPublisherById() {
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(publisherSet);

        List<Publisher> publishers = publisherService.getPublisherById(1L);
        assertEquals(1, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewPublisher() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        publisherService.saveNewPublisher(publisher1);
        assertEquals(1, publisherService.saveNewPublisher(publisher1));
    }

    @Test
    void updatePublisher() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
        publisherService.updatePublisher(1L, publisher1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deletePublisher() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);
        publisherService.deletePublisher(1L);
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}