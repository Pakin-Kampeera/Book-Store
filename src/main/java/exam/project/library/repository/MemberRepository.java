package exam.project.library.repository;

import exam.project.library.mapper.MemberMapper;
import exam.project.library.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> getAllMember() {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("((Members LEFT JOIN Buy ON Members.member_id = Buy.member_id)")
                .add("LEFT JOIN Books ON Books.book_id = Buy.Book_id)");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new MemberMapper());
    }

    public List<Member> getMemberById(Long memberId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("((Members LEFT JOIN Buy ON Members.member_id = Buy.member_id)")
                .add("LEFT JOIN Books ON Books.book_id = Buy.Book_id)")
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

    public void buyBook(Long memberId, Long bookId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("INSERT INTO")
                .add("Buy (member_id, book_id)")
                .add("VALUES (?, ?)");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , memberId
                , bookId);
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
