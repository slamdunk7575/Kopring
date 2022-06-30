package me.yanggang.kopring.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

// html 페이지를 내려줄때 사용하는 컨트롤러
// resource/static 아래에 있는 html 파일을 찾음
// resource/template 아래에 있는 파일을 찾으려면 Thymeleaf 의존성을 추가하여 ViewResolver 설정을 해야함
@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html"
    }
}
