package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        //model = MVC의 M(model)
        model.addAttribute("data", "hello!!");
        return "hello";

    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
//        RequestParam을 통해 외부에서 파라미터("name")를 받음
//        model을 넘겨줘야함
        model.addAttribute("name", name);
//        "name": 키, name: helloMvc 파라미터 String name
        return "hello-template";

    }

    @GetMapping("hello-string")
    @ResponseBody
    //    http의 header부와 body부의 body
    //    body부에 데이터를 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
//        name이 spring일 경우 결과: "hello spring"
//        템플릿 엔진과의 차이: 뷰 이런것 없이 그대로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
