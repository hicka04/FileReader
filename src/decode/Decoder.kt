package decode

interface Decoder<Object> {
    fun decode(string: String): Object
}