package file.csv

import decode.csv.CsvDecodable
import decode.csv.CsvDecoder
import file.FileReader

abstract class CsvReader<Item: CsvDecodable>(override val filePath: String,
                                                        override val fileName: String,
                                                        override val decoder: CsvDecoder<Item>): FileReader<List<Item>> {

    override fun readObject(): List<Item> {
//        val fileText = readFile().readText()
        // test data
        return decoder.decode("\"1\",\"hoge1\"\n\"2\",\"hoge2\"")
    }
}