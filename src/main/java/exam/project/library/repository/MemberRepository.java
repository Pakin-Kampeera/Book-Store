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

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> getAllMember() {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("Members, Books, Buy")
                .add("WHERE Buy.member_id = Members.member_id")
                .add("AND Buy.book_id = Books.book_id");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new MemberMapper());
    }

    public List<Member> getMemberById(Long memberId) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT")
                .add("Members.*, Books.*")
                .add("FROM")
                .add("Members, Books, Buy")
                .add("WHERE Buy.member_id = Members.member_id")
                .add("AND Buy.book_id = Books.book_id")
                .add("AND Members.member_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new MemberMapper()
                , memberId);
    }

    public int saveNewMember(Member member) {
        final StringJoiner sql = new StringJoiner(" ");
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
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO")
                .add("Buy (member_id, book_id)")
                .add("VALUES (?, ?)");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , memberId
                , bookId);
    }

    public void updateMember(Long memberId, Member member) {
        final StringJoiner sql = new StringJoiner(" ");
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
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE FROM")
                .add("Members")
                .add("WHERE member_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , memberId);
    }
}
