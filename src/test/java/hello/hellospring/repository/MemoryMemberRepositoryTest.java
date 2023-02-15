package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//클래스를 전체 테스트 할 경우 여기서 실행
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 실행될 때마다 AfterEach 메소드 내부의 메소드가 실행
    //예) save 메소드 실행 후  afterEach() 실행, 다른 메소드 실행 후 afterEach() 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    //@Test 입력시 org.junit.jupiter.api.Test import -> 해당 메소드만 실행 가능
    //실행해 보고 싶을 경우 메소드이름 옆에 재생버튼(삼각형) 클릭
    @Test
    public void save() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        //검증 단계: get()-> Optional에서 값을 꺼낼 때 사용(좋은 방법은 아님)
        Member result = repository.findById(member.getId()).get();
        //검증 방법 1: 메모리에 저장되어 있는 id와 저장할 id가 같을 때- > true 콘솔 출력
//        System.out.println("result = " + (result == member));
        // 검증 방법 2
//        Assertions.assertEquals(member, result);
        // 검증 방법 3(검증방법 2를 편하게(간단하게?) 사용)
        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName() {

        //given
        //정교한 멤버 확인을 위한 멤버 객체 2개 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        //spring1 찾기
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
