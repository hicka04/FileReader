package file

import decode.Decoder
import util.Result
import java.io.File
import java.lang.Exception

interface FileReader<Object> {

    val filePath: String
    val fileName: String
    val decoder: Decoder<Object>

    private fun readFile(): File {
        return File("$filePath/$fileName")
    }

    fun readObject(): Result<Object, Exception> {
        return try {
            val fileText = readFile().readText()
            decoder.decode(fileText)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}