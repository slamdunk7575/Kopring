package me.yanggang.kopring.controller.get

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

}
