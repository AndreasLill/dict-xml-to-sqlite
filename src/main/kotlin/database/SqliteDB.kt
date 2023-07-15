package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SqliteDB {
    fun create() {
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:dictionary.db")
            println("Connected")
            val statement = connection.createStatement()
            statement.queryTimeout = 30
            statement.executeUpdate("DROP TABLE IF EXISTS Entry")
            statement.executeUpdate("CREATE TABLE Entry (ID INT PRIMARY KEY, CommonScore INT)")
            statement.executeUpdate("DROP TABLE IF EXISTS Reading")
            statement.executeUpdate("CREATE TABLE Reading (ID INT PRIMARY KEY, EntryID INT, Kana NVARCHAR(50), Kanji Kana NVARCHAR(50), FOREIGN KEY(EntryID) REFERENCES Entry(ID))")
            println("Tables created")
        } catch(e: SQLException) {
            println(e)
        } finally {
            connection?.let {
                it.close()
                println("Closed")
            }
        }
    }
}