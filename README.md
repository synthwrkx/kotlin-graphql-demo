# kotlin-graphql-demo

The graphqldemo1 is server application.
The graphqlclient is client application.

Run the graphqldemo1 first and then the graphqlclient application.

GraphQL operations in Playground is given below.
To run this got to: http://localhost:8080/playground

QUERY VARIABLES
---------------
{
  "namePrefix": "John"
}

HTTP HEADERS
------------
{
  "Accept-language": "fr"
}

PLAYGROUND
-----------
query ConferenceQuery($namePrefix: String){
  conference {
    name
  }

  people(namePrefix: $namePrefix) {
    __typename
    name
    ... on Attendee {
      ticket
    }
    ... on Speaker {
      talks
    }
  }

  s1: schedule {
    greetings
    talks
  }

  s2: schedule {
    greetings
    talks
  }
}

mutation StudentMutation {
  createStudent(studentInput: { name: "John Doe", age: 25 }) {
    name
    age
  }
}
