package main

import main.user.UserCsvReader

fun main(args : Array<String>) {
    val users = UserCsvReader().readObject()
    print(users)
}