import database.SqliteDB
import xml.XmlHandler

fun main(args: Array<String>) {
    val items = XmlHandler.parseJMdict("JMdict_e.xml")
    SqliteDB.create("dictionary.db", items)
    println("Done!")
}