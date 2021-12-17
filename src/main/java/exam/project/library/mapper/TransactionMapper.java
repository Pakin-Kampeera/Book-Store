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
        while (rs.next()) {
            Long transactionId = rs.getLong("transaction_id");
            if (transactionMap.get(transactionId) == null) {
                Transaction transaction = new Transaction()
                        .setTransactionId(rs.getLong("transaction_id"))
                        .setQuantity(rs.getInt("quantity"))
                        .setTotalPrice(rs.getDouble("price") * rs.getInt("quantity"))
                        .setDate(rs.getObject("date", LocalDateTime.class));

                Book book = new Book()
                        .setBookId(rs.getLong("book_id"))
                        .setTitle(rs.getString("title"))
                        .setPrice(rs.getDouble("price"))
                        .setISBN(rs.getString("isbn"));
                transaction.setBook(book);

                Member member = new Member()
                        .setMemberId(rs.getLong("member_id"))
                        .setFirstName(rs.getString("firstname"))
                        .setLastName(rs.getString("lastname"))
                        .setTelephone(rs.getString("telephone"));
                transaction.setMember(member);
                transactionMap.put(transactionId, transaction);
            }
        }
        return new ArrayList<>(transactionMap.values());
    }
}
