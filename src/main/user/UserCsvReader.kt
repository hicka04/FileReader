package main.user

import file.csv.CsvReader

class UserCsvReader: CsvReader<User>(
    filePath = "aaa",
    fileName = "bbb",
    decoder = UserCsvDecoder()
)