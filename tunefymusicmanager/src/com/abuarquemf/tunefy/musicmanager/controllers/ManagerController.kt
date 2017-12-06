package com.abuarquemf.tunefy.musicmanager.controllers

import com.abuarquemf.tunefy.musicmanager.configuration.URLhandler
import com.abuarquemf.tunefy.musicmanager.connectionhandler.RestHandler
import com.abuarquemf.tunefy.musicmanager.models.Music
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.text.Text
import javafx.stage.FileChooser

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

    private var musicName: String? = null
    private var bandName: String? = null

    private var tunePath: String? = null

    private var isAvaiableToSendTune: Boolean = false

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
            val choosenImage = fileChooser.showOpenDialog(infoLabel.getScene().getWindow())
            if (choosenImage != null) {
                try {
                    tunePath = choosenImage.toURI().toURL().toString()
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
    fun sendTuneAction(event: Event) {
        if(isAvaiableToSendTune) {
            val response = RestHandler.getInstance()
                    .doPost(URLhandler.urlPOST(),
                            Music(musicName!!, bandName!!, "", -505))
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

    }

    @FXML
    fun queryTunesAction(event: Event) {

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
