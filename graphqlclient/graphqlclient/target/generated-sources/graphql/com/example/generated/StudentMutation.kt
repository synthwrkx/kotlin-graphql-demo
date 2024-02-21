package com.example.generated

import com.expediagroup.graphql.client.GraphQLWebClient
import com.expediagroup.graphql.types.GraphQLResponse
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import org.springframework.web.reactive.function.client.WebClient

const val STUDENT_MUTATION: String =
    "mutation StudentMutation(${'$'}name: String, ${'$'}age: Int) {\r\n    createStudent(studentInput: { name: ${'$'}name, age: ${'$'}age }) {\r\n        name\r\n        age\r\n    }\r\n}"

class StudentMutation(
  private val graphQLClient: GraphQLWebClient
) {
  suspend fun execute(variables: StudentMutation.Variables,
      requestBuilder: WebClient.RequestBodyUriSpec.() -> Unit = {}):
      GraphQLResponse<StudentMutation.Result> = graphQLClient.execute(STUDENT_MUTATION,
      "StudentMutation", variables, requestBuilder)

  data class Variables(
    val name: String? = null,
    val age: Int? = null
  )

  data class StudentDetails(
    val name: String,
    val age: Int
  )

  data class Result(
    val createStudent: List<StudentMutation.StudentDetails>
  )
}
