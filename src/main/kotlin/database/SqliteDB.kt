package database

import model.Entry
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SqliteDB {
    fun create(name: String, items: HashMap<Int, Entry>) {
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$name")
            connection.autoCommit = false
            println("Connected to $name")

            val statement = connection.createStatement()
            statement.queryTimeout = 60

            statement.executeUpdate("DROP TABLE IF EXISTS Entry")
            statement.executeUpdate("CREATE TABLE Entry (ID INTEGER PRIMARY KEY, CommonScore INTEGER)")
            statement.executeUpdate("DROP TABLE IF EXISTS Reading")
            statement.executeUpdate("CREATE TABLE Reading (ID INTEGER PRIMARY KEY AUTOINCREMENT, EntryID INTEGER, Kana NVARCHAR(50), Kanji NVARCHAR(50), FOREIGN KEY(EntryID) REFERENCES Entry(ID))")
            statement.executeUpdate("DROP TABLE IF EXISTS Sense")
            statement.executeUpdate("CREATE TABLE Sense (ID INTEGER PRIMARY KEY AUTOINCREMENT, EntryID INTEGER, Glossary VARCHAR(1000), PartOfSpeech VARCHAR(1000), FOREIGN KEY(EntryID) REFERENCES Entry(ID))")
            connection.commit()
            println("Tables created")

            val batchSize = 500
            var count = 0
            items.values.sortedBy { it.id }.forEach { entry ->
                // Add entry
                statement.addBatch("INSERT INTO Entry VALUES (${entry.id}, ${entry.commonScore})")
                count++

                // Add readings
                entry.reading.forEach { reading ->
                    statement.addBatch("INSERT INTO Reading (EntryID, Kana, Kanji) VALUES (${entry.id}, '${reading.kana}', '${reading.kanji}')")
                    count++
                }

                entry.sense.forEach { sense ->
                    statement.addBatch("INSERT INTO Sense (EntryID, Glossary, PartOfSpeech) VALUES (${entry.id}, '${sense.glossary.joinToString("|", transform = { str -> str.replace("'", "") })}', '${sense.partOfSpeech.joinToString("|")}')")
                    count++
                }

                if (count % batchSize == 0) {
                    statement.executeBatch()
                    statement.clearBatch()
                }
            }
            statement.executeBatch()
            connection.commit()
            println("Inserted all items")
        } catch(e: SQLException) {
            println(e)
        } finally {
            connection?.let {
                it.close()
                println("Connection closed")
            }
        }
    }
}