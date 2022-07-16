package exam.project.library.repository;

import exam.project.library.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherRepositoryTest {

    @InjectMocks
    private PublisherRepository publisherRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Publisher publisher1, publisher2;

    @BeforeEach
    void setUp() {
        publisher1 = new Publisher()
                .setPublisherId(1L)
                .setName("London House")
                .setStreet("1234")
                .setCity("Manchester")
                .setZip("2456");

        publisher2 = new Publisher()
                .setPublisherId(1L)
                .setName("City Hall")
                .setStreet("7432")
                .setCity("Houston")
                .setZip("8589");
    }

    @Test
    void getAllPublisher() {
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        publisherSet.add(publisher2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(publisherSet);

        List<Publisher> publishers = publisherRepository.getAllPublisher();
        assertEquals(2, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getPublisherById() {
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(publisherSet);

        List<Publisher> publishers = publisherRepository.getPublisherById(1L);
        assertEquals(1, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewPublisher() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);

        publisherRepository.saveNewPublisher(publisher1);

        assertEquals(1, publisherRepository.saveNewPublisher(publisher1));
    }

    @Test
    void updatePublisher() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong())).thenReturn(1);

        publisherRepository.updatePublisher(1L, publisher1);

        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deletePublisher() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);

        publisherRepository.deletePublisher(1L);

        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}