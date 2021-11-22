package exam.project.library.service;

import exam.project.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthor();

    List<Author> getAuthorById(Long authorId);

    int saveNewAuthor(Author author);

    void saveWriteBook(Long authorId, Long bookId);

    void updateAuthor(Long authorId, Author author);

    void deleteAuthor(Long authorId);
}
