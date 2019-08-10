package file.csv

import decode.csv.CsvDecodable
import decode.csv.CsvDecoder
import file.FileReader

class CsvReader<Item: CsvDecodable>(override val filePath: String,
                                    override val fileName: String,
                                    override val decoder: CsvDecoder<Item>): FileReader<List<Item>> {

    override fun readObject(): List<Item> {
//        val fileText = readFile().readText()
        // test data
        return decoder.decode("""
            "1","hog,e1
            hoge"
            "2","ho"ge2"
        """.trimIndent())
    }
}