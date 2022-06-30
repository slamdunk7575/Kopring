package me.yanggang.kopring.controller.response

import me.yanggang.kopring.model.http.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
        return age?.let {
            if (age < 20) {
                return ResponseEntity.status(400).body("age 값은 20 보다 커야 합니다.")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            return ResponseEntity.status(400).body("age 값이 누락되었습니다.")
        }
    }

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

}
