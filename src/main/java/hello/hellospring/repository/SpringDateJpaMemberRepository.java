package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDateJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

//    JPQL select m from Member m where m.name =?
//    m.name에서 name은 파라미터;
//    인터페이스 이름만으로 규칙에 따라 자동으로 쿼리를 생성
//    (인터페이스 이름 예: findByNameAndID, findByNameOrID)
    @Override
    Optional<Member> findByName(String name);
}
