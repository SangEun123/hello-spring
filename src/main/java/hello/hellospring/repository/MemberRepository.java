package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//회원 리포지토리 인터페이스
public interface MemberRepository {
    Member save(Member member); //회원 저장
    //Optional: findByID 혹은 findByName이 null일 경우 null을 반환하는 대신 다른 값 반환
    Optional<Member> findById(Long id); //id로 회원 찾기
    Optional<Member> findByName(String name); //이름으로 회원 찾기
    List<Member> findAll(); //모든 회원
}
