package file

import decode.Decoder
import util.Result
import java.io.File
import kotlin.Exception

interface FileReader<out Object> {

    val filePath: String
    val fileName: String
    val decoder: Decoder<Object, Decoder.DecoderError>

    private fun readFile(): File? {
        return try {
            File("$filePath/$fileName")
        } catch(e: Exception) {
            null
        }
    }

    fun readObject(): Result<Object, FileReaderError> {
        val fileText = readFile()?.readText()
        fileText ?: return Result.Failure(FileReaderError.FileNotFoundError)

        return decoder.decode(fileText).mapError { FileReaderError.FileDecodeError(it) }
    }

    sealed class FileReaderError: Error() {
        object FileNotFoundError: FileReaderError()
        data class FileDecodeError(val decoderError: Decoder.DecoderError): FileReaderError()
    }
}