package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest { // integration : 통합

   @Autowired private MemberService memberService;
   @Autowired private MemberRepository memberRepository;

    @Test
    void 회원가입(){
        //given
        Member member = new Member();
        member.setName("박종혁");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원예외(){
        //given
        Member member = new Member();
        member.setName("박종혁"); // 라인 복사 : ctrl + d

        Member member2= new Member(); // 이름 변경하기 : shift + f6
        member2.setName("박종혁");

        //when
        memberService.join(member);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 회원리스트가져오기(){
        //given
        Member member = new Member();
        member.setName("박종혁1");

        Member member2 = new Member();
        member2.setName("박종혁2");

        Member member3 = new Member();
        member3.setName("박종혁3");

        //when
        memberService.join(member);
        memberService.join(member2);
        memberService.join(member3);

        //then
        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(3);
    }

}
