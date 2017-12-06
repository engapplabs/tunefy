package com.abuarquemf.tunefy.musicmanager.controllers

import javafx.event.Event
import javafx.fxml.FXML
import javafx.scene.control.Button
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

    @FXML
    private lateinit var sendTuneButton: Button

    private var tunePath: String? = null

    fun initialize() {
        sendTuneButton.isVisible = false
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
                    sendTuneButton.isVisible = true
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
        //TODO send music to server
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
