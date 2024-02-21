package com.example.graphqldemo1.studentdetails

import com.expediagroup.graphql.spring.operations.Mutation
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import com.expediagroup.graphql.spring.operations.Query

data class StudentDetails(val name: String, val age: Int)

@Component
class StudentMutation: Mutation {

    var studentDetails = ArrayList<StudentDetails>()
    private val logger = LoggerFactory.getLogger(StudentDetails::class.java)

    fun createStudent(studentInput: StudentDetails): ArrayList<StudentDetails> {
        logger.info("Adding student details")
        studentDetails.add(studentInput)
        println(studentDetails[0])
        return studentDetails
    }
}