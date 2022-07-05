package me.yanggang.kopring.advice

import me.yanggang.kopring.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class]) // 특정 Controller 에 대한 예외만 처리하도록 지정할 수도 있음
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}
