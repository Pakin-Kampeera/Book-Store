package exam.project.library.repository;

import exam.project.library.mapper.MemberMapper;
import exam.project.library.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Log4j2
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public List<Member> getAllMember() {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("((Members LEFT JOIN Transactions ON Members.member_id = Transactions.member_id)")
                .add("LEFT JOIN Books ON Books.book_id = Transactions.Book_id)");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new MemberMapper());
    }

    public List<Member> getMemberById(Long memberId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("((Members LEFT JOIN Transactions ON Members.member_id = Transactions.member_id)")
                .add("LEFT JOIN Books ON Books.book_id = Transactions.Book_id)")
                .add("WHERE Members.member_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new MemberMapper()
                , memberId);
    }

    public int saveNewMember(Member member) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("INSERT INTO")
                .add("Members (firstname, lastname, telephone)")
                .add("VALUES (?, ?, ?)");
        log.info("sql = {}", sql);
        return jdbcTemplate.update(sql.toString()
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone());
    }

    public void updateMember(Long memberId, Member member) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("UPDATE")
                .add("Members")
                .add("SET firstname = ?, lastname = ?, telephone = ?")
                .add("WHERE member_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone()
                , memberId);
    }

    public void deleteMember(Long memberId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("DELETE FROM")
                .add("Members")
                .add("WHERE member_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , memberId);
    }
}
