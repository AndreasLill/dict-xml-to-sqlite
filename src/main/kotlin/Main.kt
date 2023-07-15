import database.SqliteDB
import xml.XmlHandler

fun main(args: Array<String>) {
    val entries = XmlHandler.parseJMdict("JMdict_e.xml")
    val kanji = XmlHandler.parseKanji("kanjidic2.xml")
    SqliteDB.connect("dictionary.db")
    SqliteDB.createTables()
    SqliteDB.insertJMdict(entries)
    SqliteDB.insertKanji(kanji)
    SqliteDB.close()
    println("Done!")
}