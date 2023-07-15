package database

import model.Entry
import model.Kanji
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SqliteDB {

    private var connection: Connection? = null

    fun connect(name: String) {
        connection = DriverManager.getConnection("jdbc:sqlite:$name")
        connection?.let {
            it.autoCommit = false
        }
    }

    fun close() {
        connection?.let {
            it.close()
        }
    }

    fun createTables() {
        connection?.let {
            try {
                val statement = it.createStatement()
                // Entry
                statement.executeUpdate("DROP TABLE IF EXISTS Entry")
                statement.executeUpdate("CREATE TABLE Entry (ID INTEGER PRIMARY KEY, CommonScore INTEGER)")
                // reading
                statement.executeUpdate("DROP TABLE IF EXISTS Reading")
                statement.executeUpdate("CREATE TABLE Reading (ID INTEGER PRIMARY KEY AUTOINCREMENT, EntryID INTEGER, Kana NVARCHAR(50), Kanji NVARCHAR(50), FOREIGN KEY(EntryID) REFERENCES Entry(ID))")
                statement.executeUpdate("CREATE INDEX IX_Reading_EntryID ON Reading (EntryID)")
                // Sense
                statement.executeUpdate("DROP TABLE IF EXISTS Sense")
                statement.executeUpdate("CREATE TABLE Sense (ID INTEGER PRIMARY KEY AUTOINCREMENT, EntryID INTEGER, Glossary VARCHAR(1000), PartOfSpeech VARCHAR(1000), FOREIGN KEY(EntryID) REFERENCES Entry(ID))")
                statement.executeUpdate("CREATE INDEX IX_Sense_EntryID ON Sense (EntryID)")
                // Kanji
                statement.executeUpdate("DROP TABLE IF EXISTS Kanji")
                statement.executeUpdate("CREATE TABLE Kanji (Character NVARCHAR(1), Meaning VARCHAR(1000))")
                it.commit()
                println("Tables created")
            } catch (e: SQLException) {
                println(e)
            }
        }
    }

    fun insertJMdict(items: HashMap<Int, Entry>) {
        connection?.let {
            try {
            val batchSize = 500
            var count = 0
            val statement = it.createStatement()
            items.values.sortedBy { e -> e.id }.forEach { entry ->
                statement.addBatch("INSERT INTO Entry VALUES (${entry.id}, ${entry.commonScore})")
                count++

                entry.reading.forEach { reading ->
                    val kana = String.format("'%s'", reading.kana)
                    val kanji = if (reading.kanji.isNotEmpty()) String.format("'%s'", reading.kanji) else "NULL"
                    statement.addBatch("INSERT INTO Reading (EntryID, Kana, Kanji) VALUES (${entry.id}, $kana, $kanji)")
                    count++
                }

                entry.sense.forEach { sense ->
                    val glossary = String.format("'%s'", sense.glossary.joinToString("|", transform = { str -> str.replace("'", "") }))
                    val partOfSpeech = String.format("'%s'", sense.partOfSpeech.joinToString("|"))
                    statement.addBatch("INSERT INTO Sense (EntryID, Glossary, PartOfSpeech) VALUES (${entry.id}, $glossary, $partOfSpeech)")
                    count++
                }

                if (count % batchSize == 0) {
                    statement.executeBatch()
                    statement.clearBatch()
                }
            }
            statement.executeBatch()
            it.commit()
            println("Inserted JMdict")
            } catch (e: SQLException) {
                println(e)
            }
        }
    }

    fun insertKanji(items: HashMap<String, Kanji>) {
        connection?.let {
            try {
                val batchSize = 500
                var count = 0
                val statement = it.createStatement()

                items.values.forEach { kanji ->
                    val character = String.format("'%s'", kanji.character)
                    val meaning = String.format("'%s'", kanji.meaning.joinToString("|", transform = { str -> str.replace("'", "") }))
                    statement.addBatch("INSERT INTO Kanji (Character, Meaning) VALUES ($character, $meaning)")
                    count++

                    if (count % batchSize == 0) {
                        statement.executeBatch()
                        statement.clearBatch()
                    }
                }

                statement.executeBatch()
                it.commit()
                println("Inserted Kanji")
            } catch (e: SQLException) {
                println(e)
            }
        }
    }
}