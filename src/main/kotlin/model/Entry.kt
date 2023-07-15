package model

class Entry {
    var id : Int = 0
    var commonScore: Int = 50
    val reading : ArrayList<Reading> = ArrayList()
    val sense : ArrayList<Sense> = ArrayList()

    class Reading {
        var kana : String = ""
        var kanji : String = ""
        var info : ArrayList<String> = ArrayList()
        var priority : ArrayList<String> = ArrayList()
    }
}