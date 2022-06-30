package me.yanggang.kopring.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    // 1. Query Parameter
    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        @RequestParam(value = "name") _name: String,
        @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }

    // 2. Path Variable
    @RequestMapping(method = [RequestMethod.DELETE], path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @PathVariable("name") _name: String,
        @PathVariable("age") _age: Int): String {
        println(_name)
        println(_age)
        return "$_name $_age"
    }
}
