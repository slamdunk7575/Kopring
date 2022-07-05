package me.yanggang.kopring.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {
        // throw RuntimeException("강제 Exception 발생")
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    // 클래스 내부에 ExceptionHandler 정의하면 해당 Controller 내부의 예외만 처리할 수 있음 (GlobalControllerAdvice 타지 않음)
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(): ResponseEntity<String> {
        println("Controller Exception Handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}
