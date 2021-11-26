package exam.project.library.mapper;

import exam.project.library.model.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionMapper implements ResultSetExtractor<List<Transaction>> {
    @Override
    public List<Transaction> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Transaction> transactionMap = new HashMap<>();
        while (rs.next()) {
            Long transactionId = rs.getLong("transaction_id");
            if (transactionMap.get(transactionId) == null) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getLong("transaction_id"));
                transaction.setTotalPrice(rs.getDouble("total_price"));
                transaction.setDate(rs.getObject("date", LocalDateTime.class));
                transactionMap.put(transactionId, transaction);
            }

//            Transaction transaction = transactionMap.get(transactionId);
//            Set<Book> books = transaction.getBooks();
//            Book book = new Book();
//            book.setId(rs.getLong("book_id"));
//            book.setTitle(rs.getString("title"));
//            book.setPrice(rs.getDouble("price"));
//            book.setISBN(rs.getString("isbn"));
//            book.setQuantity(rs.getInt("quantity"));
//            books.add(book);
//            transaction.setBooks(books);
        }
        return new ArrayList<>(transactionMap.values());
    }
}