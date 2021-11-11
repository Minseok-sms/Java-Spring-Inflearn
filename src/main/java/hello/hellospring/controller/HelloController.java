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
        model.addAttribute("data","hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    //MVC처리 Controller가 실행하면 viewResolver가 thymeleaf템플릿엔진을 처리함.

    @GetMapping("hello-string") // @ResponseBody 을 사용시 viewResolver를 사용하지않음.
    @ResponseBody // http에서 header body부의 데이터를 직접 넣어주겠다.
                  // template 을 사용하는것이아니다. 아무 태그도없이 그냥무식하게
                  //들어가는 방식이다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
    @GetMapping("hello-api")
    @ResponseBody // reponsebody를 사용후 객체를 반환시 객체가 JSON으로 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //자동완성 ctrl + shift + space
        hello.setName(name);
        return hello;  // 객체를 넘긴다. json 방식으로 나옴.
                        // XML방식 , json방식 비교
                    // 객체가오면 기본 default 방식이 json방식으로 넘겨준다.
                    // HttpMessageConverter가 동작한다.
                    // 객체를 json으로 바꿔주는 방식중하나가 jackson 라이브러리를 사용

    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

