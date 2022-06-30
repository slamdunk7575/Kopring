package me.yanggang.kopring.controller.page

import me.yanggang.kopring.model.http.Result
import me.yanggang.kopring.model.http.UserRequest
import me.yanggang.kopring.model.http.UserResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

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

    // @Controller 에서 JSON 형태로 응답을 내려줄때 사용
    @ResponseBody
    @GetMapping("/test")
    fun response(): UserResponse {
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(UserRequest().apply {
                this.name = "yanggang"
                this.age = 100
                this.email = "yanggang@gmail.com"
                this.address = "yanggang address"
                this.phoneNumber = "010-1234-5678"
            })

            this.userRequest = userList
        }
    }
}
