package com.abuarquemf.tunefy.musicmanager.configuration

import java.io.File

object URLhandler {

    var parameters: List<String>? = null

    init {
        val configFile = File("src/com/abuarquemf/tunefy/musicmanager/configuration/tunefy.config")
        parameters = configFile.bufferedReader().readLines()
    }

    fun urlGet() = parameters!![2].replace("GET=", "")

    fun urlPOST() = parameters!![3].replace("POST=", "")

    fun urlPUT() = parameters!![4].replace("PUT=", "")

    fun urlDELETE() = parameters!![5].replace("DELETE=", "")

}
