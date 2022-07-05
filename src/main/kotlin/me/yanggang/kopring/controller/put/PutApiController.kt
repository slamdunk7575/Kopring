package me.yanggang.kopring.controller.put

import me.yanggang.kopring.model.http.Result
import me.yanggang.kopring.model.http.UserRequest
import me.yanggang.kopring.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

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
    // @Valid 는 해당 빈에 대해서 검증 (DeleteApiController @Validated 비교)
    // Controller 로 들어와서 Valid 한 결과를 BindingResult 로 받음
    // (BindingResult 가 없는 경우는 예외가 발생하면 바로 에러를 던짐)
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        if (bindingResult.hasErrors()) {
            // 400 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append("${field.field} : $message\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")

        // 0. response
        // apply(자기 자신 리턴) 패턴을 사용
        /*return UserResponse().apply {
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
        }*/
    }
}
