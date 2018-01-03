package com.abuarquemf.tunefy.musicmanager

import com.abuarquemf.tunefy.musicmanager.configuration.URLhandler
import com.abuarquemf.tunefy.musicmanager.connectionhandler.RestHandler
import com.abuarquemf.tunefy.musicmanager.models.Music
import com.abuarquemf.tunefy.musicmanager.streamhandler.ResourceStreamUtil
import com.abuarquemf.tunefy.musicmanager.streamhandler.SerializationUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val echo = RestHandler.getInstance().doGet(URLhandler.urlGet() + "1514951817")
        val tune = Gson().fromJson<Music>(echo, object: TypeToken<Music>(){}.type)

        val handler = ResourceStreamUtil()

        val decompressed = handler.decompressResource(tune.musicResource)
        val tempFile = handler.getTemporaryFileFromBytes(decompressed, "eita", ".mp3")

        handler.createResourceFile(decompressed, "caralho.mp3")
    }
}
