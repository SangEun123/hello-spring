package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*memberService와 memberRepository는 서로 다른 객체이기 때문에
    내용물이 달라질 경우 문제가 생길 수 있음
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/

    MemberService memberService;
    MemoryMemberRepository memberRepository;

//    memberService입장에서 memberRepository를 외부에서 넣어줌
//    => DI(dependency Indection)
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }




    //테스트가 실행될 때마다 AfterEach 메소드 내부의 메소드가 실행
    //예) save 메소드 실행 후  afterEach() 실행, 다른 메소드 실행 후 afterEach() 실행
    @AfterEach
    public void afterEach(){

        memberRepository.clearStore();
    }



    //    테스트코드 메소드는 한글로 적어도 상관 없음(외국인이랑 일하지 않는 이상)
//    -> 실제 코드에 포함 X
    @Test
    void 회원가입() {
//        given
        Member member = new Member();
        member.setName("Spring");

//        when
        Long savaId = memberService.join(member);

//        then
        Member findMember = memberService.findOne(savaId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //테스트는 정상플로우보다 예외 플로우가 더 중요(중복 회원 검증 로직)
    @Test
    public void 중복_회원_예외() throws Exception{
//        given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
//        when
//        로직 실행시 IllegalStateException 예외가 터져야 함
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*      try-catch절로 예외 잡는 방법(추천하지는 않음)
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

//        then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}