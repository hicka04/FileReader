import file.csv.CsvReader
import user.UserCsvDecoder

fun main(args: Array<String>) {
    val result = CsvReader(
        filePath = "src/main/resources/csv",
        fileName = "users.csv",
        decoder = UserCsvDecoder()
    ).readObject()
    result
        .onSuccess { users -> println(users) }
        .onFailure { error -> println(error) }
    println(result.getOrNull())
}