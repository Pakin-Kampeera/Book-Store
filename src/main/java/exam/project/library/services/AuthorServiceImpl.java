package exam.project.library.services;

import exam.project.library.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Override
    public List<Author> getAllAuthor() {
        return null;
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return null;
    }

    @Override
    public int saveNewAuthor(Author author) {
        return 0;
    }

    @Override
    public int updateAuthor(Long authorId, Author author) {
        return 0;
    }

    @Override
    public void deleteAuthor(Long authorId) {

    }
}
