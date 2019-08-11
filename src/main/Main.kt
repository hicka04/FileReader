package main

import file.csv.CsvReader
import main.user.UserCsvDecoder

fun main(args : Array<String>) {
    val users = CsvReader(filePath = "res/csv",
                          fileName = "users.csv",
                          decoder = UserCsvDecoder()).readObject()
    print(users)
}