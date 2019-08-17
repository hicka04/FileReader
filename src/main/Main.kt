package main

import file.csv.CsvReader
import main.user.UserCsvDecoder

fun main(args : Array<String>) {
    val result = CsvReader(filePath = "res/csv",
                           fileName = "users.csv",
                           decoder = UserCsvDecoder()).readObject()
    result
        .onSuccess { users -> println(users) }
        .onFailure { error -> println(error) }
    println(result.getOrNull())
}