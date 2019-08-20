package user

import decode.csv.CsvDecodable

data class User(
    val id: Int,
    val name: String
): CsvDecodable()