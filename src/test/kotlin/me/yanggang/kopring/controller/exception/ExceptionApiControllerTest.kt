package me.yanggang.kopring.controller.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.yanggang.kopring.model.http.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect (
            status().isOk
            // status().`is`(400)
        ).andExpect (
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "yanggang")
        queryParams.add("age", "20")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("yanggang 20")
        ).andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "yanggang")
        queryParams.add("age", "9")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "yanggang"
            this.age = 20
            this.phoneNumber = "010-1234-5678"
            this.address = "하남시"
            this.email = "yanggang@gmail.com"
            this.createdAt = "2022-07-12 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("yanggang")
        ).andExpect(
            jsonPath("\$.age").value("20")
        ).andExpect(
            jsonPath("\$.email").value("yanggang@gmail.com")
        ).andDo(print())
    }

    @Test
    fun postFailTest() {
        val userRequest = UserRequest().apply {
            this.name = "yanggang"
            this.age = -1
            this.phoneNumber = "010-1234-5678"
            this.address = "하남시"
            this.email = "yanggang@gmail.com"
            this.createdAt = "2022-07-12 13:00:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().`is`(400)
        ).andDo(print())
    }
}
