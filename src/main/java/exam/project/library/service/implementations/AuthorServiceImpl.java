package exam.project.library.service.implementations;

import exam.project.library.model.Author;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.service.AuthorService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Cacheable(value = "authors")
    public List<Author> getAllAuthor() {
        return authorRepository.getAllAuthor();
    }

    @Override
    @Cacheable(value = "author", key = "#authorId", unless = "#result==null")
    public List<Author> getAuthorById(Long authorId) {
        return authorRepository.getAuthorById(authorId);
    }

    @Override
    @CacheEvict(value = "authors", allEntries = true)
    public int saveNewAuthor(Author author) {
        return authorRepository.saveNewAuthor(author);
    }

    @Override
    @CacheEvict(value = "authors", allEntries = true)
    public void saveWriteBook(Long authorId, Long bookId) {
        authorRepository.saveWriteBook(authorId, bookId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "authors", allEntries = true),
            @CacheEvict(value = "author", key = "#authorId")
    })
    public void updateAuthor(Long authorId, Author author) {
        authorRepository.updateAuthor(authorId, author);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "authors", allEntries = true),
            @CacheEvict(value = "author", key = "#authorId")
    })
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteAuthor(authorId);
    }
}
