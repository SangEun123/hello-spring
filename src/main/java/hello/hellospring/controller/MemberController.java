package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/*Controller의 경우 컴포넌트 스캔 방식으로만 스프링에 올라감 */
@Controller
public class MemberController {

    private final MemberService memberService;

/*  ***컴포넌트 스캔 방식***
    //final 변수인 memberService를 스프링이 파라미터인 memberService와 연결시켜줌
    //---
    //Autowired 스프링 컨테이너에서 memberService를 가져옴
    //memberService는 순수 자바 클래스임. 스프링이 알 수 있는 방법이 없음
    //MemberService 클래스에 들어가서 @Service 입력
    //MemoryMemberRepository에 @Repository 입력
    //controller-service-repository 연결
 */

    /*Controller의 경우 컴포넌트 스캔 방식으로만 스프링에 올라감 */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value =  "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }


}
