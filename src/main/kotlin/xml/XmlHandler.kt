package xml

import model.Entry
import model.Sense
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.FileInputStream

object XmlHandler {
    fun parseJMdict(url: String) : HashMap<Int, Entry> {

        val stream = FileInputStream(url)
        val hashMap = HashMap<Int, Entry>()
        val parserFactory = XmlPullParserFactory.newInstance()
        val parser = parserFactory.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(stream, null)
        XmlReplacement.setEntityReplacementText(parser)
        println("Loaded File: $url")

        var text = ""
        var event = parser.eventType
        var entry = Entry()
        var kanji = XmlKanji()
        var kana = XmlKana()
        var sense = Sense()
        val kanjiList = ArrayList<XmlKanji>()
        val kanaList = ArrayList<XmlKana>()

        while (event != XmlPullParser.END_DOCUMENT) {
            val tag = parser.name
            when (event) {
                XmlPullParser.START_TAG -> when (tag) {
                    "entry" -> entry = Entry()
                    "k_ele" -> kanji = XmlKanji()
                    "r_ele" -> kana = XmlKana()
                    "sense" -> sense = Sense()
                }
                XmlPullParser.TEXT -> text = parser.text
                XmlPullParser.END_TAG -> when (tag) {
                    "ent_seq" -> entry.id = Integer.parseInt(text)
                    // Kanji
                    "keb" -> kanji.value = text
                    "ke_inf" -> kanji.info.add(text)
                    "ke_pri" -> kanji.priority.add(text)
                    "k_ele" -> kanjiList.add(kanji)
                    // Kana
                    "reb" -> kana.value = text
                    "re_nokanji" -> kana.noKanji = true
                    "re_restr" -> kana.restriction.add(text)
                    "re_inf" -> kana.info.add(text)
                    "re_pri" -> kana.priority.add(text).also {
                        if (text.startsWith("nf")) {
                            val score = Integer.valueOf(text.replace("nf", ""))
                            if (entry.commonScore > score)
                                entry.commonScore = score
                        }
                        else if ((text == "spec1" || text == "ichi1")) {
                            if (entry.commonScore > 24)
                                entry.commonScore = 24
                        }
                    }
                    "r_ele" -> kanaList.add(kana)
                    // Sense
                    "pos" -> sense.partOfSpeech.add(XmlReplacement.getPartOfSpeech(text))
                    "field" -> sense.field.add(XmlReplacement.getField(text))
                    "misc" -> sense.misc.add(XmlReplacement.getMisc(text))
                    "s_inf" -> sense.usage.add(text)
                    "dial" -> sense.dialect.add(XmlReplacement.getDialect(text))
                    "gloss" -> sense.glossary.add(text)
                    "sense" -> entry.sense.add(sense)
                    // Add entry to HashMap via id.
                    "entry" -> {
                        entry.reading.addAll(createReadings(kanjiList, kanaList))
                        hashMap[entry.id] = entry
                        kanjiList.clear()
                        kanaList.clear()
                    }
                }
            }
            event = parser.next()
        }

        println("Parsed ${hashMap.size} items")
        return hashMap
    }

    private fun createReadings(kanjiList: List<XmlKanji>, kanaList: List<XmlKana>): ArrayList<Entry.Reading> {
        val readings = ArrayList<Entry.Reading>()

        for (kana in kanaList) {
            if (kana.noKanji || kanjiList.isEmpty()) {
                // Create an empty kanji reading if kana has no kanji, or kanji list is empty.
                val reading = Entry.Reading()
                reading.kana = kana.value
                reading.kanji = ""
                reading.info = kana.info
                reading.priority = kana.priority
                readings.add(reading)
            }
            else {
                if (kana.restriction.isNotEmpty()) {
                    // Add reading to kana by kanji restrictions.
                    for (kanji in kanjiList) {
                        if (kana.restriction.contains(kanji.value)) {
                            val reading = Entry.Reading()
                            reading.kana = kana.value
                            reading.kanji = kanji.value
                            reading.info = kana.info
                            reading.priority = kana.priority
                            readings.add(reading)
                        }
                    }
                }
                else {
                    // Add all kanji readings to kana.
                    for (kanji in kanjiList) {
                        val reading = Entry.Reading()
                        reading.kana = kana.value
                        reading.kanji = kanji.value
                        reading.info = kana.info
                        reading.priority = kana.priority
                        readings.add(reading)
                    }
                }
            }
        }

        if (readings.isEmpty())
            throw Exception("Readings should never be zero.")

        return readings
    }

    // Only used for XML processing
    private class XmlKanji {
        var value : String = ""
        val info : ArrayList<String> = ArrayList()
        val priority : ArrayList<String> = ArrayList()
    }

    // Only used for XML processing
    private class XmlKana {
        var value : String = ""
        var noKanji : Boolean = false
        val info : ArrayList<String> = ArrayList()
        val priority : ArrayList<String> = ArrayList()
        val restriction : ArrayList<String> = ArrayList()
    }
}