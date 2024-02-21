package com.example.graphqldemo1.scheduledetails

import com.example.graphqldemo1.context.CustomGraphQLContext
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class ScheduleDetails(val greetings: String, val talks: List<String>) {

    fun greetings(context: CustomGraphQLContext) = if(context.languages.firstOrNull() == "fr") {
        "Bienvenue"
    } else {
        "Welcome to list of talks"
    }
    private val logger = LoggerFactory.getLogger(ScheduleDetails::class.java)

    //Computation intensive operations to be in functions such that only invoked when needed
    //4 seconds time delay when two aliases of graphQL are called from playground
//    fun talks(): List<String> {
//        logger.info("Getting list of talks")
//
//        val cached = cachedTalks()
//        val databased = dbTalks()
//
//        logger.info("Returning list of talks")
//
//        return listOf(cached, databased).flatten()
//        //return listOf("GraphQL", "kotlin-graphql")
//    }

    //Reduced to 2 seconds time delay
    suspend fun talks(): List<String> = coroutineScope{
        logger.info("Getting list of talks")

        val cached = async { cachedTalks() }
        val databased = async{ dbTalks() }

        logger.info("Returning list of talks")

        listOf(cached.await(), databased.await()).flatten()
        //return listOf("GraphQL", "kotlin-graphql")
    }

//    private fun cachedTalks(): List<String> {
//        Thread.sleep(100)
//        logger.info("Get cached talks")
//        return listOf("GraphQL is awesome")
//    }
//
//    private fun dbTalks(): List<String> {
//        Thread.sleep(2000)
//        logger.info("Get DB talks")
//        return listOf("graphql-kotlin is even better")
//    }

    private suspend fun cachedTalks(): List<String> {
        delay(100)
        logger.info("Get cached talks")
        return listOf("GraphQL is awesome")
    }

    private suspend fun dbTalks(): List<String> {
        delay(2000)
        logger.info("Get DB talks")
        return listOf("graphql-kotlin is even better")
    }
}
