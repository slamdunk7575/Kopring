package me.yanggang.kopring.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest (
        @field:NotEmpty
        @field:Size(min = 2, max = 8)
        var name: String?=null,

        @field:PositiveOrZero // 0 보다 작은 숫자 검증 (양수, 0 포함)
        var age: Int?=null,

        @field:Email // email 양식
        var email: String?=null,

        @field:NotBlank // 공백을 검증
        var address: String?=null,

        // P1. Naming 컨벤션이 다른 문제
        // 코틀린은 Camel Case(예: phoneNumber)를 사용하고
        // Rest API에서 JSON은 Snake Case(예: phone_number)를 사용함
        // @JsonProperty를 사용하여 이름 지정할 수 있음
        // @JsonProperty("phone_number")
        @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
        var phoneNumber: String?=null

        // P2. 현업에서는 여러 곳에 API 연동을 처리하는데 그럴때마다 일일히 @JsonProperty 지정한다?
        // 클래스에 JSON 타입을 지정할 수 있음
        // (추가)
        // Object Mapper를 Bean으로 등록할때 지정하거나 application.property 파일에 설정할 수도 있음
)
