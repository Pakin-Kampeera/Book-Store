package exam.project.library.controller;

import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import exam.project.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMember(), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<Member>> getMemberById(@PathVariable("memberId") Long memberId) {
        return new ResponseEntity<>(memberService.getMemberById(memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> addMember(@Valid @RequestBody Member member) {
        memberService.saveNewMember(member);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/member/");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyBook(@Valid @RequestBody Transaction transaction) {
        memberService.buyBook(transaction);
    }

    @PutMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@PathVariable("memberId") Long memberId, @Valid @RequestBody Member member) {
        memberService.updateMember(memberId, member);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
    }
}