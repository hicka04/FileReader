package decode.csv

import decode.Decoder
import util.Result
import java.lang.Exception

abstract class CsvDecoder<Item: CsvDecodable>(private val delimiter: String = ",",
                                              private val lineDelimiter: String = "\n",
                                              private val quote: Quote? = Quote.DOUBLE_QUOTE,
                                              private val numberOfHeaderLines: Int = 0): Decoder<List<Item>> {

    enum class Quote(val char: Char) {
        SINGLE_QUOTE('\''),
        DOUBLE_QUOTE('"')
    }

    override fun decode(string: String): Result<List<Item>, Exception> {
        val items = string.split("${quote?.char ?: ""}$lineDelimiter${quote?.char ?: ""}")
                .drop(numberOfHeaderLines)
                .map { columns(it) }
                .mapNotNull { decodeItem(it) }
        return when(items.isNotEmpty()) {
            true -> Result.Success(items)
            false -> Result.Failure(Exception())
        }
    }

    private fun columns(line: String): List<String> {
        return when(quote) {
            null -> line
            else -> line.trim(quote.char)
        }.split("${quote?.char ?: ""}$delimiter${quote?.char ?: ""}")
    }

    abstract fun decodeItem(columns: List<String>): Item?
}
