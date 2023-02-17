package hello.hellospring;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;

/*
 * ***자동 의존 관계 설정 방식***
 * */
@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//    }


}