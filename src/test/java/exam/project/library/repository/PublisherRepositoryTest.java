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
import static org.mockito.BDDMockito.given;
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
        this.publisher1 = new Publisher()
                .setPublisherId(1L)
                .setName("London House")
                .setStreet("1234")
                .setCity("Manchester")
                .setZip("2456");

        this.publisher2 = new Publisher()
                .setPublisherId(1L)
                .setName("City Hall")
                .setStreet("7432")
                .setCity("Houston")
                .setZip("8589");
    }

    @Test
    void getAllPublisher() {
        // given
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        publisherSet.add(publisher2);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(publisherSet);

        // when
        List<Publisher> publishers = publisherRepository.getAllPublisher();

        // then
        assertEquals(2, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getPublisherById() {
        // given
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(publisherSet);

        // when
        List<Publisher> publishers = publisherRepository.getPublisherById(1L);

        // then
        assertEquals(1, publishers.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewPublisher() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString())).willReturn(1);

        // when
        publisherRepository.saveNewPublisher(publisher1);

        // then
        assertEquals(1, publisherRepository.saveNewPublisher(publisher1));
    }

    @Test
    void updatePublisher() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong())).willReturn(1);

        // when
        publisherRepository.updatePublisher(1L, publisher1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deletePublisher() {
        // given
        given(jdbcTemplate.update(anyString(), anyLong())).willReturn(1);

        // when
        publisherRepository.deletePublisher(1L);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}