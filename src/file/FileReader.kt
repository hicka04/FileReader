package file

import java.io.File

interface FileReader<Object> {

    val filePath: String
    val fileName: String
    val decoder: Decoder<Object>

    fun readFile(): File {
        return File("$filePath/$fileName")
    }

    fun readObject(): Object
}