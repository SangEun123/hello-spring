package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    /*회원 서비스를 위해 멤버 리포지토리를 가져옴*/
    private final MemberRepository memberRepository;

    //    멤버 서비스를 외부에서 넣어지도록 바꾸기
    //****컴포넌트 스캔 방식***
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    /*비즈니스 로직 중 같은 이름이 있는 중복 회원 불가*/
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        /*임의로 아이디 리턴*/
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        /*멤버 리포지토리에서 이름을 찾아서 가져옴*/
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    /*값이 있을 경우 throw 로직 실행*/
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
