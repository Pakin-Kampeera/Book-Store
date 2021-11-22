package exam.project.library.service.implementations;

import exam.project.library.model.Author;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return null;
    }

    @Override
    public List<Author> getAuthorById(Long authorId) {
        return null;
    }

    @Override
    public int saveNewAuthor(Author author) {
        return 1;
    }

    @Override
    public void saveWriteBook(Long authorId, Long bookId) {

    }

    @Override
    public void updateAuthor(Long authorId, Author author) {

    }

    @Override
    public void deleteAuthor(Long authorId) {

    }
}
