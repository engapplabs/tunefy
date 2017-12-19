package com.abuarquemf.tunefy.desktopapp.controllers

import com.abuarquemf.tunefy.desktopapp.connectionhandler.RestHandler
import com.abuarquemf.tunefy.desktopapp.models.Music
import com.abuarquemf.tunefy.desktopapp.streamhandler.SerializationUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView
import javafx.concurrent.Task
import javafx.event.Event
import javafx.fxml.FXML
import javafx.concurrent.WorkerStateEvent
import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.scene.input.MouseEvent
import java.io.IOException
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.file.Files
import java.util.*

class MainController : Initializable {

    @FXML
    lateinit var closeAppButton: MaterialDesignIconView

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Initialized")
    }

    @FXML
    fun closeApp(event: MouseEvent) {
        if(event.source == closeAppButton)
            System.exit(0)
    }

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

    @FXML
    fun signInAction(evente: Event) {
        println("Sign in")
    }
}
