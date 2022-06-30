package me.yanggang.kopring.controller.put

import me.yanggang.kopring.model.http.Result
import me.yanggang.kopring.model.http.UserRequest
import me.yanggang.kopring.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. response
        // apply(자기 자신 리턴) 패턴을 사용
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
            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "aaa"
                this.age = 100
                this.email = "aaa@gmail.com"
                this.address = "aaa address"
                this.phoneNumber = "010-1111-1111"
            })

            userList.add(UserRequest().apply {
                this.name = "bbb"
                this.age = 200
                this.email = "bbb@gmail.com"
                this.address = "bbb address"
                this.phoneNumber = "010-2222-2222"
            })

            this.userRequest = userList
        }
    }
}
