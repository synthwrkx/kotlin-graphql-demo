package com.example.graphqldemo1.query

import com.example.graphqldemo1.scheduledetails.ScheduleDetails
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component


@Component
class ConferenceQuery: Query {

    fun conference() = Conference(1, name = "Spring One 2020")

    fun people(namePrefix: String?) = listOf(
        Attendee("John", TicketType.FULL),
        Speaker("Jeremy", listOf("Kotlin", "GraphQL", "kotlin-graphql"))
    ).filter { it.name == namePrefix.orEmpty() }

    fun schedule() = ScheduleDetails(
        "Welcome to Spring One Conference",
        listOf("All about kotlin-graphql", "Spring One")
    )
}

interface People {
    val name: String
}

data class Attendee (
    override val name: String,
    val ticket: TicketType
) : People

enum class TicketType {
    CONFERENCE, WORKSHOP, FULL
}

data class Speaker (
    override val name: String,
    val talks: List<String>
) : People

@GraphQLDescription("Great place to work")
data class Conference(
    val conferenceId: Int,
    //@Deprecated("No longer needed")
    val name: String
)
