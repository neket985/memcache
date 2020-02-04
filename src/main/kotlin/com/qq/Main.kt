package com.qq

import de.ailis.pherialize.Pherialize
import java.io.IOException
import net.rubyeye.xmemcached.XMemcachedClient
import net.rubyeye.xmemcached.transcoders.*


object Main {
    @JvmStatic
    fun main(args: Array<String>) {

        val test = Test("qq", 1234, false)
        val q = Pherialize.serialize(test).toByteArray()
//        val q = "a:2:{i:0;i:11;i:1;i:12;}".toByteArray()
        try {
            val client = XMemcachedClient("localhost", 11211)
            client.transcoder = object: PrimitiveTypeTranscoder<ByteArray>(){
                override fun encode(o: ByteArray): CachedData = CachedData(1, o)

                override fun decode(d: CachedData): ByteArray = d.data
            }

            client.set("match", 3600, q)
            val q1 = String(client.get<ByteArray>("match"))
            println(q1)

        } catch (e: IOException) {
            // Prints this throwable and its backtrace to the standard error stream
            e.printStackTrace()
        }


    }

    data class Test(
            val qwe: String,
            val fgh: Int,
            val mnb: Boolean
    ): java.io.Serializable {

    }

    val str =
            """
array(1) {
  [0]=>
  array(63) {
    ["sportName"]=>
    string(33) "Привет"
    ["champName"]=>
    string(38) "Земляне"
    ["matchname"]=>
    string(34) "куку"
    ["sportId"]=>
    int(40)
    ["matchid"]=>
    int(55158693)
    ["champid"]=>
    int(4971)
    ["mResult"]=>
    string(20) "0:1@  (5:11, 2:1) #1"
    ["maxbet"]=>
    int(20000)
    ["maxmisc"]=>
    int(20000)
    ["champcom"]=>
    string(0) ""
    ["matchcom"]=>
    string(0) ""
    ["mstate"]=>
    int(2)
    ["sportordr"]=>
    int(11)
    ["dt"]=>
    string(10) "03.02.2020"
    ["tm"]=>
    string(8) "23:00:00"
    ["id"]=>
    int(1448864318)
    ["value1"]=>
    float(2.07)
    ["value2"]=>
    float(1)
    ["value3"]=>
    float(1)
    ["shSrcName"]=>
    string(7) "BConstr"
    ["evname1"]=>
    string(25) "хаха"
    ["evname2"]=>
    string(0) ""
    ["evname3"]=>
    string(0) ""
    ["shName1"]=>
    string(3) "лол"
    ["shName2"]=>
    string(0) ""
    ["shName3"]=>
    string(0) ""
    ["catname"]=>
    string(16) "кек"
    ["main"]=>
    bool(true)
    ["event_type"]=>
    int(1)
    ["catordr"]=>
    int(1)
    ["eventid"]=>
    int(1448864318)
    ["mNum"]=>
    int(109)
    ["linedataid"]=>
    int(55158693)
    ["ordr"]=>
    int(1)
    ["origin_name"]=>
    string(2) "Ï1"
    ["video_id"]=>
    int(901162607)
    ["brid"]=>
    int(0)
    ["roul"]=>
    int(0)
    ["isvss"]=>
    int(0)
    ["siteallow"]=>
    string(50) ":KZ::mobileKZ::appIOSKZ::appAndroidKZ::appAndroidK"
    ["sitematch"]=>
    string(199) "901162607-KZ;901162607-mobileKZ;901162607-appIOSKZ;901162607-appAndroidKZ;901162607-appAndroidKZOfs;901162607-COM;901162607-mobileCOM;901162607-appIOS;901162607-appIOSRUOfs;901162607-appAndroidRUOfs;"
    ["upd_time"]=>
    int(1)
    ["cat_bet_group"]=>
    int(0)
    ["market"]=>
    int(2)
    ["selection"]=>
    int(1)
    ["playerid"]=>
    int(0)
    ["Team1Ext"]=>
    int(0)
    ["Team2Ext"]=>
    int(0)
    ["mResultLong"]=>
    string(0) ""
    ["unordered"]=>
    int(0)
    ["col_count"]=>
    int(2)
    ["sel_count"]=>
    int(2)
    ["market_grp_id"]=>
    string(10) "8589934592"
    ["is_match_test"]=>
    int(0)
    ["period"]=>
    int(0)
    ["live_delay"]=>
    int(0)
    ["block_cashout"]=>
    int(1)
    ["Team1Country"]=>
    int(10)
    ["Team2Country"]=>
    int(2)
    ["isCashOut"]=>
    int(1)
    ["line"]=>
    string(8) "-9999.00"
    ["turn_table"]=>
    int(1)
    ["spec_offer"]=>
    int(0)
  }
}
            """
}