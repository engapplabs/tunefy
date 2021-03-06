package com.abuarquemf.tunefy.musicmanager.controllers

import com.abuarquemf.tunefy.musicmanager.Main
import com.abuarquemf.tunefy.musicmanager.configuration.URLhandler
import com.abuarquemf.tunefy.musicmanager.connectionhandler.RestHandler
import com.abuarquemf.tunefy.musicmanager.models.Music
import com.abuarquemf.tunefy.musicmanager.streamhandler.ResourceStreamUtil
import com.abuarquemf.tunefy.musicmanager.streamhandler.SerializationUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.nio.file.Files

class ManagerController {

    @FXML
    private lateinit var musicNameField: TextField

    @FXML
    private lateinit var bandNameField: TextField

    @FXML
    private lateinit var infoLabel: Label

    @FXML
    private lateinit var usersCounter: Label

    @FXML
    private lateinit var tunesCounter: Label

    @FXML
    private lateinit var idField: TextField

    @FXML
    private lateinit var defaultInfoLabel: Label

    @FXML
    private lateinit var reportText: Text

    @FXML
    private lateinit var tuneImage: ImageView

    private var musicName: String? = null
    private var bandName: String? = null

    private var tunePath: String? = null

    private var isAvaiableToSendTune: Boolean = false

    private var choosenTune: File? = null

    private var choosenImage: File? = null

    fun initialize() {
        //TODO retrieve info from server aobut users and tunes
        usersCounter.isVisible = false
        tunesCounter.isVisible = false
    }

    /**
     * It gets basic information to setup UI.
     *
     */
    fun onSetupGUIManager() {
        //TODO retrieve users and tune counter
    }

    @FXML
    fun signOutAction(event: Event) {
        try {
            (event.source as Node).scene.window.hide()
            val mainSource = FXMLLoader.load<Parent>(javaClass
                    .getResource("../layouts/main_layout.fxml"))
            val mainStage = Stage()
            mainStage.icons.add(Image(javaClass.getResourceAsStream("../images/icon.png")))
            mainStage.title = Main.APP_NAME
            mainStage.scene = Scene(mainSource)
            mainStage.isResizable = false
            mainStage.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @FXML
    fun picTuneAction(event: Event) {
        musicName = musicNameField.text
        bandName = bandNameField.text
        if(musicName.equals("") || bandName.equals("")) {
            infoLabel.text = "Fill all spaces."
        } else {
            val fileChooser = FileChooser()
            fileChooser.title = "Pick tune"
            fileChooser.extensionFilters.addAll(FileChooser.ExtensionFilter("Tune sources",
                    *arrayOf("*.mp3")))
            choosenTune = fileChooser.showOpenDialog(infoLabel.getScene().getWindow())
            if (choosenTune != null) {
                try {
                    tunePath = choosenTune!!.toURI().toURL().toString()
                    infoLabel.text = tunePath
                    isAvaiableToSendTune = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                infoLabel.text = "Selecting canceled."
            }
        }
    }

    @FXML
    fun picImageAction(event: Event) {
        val fileChooser = FileChooser()
        fileChooser.title = "Pick tune"
        fileChooser.extensionFilters.addAll(FileChooser.ExtensionFilter("Tune sources",
                *arrayOf("*.png", "*.jpg")))
        choosenImage = fileChooser.showOpenDialog(infoLabel.getScene().getWindow())
        if (choosenImage != null) {
            val compressed = SerializationUtils.serialize(choosenImage)
            println("Image compress: $compressed")
            try {
                //picPath = choosenImage!!.toURI().toURL().toString()
                //tuneImage!!.text = picPath
                tuneImage.image = Image(choosenImage!!.toURI().toURL().toString())
                isAvaiableToSendTune = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            infoLabel.text = "Selecting canceled."
        }
    }

    @FXML
    fun sendTuneAction(event: Event) {
        if(isAvaiableToSendTune) {
            var tuneAsBytes = choosenTune!!.absolutePath
            println("PATH: " + tuneAsBytes)
            var handler = ResourceStreamUtil()
            val response = RestHandler.getInstance()
                    .doPost(URLhandler.urlPOST(),
                            Music(musicName!!, bandName!!,
                                    handler.compressResource(tuneAsBytes),
                                    SerializationUtils.serialize(choosenImage)))
            defaultInfoLabel.text = "Added new tune"
            val responseTune = Gson().fromJson<Music>(
                    response, object: TypeToken<Music>(){}.type)
            var messageBuilder = StringBuilder()
            messageBuilder.append("Tune: ${responseTune.name}")
            messageBuilder.append("\nBand: ${responseTune.band}")
            messageBuilder.append("\nID: ${responseTune.id}")
            reportText.text = messageBuilder.toString()
        } else {
            infoLabel.text = "Fill all spaces."
        }
    }

    @FXML
    fun deleteTuneAction(event: Event) {
        val givenId = idField.text
        if(givenId.equals("")) {
            infoLabel.text = "Fill id space."
        } else {
            val response = RestHandler.getInstance()
                    .doPost(URLhandler.urlDELETE() + givenId,
                            Music(givenId.toLong()))
            defaultInfoLabel.text = "Tune was deleted"
            var messageBuilder = StringBuilder()
            messageBuilder.append("Tune with id $givenId was removed from database.")
            reportText.text = messageBuilder.toString()
        }
    }

    @FXML
    fun queryTunesAction(event: Event) {
        val givenId = idField.text
        if(givenId.equals("")) {
            infoLabel.text = "Fill id space."
        } else {
            val response = RestHandler.getInstance()
                    .doGet(URLhandler.urlGet() + givenId)
            defaultInfoLabel.text = "Tune info"
            val responseTune = Gson().fromJson<Music>(
                    response, object: TypeToken<Music>(){}.type)
            val messageBuilder = StringBuilder()
            messageBuilder.append("Tune name: ${responseTune.name}")
            messageBuilder.append("\nTune band: ${responseTune.band}")
            reportText.text = messageBuilder.toString()
        }
    }

    @FXML
    fun updateTuneAction(event: Event) {
        if(isAvaiableToSendTune) {
            idField.isDisable = false
        } else {
            infoLabel.text = "Fill all spaces."
        }
    }
}
