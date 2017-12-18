package com.abuarquemf.tunefy.desktopapp.controllers

import com.abuarquemf.tunefy.desktopapp.connectionhandler.RestHandler
import com.abuarquemf.tunefy.desktopapp.streamhandler.SerializationUtils
import com.abuarquemf.tunefymusicapi.models.Music
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.concurrent.Task
import javafx.event.Event
import javafx.fxml.FXML
import javafx.concurrent.WorkerStateEvent
import javafx.event.EventHandler
import java.io.IOException
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.nio.file.Files


class MainController {

    fun createResourceFile(resourceAsBytes: ByteArray, resourceName: String) {
        try {
            FileOutputStream(resourceName).use { fos ->
                fos.write(resourceAsBytes)
                fos.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @FXML
    fun downloadTune(event: Event) {

        val task = object : Task<Music>() {

            override fun call(): Music {
                val echo =  RestHandler.getInstance()
                        .doGet("http://localhost:8099/tunefymusicapi/music/" + 1513618892)
                return Gson().fromJson<Music>(echo, object: TypeToken<Music>(){}.type)
            }
        }

        task.onSucceeded = object : EventHandler<WorkerStateEvent> {
            override fun handle(event: WorkerStateEvent) {
                val tune = task.value
                println(tune)
                val tuneResource = tune.musicResource
                val tuneFile = SerializationUtils.deserialize<File>(tuneResource)
                createResourceFile(Files.readAllBytes(tuneFile.toPath()),
                        "chegou.mp3")
            }
        };

        Thread(task).start()
    }
}
