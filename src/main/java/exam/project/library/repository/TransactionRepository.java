package exam.project.library.repository;

import exam.project.library.mapper.TransactionMapper;
import exam.project.library.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public void saveNewTransaction(Transaction transaction, String bookId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(format);
        sql.add("INSERT INTO")
                .add("Transactions (member_id, book_id, total_price, quantity, date)")
                .add("VALUES (?, ?, ?, ?, ?)");
        log.info("sql = {}", sql);
        log.info("time = {}", formatDateTime);
        jdbcTemplate.update(sql.toString()
                , transaction.getMemberId()
                , bookId
                , transaction.getTotalPrice()
                , transaction.getQuantity()
                , formatDateTime);
    }
}
