package main

import file.csv.CsvReader
import main.user.UserCsvDecoder

fun main(args : Array<String>) {
    val users = CsvReader(filePath = "aaa",
                          fileName = "bbb",
                          decoder = UserCsvDecoder()).readObject()
    print(users)
}