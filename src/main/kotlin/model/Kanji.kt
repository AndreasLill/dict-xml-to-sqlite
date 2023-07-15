package model

class Kanji {
    var character : String = ""
    var grade : Int = 0
    var stroke : Int = 0
    var freq : Int = 0
    var jlpt : Int = 0
    val reading : ArrayList<Reading> = ArrayList()
    val meaning : ArrayList<String> = ArrayList()

    class Reading {
        var value: String = ""
        var type: String = ""
    }
}