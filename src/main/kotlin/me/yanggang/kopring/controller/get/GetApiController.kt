package me.yanggang.kopring.controller.get

import me.yanggang.kopring.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello Kotlin"
    }

    @RequestMapping(path = ["/request-mapping"], method = [RequestMethod.GET])
    fun requestMapping(): String {
        return "Request Mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("이름:$name, 나이:$age")
        return "$name, $age";
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
        val name = "kopring"
        println("이름:$_name, 나이:$age")
        return "$_name, $age";
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam(value = "name") name: String,
        @RequestParam(name = "age") age: Int): String {
        println("이름:$name, 나이:$age")
        return "$name $age"
    }

    // name, age, address, email
    // 스프링의 @RestController 선언하면
    // 리턴 타입이 Object일때 ObjectMapper를 통해 JSON으로 변경

    // 예: phoneNumber 를 받는다고 했을때
    // phonenumber 또는 phone-number를 사용하게 되는데 코틀린에서는 변수명에 -(하이픈) 을 사용할 수 없음
    // 따라서 이경우는 Object 형태로 받을 수 없고 @RequestParam(name = "phone-number") 속성을 사용해야함
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }
}
