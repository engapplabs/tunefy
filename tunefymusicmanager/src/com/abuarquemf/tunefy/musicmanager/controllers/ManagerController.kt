package com.abuarquemf.tunefy.musicmanager.controllers

import com.abuarquemf.tunefy.musicmanager.connectionhandler.RestHandler
import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.FileChooser

class ManagerController {

    @FXML
    private lateinit var musicNameField: TextField

    @FXML
    private lateinit var bandNameField: TextField

    @FXML
    private lateinit var infoLabel: Label

    private var tunePath: String? = null

    private var isAvaiableToSendTune: Boolean = false

    private var usersCounter: Int = 0

    private var tunesCounter: Int = 0

    fun initialize() {
        //TODO retrieve info from server aobut users and tunes
    }

    @FXML
    fun picTuneAction(event: Event) {
        val musicName = musicNameField.text
        val bandName = bandNameField.text
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
            val response = RestHandler.getInstance().doGet("")
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

    }
}
