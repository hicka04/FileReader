package file

interface Decoder<Object> {
    fun decode(string: String): Object
}