package exam.project.library.mapper;

import exam.project.library.model.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                transaction.setQuantity(rs.getInt("quantity"));
            }

            Transaction transaction = transactionMap.get(transactionId);


        }
        return new ArrayList<>(transactionMap.values());
    }
}
