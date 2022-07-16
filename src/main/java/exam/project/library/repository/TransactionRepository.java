package exam.project.library.repository;

import exam.project.library.mapper.TransactionMapper;
import exam.project.library.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Log4j2
@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public List<Transaction> getAllTransaction() {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("*")
                .add("FROM")
                .add("((Transactions INNER JOIN Members ON Transactions.member_id = Members.member_id)")
                .add("INNER JOIN Books ON Transactions.book_id = Books.book_id)")
                .add("ORDER BY date ASC");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new TransactionMapper());
    }

    public List<Transaction> getTransactionById(Long transactionId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("*")
                .add("FROM")
                .add("((Transactions INNER JOIN Members ON Transactions.member_id = Members.member_id)")
                .add("INNER JOIN Books ON Transactions.book_id = Books.book_id)")
                .add("WHERE Transactions.transaction_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new TransactionMapper()
                , transactionId);
    }

    public void saveNewTransaction(Transaction transaction) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("INSERT INTO")
                .add("Transactions (member_id, book_id, quantity, date)")
                .add("VALUES (?, ?, ?, CURRENT_TIMESTAMP)");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , transaction.getMemberId()
                , transaction.getBookId()
                , transaction.getQuantity());
    }
}
