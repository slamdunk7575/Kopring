package me.yanggang.kopring.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated // 컨트롤러 단위에서 전체 적용
class DeleteApiController {

    // 1. Query Parameter
    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        @RequestParam(value = "name") _name: String,

        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(value = 20, message = "age 20보다 커야 합니다.")
        @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

    // 2. Path Variable
    @RequestMapping(method = [RequestMethod.DELETE], path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @PathVariable("name")
        @Size(min = 2, max = 5, message = "name의 길이는 2~5자여야 합니다.")
        @NotNull
        _name: String,

        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(value = 20, message = "age 20보다 커야 합니다.")
        @PathVariable("age") _age: Int): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }
}
