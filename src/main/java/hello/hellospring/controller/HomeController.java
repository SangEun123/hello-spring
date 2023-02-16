package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    / : localhost:8080으로 가면 바로 home() 리턴
//    ---
//    이전에는 hello.html과 매칭 되었었는데 현재는 home.html과 매칭
//    이유: 컨트롤러(HomeController)가 정적 파일(hello.html)보다 우선순위가 높으므로
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
