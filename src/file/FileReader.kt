package file

import decode.Decoder
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
            Result.Success(decoder.decode(fileText))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}

sealed class Result<T, Exception: java.lang.Exception> {
    data class Success<T, Exception: java.lang.Exception>(val value: T): Result<T, Exception>()
    data class Failure<T, Exception: java.lang.Exception>(val error: Exception): Result<T, Exception>()

    fun onSuccess(onSuccess: (T) -> Unit): Result<T, Exception> {
        when(this) {
            is Success -> onSuccess(this.value)
        }
        return this
    }
    
    fun onFailure(onFailure: (Exception) -> Unit): Result<T, Exception> {
        when(this) {
            is Failure -> onFailure(this.error)
        }
        return this
    }
}