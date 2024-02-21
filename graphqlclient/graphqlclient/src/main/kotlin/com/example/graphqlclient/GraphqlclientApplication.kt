package com.example.graphqlclient

import com.expediagroup.graphql.client.GraphQLWebClient
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import com.example.generated.GreetingsQuery
import com.example.generated.ConferenceQuery
import org.springframework.web.reactive.function.client.WebClient

//@SpringBootApplication
class GraphqlclientApplication

fun main(args: Array<String>) {
	//runApplication<GraphqlclientApplication>(*args)

	val builder = WebClient.builder()
	runBlocking {
		val client = GraphQLWebClient(url = "http://localhost:8080/graphql", builder = builder)

		val greetingsQuery = GreetingsQuery(client)
		val greeting = greetingsQuery.execute() {
			header("Accept-Language", "fr")
		}
		println(greeting.data)

		val conferenceQuery = ConferenceQuery(client)
		val conference = conferenceQuery.execute(variables = ConferenceQuery.Variables("John"))
		println(conference.data)

	}
}
