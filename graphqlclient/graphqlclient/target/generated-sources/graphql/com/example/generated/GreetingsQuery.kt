package com.example.generated

import com.expediagroup.graphql.client.GraphQLWebClient
import com.expediagroup.graphql.types.GraphQLResponse
import kotlin.String
import kotlin.Unit
import org.springframework.web.reactive.function.client.WebClient

const val GREETINGS_QUERY: String =
    "query GreetingsQuery {\r\n    schedule {\r\n        greetings\r\n    }\r\n}"

class GreetingsQuery(
  private val graphQLClient: GraphQLWebClient
) {
  suspend fun execute(requestBuilder: WebClient.RequestBodyUriSpec.() -> Unit = {}):
      GraphQLResponse<GreetingsQuery.Result> = graphQLClient.execute(GREETINGS_QUERY,
      "GreetingsQuery", null, requestBuilder)

  data class ScheduleDetails(
    val greetings: String
  )

  data class Result(
    val schedule: GreetingsQuery.ScheduleDetails
  )
}
