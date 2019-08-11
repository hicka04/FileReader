package decode.csv

import decode.Decoder

abstract class CsvDecoder<Item: CsvDecodable>(private val delimiter: String = ",",
                                              private val lineDelimiter: String = "\n",
                                              private val quote: Quote? = Quote.DOUBLE_QUOTE,
                                              private val numberOfHeaderLines: Int = 0): Decoder<List<Item>> {

    enum class Quote(val char: Char) {
        SINGLE_QUOTE('\''),
        DOUBLE_QUOTE('"')
    }

    override fun decode(string: String): List<Item> {
        return string.split("${quote?.char ?: ""}$lineDelimiter${quote?.char ?: ""}")
                .drop(numberOfHeaderLines)
                .map { columns(it) }
                .mapNotNull { decodeItem(it) }
    }

    private fun columns(line: String): List<String> {
        return when(quote) {
            null -> line
            else -> line.trim(quote.char)
        }.split("${quote?.char ?: ""}$delimiter${quote?.char ?: ""}")
    }

    abstract fun decodeItem(columns: List<String>): Item?
}
