package decode

import util.Result

interface Decoder<out Object, out Error: Decoder.DecoderError> {

    fun decode(string: String): Result<Object, Error>

    abstract class DecoderError: Error()
}