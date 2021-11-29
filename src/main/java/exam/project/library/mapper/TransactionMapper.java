package exam.project.library.mapper;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
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
        Map<Long, Book> bookMap = new HashMap<>();
        while (rs.next()) {
            Long transactionId = rs.getLong("transaction_id");
            if (transactionMap.get(transactionId) == null) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getLong("transaction_id"));
                transaction.setQuantity(rs.getInt("quantity"));
                transaction.setTotalPrice(rs.getDouble("price") * rs.getInt("quantity"));
                transaction.setDate(rs.getObject("date", LocalDateTime.class));

                Book book = new Book();
                book.setId(rs.getLong("book_id"));
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getDouble("price"));
                book.setISBN(rs.getString("isbn"));
                transaction.setBook(book);

                Member member = new Member();
                member.setId(rs.getLong("member_id"));
                member.setFirstName(rs.getString("firstname"));
                member.setLastName(rs.getString("lastname"));
                member.setTelephone(rs.getString("telephone"));
                transaction.setMember(member);

                transactionMap.put(transactionId, transaction);
            }
        }
        return new ArrayList<>(transactionMap.values());
    }
}
