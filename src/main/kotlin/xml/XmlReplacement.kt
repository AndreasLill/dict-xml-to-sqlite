package xml

import model.Sense.*
import org.xmlpull.v1.XmlPullParser

object XmlReplacement {
    fun getDialect(text: String): Dialect {
        return when (text) {
            "&bra;" -> Dialect.Brazilian
            "&hob;" -> Dialect.Hokkaido
            "&ksb;" -> Dialect.Kansai
            "&ktb;" -> Dialect.Kantou
            "&kyb;" -> Dialect.Kyoto
            "&kyu;" -> Dialect.Kyuushuu
            "&nab;" -> Dialect.Nagano
            "&osb;" -> Dialect.Osaka
            "&rkb;" -> Dialect.Ryuukyuu
            "&thb;" -> Dialect.Touhoku
            "&tsb;" -> Dialect.Tosa
            "&tsug;" -> Dialect.Tsugaru
            else -> Dialect.Unclassified
        }
    }

    fun getField(text: String): Field {
        return when (text) {
            "&agric;" -> Field.Agriculture
            "&anat;" -> Field.Anatomy
            "&archeol;" -> Field.Archeology
            "&archit;" -> Field.Architecture
            "&art;" -> Field.Art
            "&astron;" -> Field.Astronomy
            "&audvid;" -> Field.Audiovisual
            "&aviat;" -> Field.Aviation
            "&baseb;" -> Field.Baseball
            "&biochem;" -> Field.Biochemistry
            "&biol;" -> Field.Biology
            "&bot;" -> Field.Botany
            "&Buddh;" -> Field.Buddhism
            "&bus;" -> Field.Business
            "&chem;" -> Field.Chemistry
            "&Christn;" -> Field.Christianity
            "&cloth;" -> Field.Clothing
            "&comp;" -> Field.Computing
            "&cryst;" -> Field.Crystallography
            "&ecol;" -> Field.Ecology
            "&econ;" -> Field.Economics
            "&elec;" -> Field.Electricity
            "&electr;" -> Field.Electronics
            "&embryo;" -> Field.Embryology
            "&engr;" -> Field.Engineering
            "&ent;" -> Field.Entomology
            "&finc;" -> Field.Finance
            "&fish;" -> Field.Fishing
            "&food;" -> Field.Food
            "&gardn;" -> Field.Gardening
            "&genet;" -> Field.Genetics
            "&geogr;" -> Field.Geography
            "&geol;" -> Field.Geology
            "&geom;" -> Field.Geometry
            "&golf;" -> Field.Golf
            "&gramm;" -> Field.Grammar
            "&grmyth;" -> Field.GreekMythology
            "&horse;" -> Field.HorseRacing
            "&law;" -> Field.Law
            "&ling;" -> Field.Linguistics
            "&logic;" -> Field.Logic
            "&MA;" -> Field.MartialArts
            "&mahj;" -> Field.Mahjong
            "&math;" -> Field.Mathematics
            "&mech;" -> Field.MechanicalEngineering
            "&med;" -> Field.Medicine
            "&met;" -> Field.Meteorology
            "&mil;" -> Field.Military
            "&music;" -> Field.Music
            "&ornith;" -> Field.Ornithology
            "&paleo;" -> Field.Paleontology
            "&pathol;" -> Field.Pathology
            "&pharm;" -> Field.Pharmacy
            "&phil;" -> Field.Philosophy
            "&photo;" -> Field.Photography
            "&physics;" -> Field.Physics
            "&physiol;" -> Field.Physiology
            "&print;" -> Field.Printing
            "&psy;" -> Field.Psychiatry
            "&psych;" -> Field.Psychology
            "&rail;" -> Field.Railway
            "&Shinto;" -> Field.Shinto
            "&shogi;" -> Field.Shogi
            "&sports;" -> Field.Sports
            "&stat;" -> Field.Statistics
            "&sumo;" -> Field.Sumo
            "&telec;" -> Field.Telecommunications
            "&tradem;" -> Field.Trademark
            "&vidg;" -> Field.VideoGames
            "&zool;" -> Field.Zoology
            else -> Field.Unclassified
        }
    }

    fun getMisc(text: String): Misc {
        return when (text) {
            "&abbr;" -> Misc.Abbreviation
            "&arch;" -> Misc.Archaism
            "&char;" -> Misc.Character
            "&chn;" -> Misc.Children
            "&col;" -> Misc.Colloquialism
            "&company;" -> Misc.Company
            "&creat;" -> Misc.Creature
            "&dated;" -> Misc.Dated
            "&dei;" -> Misc.Deity
            "&derog;" -> Misc.Derogatory
            "&doc;" -> Misc.Document
            "&ev;" -> Misc.Event
            "&fam;" -> Misc.Familiar
            "&fem;" -> Misc.Female
            "&fict;" -> Misc.Fiction
            "&form;" -> Misc.Formal
            "&given;" -> Misc.Given
            "&group;" -> Misc.Group
            "&hist;" -> Misc.Historical
            "&hon;" -> Misc.Honorific
            "&hum;" -> Misc.Humble
            "&id;" -> Misc.Idiomatic
            "&joc;" -> Misc.Jocular
            "&leg;" -> Misc.Legend
            "&m-sl;" -> Misc.Manga
            "&male;" -> Misc.Male
            "&myth;" -> Misc.Mythology
            "&net-sl;" -> Misc.Internet
            "&obj;" -> Misc.Object
            "&obs;" -> Misc.Obsolete
            "&obsc;" -> Misc.Obscure
            "&on-mim;" -> Misc.Onomatopoeic
            "&organization;" -> Misc.Organization
            "&oth;" -> Misc.Other
            "&person;" -> Misc.Person
            "&place;" -> Misc.Place
            "&poet;" -> Misc.Poet
            "&pol;" -> Misc.Polite
            "&product;" -> Misc.Product
            "&proverb;" -> Misc.Proverb
            "&quote;" -> Misc.Quote
            "&rare;" -> Misc.Rare
            "&relig;" -> Misc.Religion
            "&sens;" -> Misc.Sensitive
            "&serv;" -> Misc.Service
            "&sl;" -> Misc.Slang
            "&station;" -> Misc.Station
            "&surname;" -> Misc.Surname
            "&uk;" -> Misc.Kana
            "&vulg;" -> Misc.Vulgar
            "&work;" -> Misc.Work
            "&yoji;" -> Misc.Yoji
            else -> Misc.Unclassified
        }
    }

    fun getPartOfSpeech(text: String): PartOfSpeech {
        return when {
            text == "&adj-i;" || text == "&adj-ix;" || text == "&adj-t;" -> PartOfSpeech.Adjective
            text == "&adj-na;" -> PartOfSpeech.AdjectiveNa
            text == "&adj-no;" -> PartOfSpeech.AdjectiveNo
            text.startsWith("&adv") -> PartOfSpeech.Adverb
            text == "&aux-adj;" -> PartOfSpeech.AdjectiveAux
            text == "&aux-v;" -> PartOfSpeech.VerbAux
            text == "&conj;" -> PartOfSpeech.Conjunction
            text == "&cop;" -> PartOfSpeech.Copula
            text == "&ctr;" -> PartOfSpeech.Counter
            text == "&exp;" -> PartOfSpeech.Expression
            text == "&int;" -> PartOfSpeech.Interjection
            text == "&n;" -> PartOfSpeech.Noun
            text == "&n-adv;" -> PartOfSpeech.NounAdverbial
            text == "&n-pr;" -> PartOfSpeech.NounProper
            text == "&n-pref;" -> PartOfSpeech.NounPrefix
            text == "&n-suf;" -> PartOfSpeech.NounSuffix
            text == "&n-t;" -> PartOfSpeech.NounTemporal
            text == "&num;" -> PartOfSpeech.Numeric
            text == "&pn;" -> PartOfSpeech.Pronoun
            text == "&pref;" -> PartOfSpeech.Prefix
            text == "&suf;" -> PartOfSpeech.Suffix
            text == "&v-unspec;" -> PartOfSpeech.VerbUnspecified
            text.startsWith("&v1") -> PartOfSpeech.VerbIchidan
            text.startsWith("&v2") -> PartOfSpeech.VerbNidan
            text.startsWith("&v4") -> PartOfSpeech.VerbYodan
            text.startsWith("&v5") -> PartOfSpeech.VerbGodan
            text == "&vi;" -> PartOfSpeech.VerbIntransitive
            text == "&vk;" -> PartOfSpeech.VerbKuru
            text == "&vn;" || text == "&vr;" || text == "&vs;" || text == "&vs-s;" -> PartOfSpeech.VerbIrregular
            text == "&vs-i;" -> PartOfSpeech.VerbSuru
            text == "&vt;" -> PartOfSpeech.VerbTransitive
            else -> PartOfSpeech.Unclassified
        }
    }

    // JMdict Entity Replacement Definitions.
    @Suppress("SpellCheckingInspection")
    fun setEntityReplacementText(parser: XmlPullParser) {
        // <dial>
        parser.defineEntityReplacementText("hob", "&hob;")
        parser.defineEntityReplacementText("ksb", "&ksb;")
        parser.defineEntityReplacementText("ktb", "&ktb;")
        parser.defineEntityReplacementText("kyb", "&kyb;")
        parser.defineEntityReplacementText("kyu", "&kyu;")
        parser.defineEntityReplacementText("nab", "&nab;")
        parser.defineEntityReplacementText("osb", "&osb;")
        parser.defineEntityReplacementText("rkb", "&rkb;")
        parser.defineEntityReplacementText("thb", "&thb;")
        parser.defineEntityReplacementText("tsb", "&tsb;")
        parser.defineEntityReplacementText("tsug", "&tsug;")
        // <field>
        parser.defineEntityReplacementText("agric", "&agric;")
        parser.defineEntityReplacementText("anat", "&anat;")
        parser.defineEntityReplacementText("archeol", "&archeol;")
        parser.defineEntityReplacementText("archit", "&archit;")
        parser.defineEntityReplacementText("art", "&art;")
        parser.defineEntityReplacementText("astron", "&astron;")
        parser.defineEntityReplacementText("audvid", "&audvid;")
        parser.defineEntityReplacementText("aviat", "&aviat;")
        parser.defineEntityReplacementText("baseb", "&baseb;")
        parser.defineEntityReplacementText("biochem", "&biochem;")
        parser.defineEntityReplacementText("biol", "&biol;")
        parser.defineEntityReplacementText("bot", "&bot;")
        parser.defineEntityReplacementText("Buddh", "&Buddh;")
        parser.defineEntityReplacementText("bus", "&bus;")
        parser.defineEntityReplacementText("chem", "&chem;")
        parser.defineEntityReplacementText("Christn", "&Christn;")
        parser.defineEntityReplacementText("comp", "&comp;")
        parser.defineEntityReplacementText("cryst", "&cryst;")
        parser.defineEntityReplacementText("ecol", "&ecol;")
        parser.defineEntityReplacementText("econ", "&econ;")
        parser.defineEntityReplacementText("elec", "&elec;")
        parser.defineEntityReplacementText("electr", "&electr;")
        parser.defineEntityReplacementText("embryo", "&embryo;")
        parser.defineEntityReplacementText("engr", "&engr;")
        parser.defineEntityReplacementText("ent", "&ent;")
        parser.defineEntityReplacementText("finc", "&finc;")
        parser.defineEntityReplacementText("fish", "&fish;")
        parser.defineEntityReplacementText("food", "&food;")
        parser.defineEntityReplacementText("gardn", "&gardn;")
        parser.defineEntityReplacementText("genet", "&genet;")
        parser.defineEntityReplacementText("geogr", "&geogr;")
        parser.defineEntityReplacementText("geol", "&geol;")
        parser.defineEntityReplacementText("geom", "&geom;")
        parser.defineEntityReplacementText("go", "&go;")
        parser.defineEntityReplacementText("golf", "&golf;")
        parser.defineEntityReplacementText("gramm", "&gramm;")
        parser.defineEntityReplacementText("grmyth", "&grmyth;")
        parser.defineEntityReplacementText("hanaf", "&hanaf;")
        parser.defineEntityReplacementText("horse", "&horse;")
        parser.defineEntityReplacementText("law", "&law;")
        parser.defineEntityReplacementText("ling", "&ling;")
        parser.defineEntityReplacementText("logic", "&logic;")
        parser.defineEntityReplacementText("MA", "&MA;")
        parser.defineEntityReplacementText("mahj", "&mahj;")
        parser.defineEntityReplacementText("math", "&math;")
        parser.defineEntityReplacementText("mech", "&mech;")
        parser.defineEntityReplacementText("med", "&med;")
        parser.defineEntityReplacementText("met", "&met;")
        parser.defineEntityReplacementText("mil", "&mil;")
        parser.defineEntityReplacementText("music", "&music;")
        parser.defineEntityReplacementText("ornith", "&ornith;")
        parser.defineEntityReplacementText("paleo", "&paleo;")
        parser.defineEntityReplacementText("pathol", "&pathol;")
        parser.defineEntityReplacementText("pharm", "&pharm;")
        parser.defineEntityReplacementText("phil", "&phil;")
        parser.defineEntityReplacementText("photo", "&photo;")
        parser.defineEntityReplacementText("physics", "&physics;")
        parser.defineEntityReplacementText("physiol", "&physiol;")
        parser.defineEntityReplacementText("print", "&print;")
        parser.defineEntityReplacementText("psych", "&psych;")
        parser.defineEntityReplacementText("Shinto", "&Shinto;")
        parser.defineEntityReplacementText("shogi", "&shogi;")
        parser.defineEntityReplacementText("sports", "&sports;")
        parser.defineEntityReplacementText("stat", "&stat;")
        parser.defineEntityReplacementText("sumo", "&sumo;")
        parser.defineEntityReplacementText("telec", "&telec;")
        parser.defineEntityReplacementText("tradem", "&tradem;")
        parser.defineEntityReplacementText("vidg", "&vidg;")
        parser.defineEntityReplacementText("zool", "&zool;")
        // <re_inf>
        parser.defineEntityReplacementText("gikun", "&gikun;")
        parser.defineEntityReplacementText("ik", "&ik;")
        parser.defineEntityReplacementText("ok", "&ok;")
        parser.defineEntityReplacementText("uK", "&uK;")
        // <ke_inf>
        parser.defineEntityReplacementText("ateji", "&ateji;")
        parser.defineEntityReplacementText("ik", "&ik;")
        parser.defineEntityReplacementText("iK", "&iK;")
        parser.defineEntityReplacementText("io", "&io;")
        parser.defineEntityReplacementText("oK", "&oK;")
        // <misc>
        parser.defineEntityReplacementText("abbr", "&abbr;")
        parser.defineEntityReplacementText("arch", "&arch;")
        parser.defineEntityReplacementText("char", "&char;")
        parser.defineEntityReplacementText("chn", "&chn;")
        parser.defineEntityReplacementText("col", "&col;")
        parser.defineEntityReplacementText("company", "&company;")
        parser.defineEntityReplacementText("creat", "&creat;")
        parser.defineEntityReplacementText("dated", "&dated;")
        parser.defineEntityReplacementText("dei", "&dei;")
        parser.defineEntityReplacementText("derog", "&derog;")
        parser.defineEntityReplacementText("ev", "&ev;")
        parser.defineEntityReplacementText("fam", "&fam;")
        parser.defineEntityReplacementText("fem", "&fem;")
        parser.defineEntityReplacementText("fict", "&fict;")
        parser.defineEntityReplacementText("given", "&given;")
        parser.defineEntityReplacementText("hist", "&hist;")
        parser.defineEntityReplacementText("hon", "&hon;")
        parser.defineEntityReplacementText("hum", "&hum;")
        parser.defineEntityReplacementText("id", "&id;")
        parser.defineEntityReplacementText("joc", "&joc;")
        parser.defineEntityReplacementText("leg", "&leg;")
        parser.defineEntityReplacementText("litf", "&litf;")
        parser.defineEntityReplacementText("m-sl", "&m-sl;")
        parser.defineEntityReplacementText("male", "&male;")
        parser.defineEntityReplacementText("myth", "&myth;")
        parser.defineEntityReplacementText("net-sl", "&net-sl;")
        parser.defineEntityReplacementText("obj", "&obj;")
        parser.defineEntityReplacementText("obs", "&obs;")
        parser.defineEntityReplacementText("obsc", "&obsc;")
        parser.defineEntityReplacementText("on-mim", "&on-mim;")
        parser.defineEntityReplacementText("organization", "&organization;")
        parser.defineEntityReplacementText("oth", "&oth;")
        parser.defineEntityReplacementText("person", "&person;")
        parser.defineEntityReplacementText("place", "&place;")
        parser.defineEntityReplacementText("poet", "&poet;")
        parser.defineEntityReplacementText("pol", "&pol;")
        parser.defineEntityReplacementText("product", "&product;")
        parser.defineEntityReplacementText("proverb", "&proverb;")
        parser.defineEntityReplacementText("quote", "&quote;")
        parser.defineEntityReplacementText("rare", "&rare;")
        parser.defineEntityReplacementText("relig", "&relig;")
        parser.defineEntityReplacementText("sens", "&sens;")
        parser.defineEntityReplacementText("serv", "&serv;")
        parser.defineEntityReplacementText("sl", "&sl;")
        parser.defineEntityReplacementText("station", "&station;")
        parser.defineEntityReplacementText("surname", "&surname;")
        parser.defineEntityReplacementText("uk", "&uk;")
        parser.defineEntityReplacementText("unclass", "&unclass;")
        parser.defineEntityReplacementText("vulg", "&vulg;")
        parser.defineEntityReplacementText("work", "&work;")
        parser.defineEntityReplacementText("X", "&X;")
        parser.defineEntityReplacementText("yoji", "&yoji;")
        // <pos>
        parser.defineEntityReplacementText("adj-f", "&adj-f;")
        parser.defineEntityReplacementText("adj-i", "&adj-i;")
        parser.defineEntityReplacementText("adj-ix", "&adj-ix;")
        parser.defineEntityReplacementText("adj-kari", "&adj-kari;")
        parser.defineEntityReplacementText("adj-ku", "&adj-ku;")
        parser.defineEntityReplacementText("adj-na", "&adj-na;")
        parser.defineEntityReplacementText("adj-nari", "&adj-nari;")
        parser.defineEntityReplacementText("adj-no", "&adj-no;")
        parser.defineEntityReplacementText("adj-pn", "&adj-pn;")
        parser.defineEntityReplacementText("adj-shiku", "&adj-shiku;")
        parser.defineEntityReplacementText("adj-t", "&adj-t;")
        parser.defineEntityReplacementText("adv", "&adv;")
        parser.defineEntityReplacementText("adv-to", "&adv-to;")
        parser.defineEntityReplacementText("aux", "&aux;")
        parser.defineEntityReplacementText("aux-adj", "&aux-adj;")
        parser.defineEntityReplacementText("aux-v", "&aux-v;")
        parser.defineEntityReplacementText("conj", "&conj;")
        parser.defineEntityReplacementText("cop", "&cop;")
        parser.defineEntityReplacementText("ctr", "&ctr;")
        parser.defineEntityReplacementText("exp", "&exp;")
        parser.defineEntityReplacementText("int", "&int;")
        parser.defineEntityReplacementText("n", "&n;")
        parser.defineEntityReplacementText("n-adv", "&n-adv;")
        parser.defineEntityReplacementText("n-pr", "&n-pr;")
        parser.defineEntityReplacementText("n-pref", "&n-pref;")
        parser.defineEntityReplacementText("n-suf", "&n-suf;")
        parser.defineEntityReplacementText("n-t", "&n-t;")
        parser.defineEntityReplacementText("num", "&num;")
        parser.defineEntityReplacementText("pn", "&pn;")
        parser.defineEntityReplacementText("pref", "&pref;")
        parser.defineEntityReplacementText("prt", "&prt;")
        parser.defineEntityReplacementText("suf", "&suf;")
        parser.defineEntityReplacementText("unc", "&unc;")
        parser.defineEntityReplacementText("v-unspec", "&v-unspec;")
        parser.defineEntityReplacementText("v1", "&v1;")
        parser.defineEntityReplacementText("v1-s", "&v1-s;")
        parser.defineEntityReplacementText("v2a-s", "&v2a-s;")
        parser.defineEntityReplacementText("v2b-k", "&v2b-k;")
        parser.defineEntityReplacementText("v2b-s", "&v2b-s;")
        parser.defineEntityReplacementText("v2d-k", "&v2d-k;")
        parser.defineEntityReplacementText("v2d-s", "&v2d-s;")
        parser.defineEntityReplacementText("v2g-k", "&v2g-k;")
        parser.defineEntityReplacementText("v2g-s", "&v2g-s;")
        parser.defineEntityReplacementText("v2h-k", "&v2h-k;")
        parser.defineEntityReplacementText("v2h-s", "&v2h-s;")
        parser.defineEntityReplacementText("v2k-k", "&v2k-k;")
        parser.defineEntityReplacementText("v2k-s", "&v2k-s;")
        parser.defineEntityReplacementText("v2m-k", "&v2m-k;")
        parser.defineEntityReplacementText("v2m-s", "&v2m-s;")
        parser.defineEntityReplacementText("v2n-s", "&v2n-s;")
        parser.defineEntityReplacementText("v2r-k", "&v2r-k;")
        parser.defineEntityReplacementText("v2r-s", "&v2r-s;")
        parser.defineEntityReplacementText("v2s-s", "&v2s-s;")
        parser.defineEntityReplacementText("v2t-k", "&v2t-k;")
        parser.defineEntityReplacementText("v2t-s", "&v2t-s;")
        parser.defineEntityReplacementText("v2w-s", "&v2w-s;")
        parser.defineEntityReplacementText("v2y-k", "&v2y-k;")
        parser.defineEntityReplacementText("v2y-s", "&v2y-s;")
        parser.defineEntityReplacementText("v2z-s", "&v2z-s;")
        parser.defineEntityReplacementText("v4b", "&v4b;")
        parser.defineEntityReplacementText("v4g", "&v4g;")
        parser.defineEntityReplacementText("v4h", "&v4h;")
        parser.defineEntityReplacementText("v4k", "&v4k;")
        parser.defineEntityReplacementText("v4m", "&v4m;")
        parser.defineEntityReplacementText("v4n", "&v4n;")
        parser.defineEntityReplacementText("v4r", "&v4r;")
        parser.defineEntityReplacementText("v4s", "&v4s;")
        parser.defineEntityReplacementText("v4t", "&v4t;")
        parser.defineEntityReplacementText("v5aru", "&v5aru;")
        parser.defineEntityReplacementText("v5b", "&v5b;")
        parser.defineEntityReplacementText("v5g", "&v5g;")
        parser.defineEntityReplacementText("v5k", "&v5k;")
        parser.defineEntityReplacementText("v5k-s", "&v5k-s;")
        parser.defineEntityReplacementText("v5m", "&v5m;")
        parser.defineEntityReplacementText("v5n", "&v5n;")
        parser.defineEntityReplacementText("v5r", "&v5r;")
        parser.defineEntityReplacementText("v5r-i", "&v5r-i;")
        parser.defineEntityReplacementText("v5s", "&v5s;")
        parser.defineEntityReplacementText("v5t", "&v5t;")
        parser.defineEntityReplacementText("v5u", "&v5u;")
        parser.defineEntityReplacementText("v5u-s", "&v5u-s;")
        parser.defineEntityReplacementText("v5uru", "&v5uru;")
        parser.defineEntityReplacementText("vi", "&vi;")
        parser.defineEntityReplacementText("vk", "&vk;")
        parser.defineEntityReplacementText("vn", "&vn;")
        parser.defineEntityReplacementText("vr", "&vr;")
        parser.defineEntityReplacementText("vs", "&vs;")
        parser.defineEntityReplacementText("vs-c", "&vs-c;")
        parser.defineEntityReplacementText("vs-i", "&vs-i;")
        parser.defineEntityReplacementText("vs-s", "&vs-s;")
        parser.defineEntityReplacementText("vt", "&vt;")
        parser.defineEntityReplacementText("vz", "&vz;")
    }
}