package main.user

import decode.csv.CsvDecoder
import java.lang.Exception

class UserCsvDecoder: CsvDecoder<User>() {

    override fun decodeItem(columns: List<String>): User? {
        return try {
            User(
                id = Integer.parseInt(columns[0]),
                name = columns[1]
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}