package com.abuarquemf.tunefy.desktopapp.controllers

import com.abuarquemf.tunefy.desktopapp.connectionhandler.RestHandler
import com.abuarquemf.tunefy.desktopapp.models.Music
import com.abuarquemf.tunefy.desktopapp.streamhandler.SerializationUtils.deserialize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView
import javafx.concurrent.Task
import javafx.event.Event
import javafx.fxml.FXML
import javafx.concurrent.WorkerStateEvent
import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.ImageViewBuilder
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import org.controlsfx.control.textfield.TextFields
import java.io.IOException
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.file.Files
import java.util.*

class MainController : Initializable {

    @FXML
    lateinit var searchTuneField: TextField

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

    @FXML
    lateinit var tuneImage: ImageView

    @FXML
    lateinit var tuneNameLabel: Label

    @FXML
    lateinit var searchTune: MaterialDesignIconView

    @FXML
    lateinit var changeablePane: AnchorPane

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("Initialized")
        TextFields.bindAutoCompletion(searchTuneField, arrayListOf("Arctic Monkeys", "The Beatles"))
    }

    @FXML
    fun searchTuneEvent(event: MouseEvent) {
        println("searching tune")
        //changeablePane.children.add(ListView<String>())
    }

    /**
     * It handles tune play control buttons,
     * such as play, repeat, shuffle...
     *
     */
    @FXML
    fun handleTunePlaying(event: MouseEvent) {
        when(event.source) {
            playPauseButton -> downloadTune() //println("play/pause")
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
                        .doGet("http://localhost:8099/tunefymusicapi/music/" + 1513811858)
                return Gson().fromJson<Music>(echo, object: TypeToken<Music>(){}.type)
            }
        }

        task.onSucceeded = object : EventHandler<WorkerStateEvent> {
            override fun handle(event: WorkerStateEvent) {
                val tune = task.value
                println(tune)
                val tuneResource = tune.musicResource
                val tuneFile = deserialize<File>(tuneResource)
                //val tuneImge = deserialize<File>(tune.imageResource)
                tuneImage.image = Image("https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/ACDC_Back_in_Black.png/220px-ACDC_Back_in_Black.png")
                tuneNameLabel.text = tune.name
                //createResourceFile(Files.readAllBytes(tuneFile.toPath()),
                  //    "chegou.mp3")

                //val media = Media(tuneFile.toURI().toURL().toString())
                //val mediaPlayer = MediaPlayer(media)
                //mediaPlayer.play()
                //TODO SEE mp3handling project
            }
        };

        Thread(task).start()
    }

    @FXML
    fun signInAction(evente: Event) {
        println("Sign in")
    }
}
