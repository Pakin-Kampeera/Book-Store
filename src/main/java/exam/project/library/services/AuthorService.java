package exam.project.library.services;

import exam.project.library.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();

    List<Author> getAuthorById(Long authorId);

    int saveNewAuthor(Author author);

    void updateAuthor(Long authorId, Author author);

    void deleteAuthor(Long authorId);
}
