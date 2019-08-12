package decode

import util.Result
import java.lang.Exception

interface Decoder<Object> {
    fun decode(string: String): Result<Object, Exception>
}