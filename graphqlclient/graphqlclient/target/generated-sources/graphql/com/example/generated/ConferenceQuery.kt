package com.example.generated

import com.expediagroup.graphql.client.GraphQLWebClient
import com.expediagroup.graphql.types.GraphQLResponse
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import org.springframework.web.reactive.function.client.WebClient

const val CONFERENCE_QUERY: String =
    "query ConferenceQuery(${'$'}namePrefix: String){\r\n    conference {\r\n        name\r\n    }\r\n\r\n    people(namePrefix: ${'$'}namePrefix) {\r\n        __typename\r\n        name\r\n        ... on Attendee {\r\n            ticket\r\n        }\r\n        ... on Speaker {\r\n            talks\r\n        }\r\n    }\r\n\r\n    s1: schedule {\r\n        greetings\r\n        talks\r\n    }\r\n\r\n    s2: schedule {\r\n        greetings\r\n        talks\r\n    }\r\n}\r\n"

class ConferenceQuery(
  private val graphQLClient: GraphQLWebClient
) {
  suspend fun execute(variables: ConferenceQuery.Variables,
      requestBuilder: WebClient.RequestBodyUriSpec.() -> Unit = {}):
      GraphQLResponse<ConferenceQuery.Result> = graphQLClient.execute(CONFERENCE_QUERY,
      "ConferenceQuery", variables, requestBuilder)

  data class Variables(
    val namePrefix: String? = null
  )

  /**
   * Great place to work
   */
  data class Conference(
    val name: String
  )

  enum class TicketType {
    CONFERENCE,

    FULL,

    WORKSHOP,

    /**
     * This is a default enum value that will be used when attempting to deserialize unknown value.
     */
    @JsonEnumDefaultValue
    __UNKNOWN_VALUE
  }

  data class Attendee(
    override val name: String,
    val ticket: ConferenceQuery.TicketType
  ) : ConferenceQuery.People

  data class Speaker(
    override val name: String,
    val talks: List<String>
  ) : ConferenceQuery.People

  @JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "__typename"
  )
  @JsonSubTypes(value = [com.fasterxml.jackson.annotation.JsonSubTypes.Type(value =
      ConferenceQuery.Attendee::class,
      name="Attendee"),com.fasterxml.jackson.annotation.JsonSubTypes.Type(value =
      ConferenceQuery.Speaker::class, name="Speaker")])
  interface People {
    val name: String
  }

  data class ScheduleDetails(
    val greetings: String,
    val talks: List<String>
  )

  data class ScheduleDetails2(
    val greetings: String,
    val talks: List<String>
  )

  data class Result(
    val conference: ConferenceQuery.Conference,
    val people: List<ConferenceQuery.People>,
    val s1: ConferenceQuery.ScheduleDetails,
    val s2: ConferenceQuery.ScheduleDetails2
  )
}
