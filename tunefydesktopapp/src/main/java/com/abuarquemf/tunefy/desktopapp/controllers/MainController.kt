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
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.paint.Material
import java.io.IOException
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.file.Files
import java.util.*

class MainController : Initializable {

    @FXML
    lateinit var closeAppButton: MaterialDesignIconView

    @FXML
    lateinit var closeTunesListNode: MaterialDesignIconView

    @FXML
    lateinit var closeAccountSettingsNode: MaterialDesignIconView

    @FXML
    lateinit var closeUserSettingsNode: MaterialDesignIconView

    @FXML
    lateinit var playPauseButton: MaterialDesignIconView

    @FXML
    lateinit var nextTuneButton: MaterialDesignIconView

    @FXML
    lateinit var repeatTuneButton: MaterialDesignIconView

    @FXML
    lateinit var prevTuneButton: MaterialDesignIconView

    @FXML
    lateinit var shuffleTuneButton: MaterialDesignIconView

    @FXML
    lateinit var tunesListNode: AnchorPane

    @FXML
    lateinit var accountSettingsNode: AnchorPane

    @FXML
    lateinit var userSettingsNode: AnchorPane

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Initialized")
    }

    /**
     * It handles tune play control buttons,
     * such as play, repeat, shuffle...
     *
     */
    @FXML
    fun handleTunePlaying(event: MouseEvent) {
        when(event.source) {
            playPauseButton -> downloadTune()
            nextTuneButton -> println("next")
            prevTuneButton -> println("Prev")
            shuffleTuneButton -> println("shuffle")
            repeatTuneButton -> println("Repeat")
        }
    }

    /**
     * It handles close situations in app, such as
     * properly close the app and also close internal
     * nodes.
     */
    @FXML
    fun closeApp(event: MouseEvent) {
        if(event.source == closeAppButton)
            System.exit(0)
        else if(event.source == closeTunesListNode)
            tunesListNode.isVisible = false
        else if(event.source == closeAccountSettingsNode)
            accountSettingsNode.isVisible = false
        else if(event.source == closeUserSettingsNode)
            userSettingsNode.isVisible = false
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

    fun downloadTune() {

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
                //createResourceFile(Files.readAllBytes(tuneFile.toPath()),
                  //      "chegou.mp3")

                val media = Media(tuneFile.toURI().toURL().toString())
                val mediaPlayer = MediaPlayer(media)
                mediaPlayer.play()
            }
        };

        Thread(task).start()
    }

    @FXML
    fun signInAction(evente: Event) {
        println("Sign in")
    }
}
