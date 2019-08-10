package decode.csv

import decode.Decoder

abstract class CsvDecoder<Item: CsvDecodable>(private val delimiter: String = ",",
                                                         private val lineDelimiter: String = "\n",
                                                         private val quote: String = "\"",
                                                         private val numberOfHeaderLines: Int = 0): Decoder<List<Item>> {

    override fun decode(string: String): List<Item> {
        return string.split(lineDelimiter)
                .drop(numberOfHeaderLines)
                .map { columns(it) }
                .mapNotNull { decodeItem(it) }
    }

    private fun columns(line: String): List<String> {
        return line.split(delimiter).map {
            it.replaceFirst("^$quote", "")
                    .replaceFirst("$quote$", "")
        }
    }

    abstract fun decodeItem(columns: List<String>): Item?
}