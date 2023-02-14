package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //save를 할 때 저장해둘 곳
    private static Map<Long, Member> store = new HashMap<>();
    //0,1,2 등 키값을 생성해 줌
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //시스템에서 키값(++sequence)을 생성해서 id에 저장
        member.setId(++sequence);
        //member에서 id를 가져와서 DB에 저장
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        //id가 null이어도 Optional로 둘러져있기 때문에 감쌀 수 있음
        //감싸서 반환하면 클라이언트에서 무엇을 할 수 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //스토에에서 필터링(스토어에 있는 이름과 파라미터로 넘어온 이름이 같을 경우)
        //람다식 사용, findAny: 하나라도 찾으면 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //map을 리스트로 변환후 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
